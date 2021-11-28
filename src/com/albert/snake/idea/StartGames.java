package com.albert.snake.idea;

import javax.swing.*;

public class StartGames
{
    public static void main(String[] args)
    {
        //1-绘制一个静态窗口 JFrame
        JFrame frame = new JFrame("First Project about HungrySnake");
        //设置界面的大小
        frame.setBounds(10,10,900,720);
        frame.setResizable(false);//窗口大小不可以改变
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//设置关闭事件，游戏可以关闭了

        //2-面板 JPanel 可以加入到JFrame
        frame.add(new GamePanel());

        frame.setVisible(true);//让窗口能够展现出来
    }
}
