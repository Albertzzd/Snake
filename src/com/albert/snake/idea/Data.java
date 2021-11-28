package com.albert.snake.idea;

import javax.swing.*;
import java.net.URL;

//存放外部数据
public class Data {
    //头部的图片  URL:定位图片地址  ImageIcone：图片
    public static URL headerURL = Data.class.getResource("/statics/header.png");
    public static ImageIcon header = new ImageIcon(headerURL);
    //头部
    public static URL downURL = Data.class.getResource("/statics/down.png");
    public static ImageIcon down = new ImageIcon(downURL);
    public static URL leftURL = Data.class.getResource("/statics/left.png");
    public static ImageIcon left = new ImageIcon(leftURL);
    public static URL rightURL = Data.class.getResource("/statics/right.png");
    public static ImageIcon right = new ImageIcon(rightURL);
    public static URL upURL = Data.class.getResource("/statics/up.png");
    public static ImageIcon up = new ImageIcon(upURL);
    //身体
    public static URL bodyURL = Data.class.getResource("/statics/body.png");
    public static ImageIcon body = new ImageIcon(bodyURL);
    //食物
    public static URL foodURL = Data.class.getResource("/statics/food.png");
    public static ImageIcon food = new ImageIcon(foodURL);

}
