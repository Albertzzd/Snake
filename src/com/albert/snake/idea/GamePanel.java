package com.albert.snake.idea;

import com.sun.scenario.animation.shared.ClipEnvelope;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class GamePanel extends JPanel implements KeyListener, ActionListener {
    int length;//蛇的长度
    int[] snakeX = new int[600]; //蛇的坐标X
    int[] snakeY = new int[500]; //蛇的坐标Y
    String direction ;
    boolean isStrart = false;//游戏是否开始
    Timer timer = new Timer(100,this);//定时器
    //定义一个食物
    int foodx;
    int foody;
    Random random = new Random();
    boolean isFail = false;
    //积分系统
    int score ;
    //构造器
    public GamePanel() {
        init();
        //获取键盘的监听事件
        this.setFocusable(true);
        this.addKeyListener(this);
        timer.start();
    }

    //初始化
    public void init() {
        length = 3;
        snakeX[0] = 100;
        snakeY[0] = 100;//头部坐标
        snakeX[1] = 75;
        snakeY[1] = 100;//第一个身体坐标
        snakeX[2] = 50;
        snakeY[2] = 100;//第二个身体坐标
        direction = "right";
        score = 0;

        foodx = 25 + 25*random.nextInt(34);
        foody = 75 + 25*random.nextInt(24);
    }

    // 画板：画界面，画蛇
    // Graphics ： 画笔
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);//清屏
        this.setBackground(Color.WHITE);//设置背景的颜色
        //绘制头部的广告栏
        Data.header.paintIcon(this, g, 15, 11);
        //绘制游戏区域
        g.fillRect(15, 55, 850, 600);
        //画一条静态的小蛇
        if (direction.equals("right")) {
            Data.right.paintIcon(this, g, snakeX[0], snakeY[0]);
        } else if (direction.equals("left")) {
            Data.left.paintIcon(this, g, snakeX[0], snakeY[0]);
        } else if (direction.equals("up")) {
            Data.up.paintIcon(this, g, snakeX[0], snakeY[0]);
        } else if (direction.equals("down")) {
            Data.down.paintIcon(this, g, snakeX[0], snakeY[0]);
        }

        for (int i = 1; i < length; i++) {
            Data.body.paintIcon(this, g, snakeX[i], snakeY[i]);//蛇的身体长度通过length来控制
        }
        //画积分
        g.setColor(Color.white);
        g.setFont(new Font("微软雅黑",Font.BOLD,18));
        g.drawString("长度："+length,650,35);
        g.drawString("分数："+score,750,35);
        //画食物
        Data.food.paintIcon(this,g,foodx,foody);
        //游戏提示：是否开始
        if(isStrart == false)
        {
            //画一个文字，String
            g.setColor(Color.WHITE);//设置画笔的颜色
            g.setFont(new Font("微软雅黑",Font.BOLD,40));//设置字体
            g.drawString("按下空格开始游戏",300,300);
        }
        //失败提醒
        if(isFail)
        {
            g.setColor(Color.RED);//设置画笔的颜色
            g.setFont(new Font("微软雅黑",Font.BOLD,40));//设置字体
            g.drawString("游戏失败，按下空格重新开始",200,300);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //键盘按下，弹起：敲击
    }

    //接受键盘的输入：监听
    @Override
    public void keyPressed(KeyEvent e) {
        //键盘按下，未释放
        //获取按下的键盘是哪个键
        int keyCode = e.getKeyCode();
        if(keyCode == KeyEvent.VK_SPACE)
        {
            if(isFail)
            {
                //失败，游戏再来一遍
                isFail = false;
                init();//重新初始化游戏
            }else
            {
                //暂停游戏
                isStrart = !isStrart;
            }
            repaint();//刷新界面
        }


        //键盘控制走向
        if(keyCode==KeyEvent.VK_LEFT)
        {
            direction = "left";
        }else if(keyCode==KeyEvent.VK_RIGHT)
        {
            direction = "right";
        }else if(keyCode==KeyEvent.VK_UP)
        {
            direction = "up";
        }else if(keyCode==KeyEvent.VK_DOWN)
        {
            direction = "down";
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // 释放某个键
    }

    //定时器，监听时间，帧：执行定时操作
    @Override
    public void actionPerformed(ActionEvent e) {
        //如果游戏处于开始状态,并且游戏没有结束
        if(isStrart && isFail == false){
            //右移
            for (int i = length-1;i>0;i--)
            {
                //除了脑袋，身体都向前移动
                snakeX[i] = snakeX [i-1];
                snakeY[i] = snakeY [i-1];
            }
            //通过控制方向让头部移动
            if(direction=="right")
            {
                snakeX[0]= snakeX[0]+25; //头部移动
                //边界判断
                if(snakeX[0]>850)
                {
                    snakeX[0]=25;
                }
            }else if(direction =="left")
            {
                snakeX[0]= snakeX[0]-25; //头部移动
                //边界判断
                if(snakeX[0]<25)
                {
                    snakeX[0]=850;
                }
            }else if(direction =="up")
            {
                snakeY[0]= snakeY[0]-25; //头部移动
                //边界判断
                if(snakeY[0]<75)
                {
                    snakeY[0]=650;
                }
            }else if(direction =="down")
            {
                snakeY[0]= snakeY[0]+25; //头部移动
                //边界判断
                if(snakeY[0]>650)
                {
                    snakeY[0]=75;
                }
            }
            //如果蛇头和食物坐标重合
            if(snakeX[0]==foodx && snakeY[0]==foody)
            {
                //长度+1
                length++;
                score += 10;
                //重新生成食物
                foodx = 25 + 25*random.nextInt(34);
                foody = 75 + 25*random.nextInt(24);
            }
            //结束判断
            for(int i = 1;i < length;i++)
            {
                if(snakeX[0]==snakeX[i] && snakeY[0]==snakeY[i])
                {
                    isFail = true;
                }
            }
            //刷新界面
            repaint();
        }
        timer.start();//让时间动起来
    }
}
