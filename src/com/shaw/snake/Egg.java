package com.shaw.snake;

import java.awt.*;
import java.util.Random;

/**
 * @Package com.shaw.snake
 * @Description:
 * @Author shawn
 * @Date Created in 2018-5-16
 */
public class Egg {
    private int row;
    private int col;
    // 大小
    public static final int BLOCK_WIDTH = SnakeFrame.BLOCK_WIDTH;
    public static final int BLOCK_HIGHT = SnakeFrame.BLOCK_HIGHT;

    public static final Random r = new Random();

    private Color color = Color.RED;

    public Egg(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public Egg() {
        this((r.nextInt(SnakeFrame.ROW-2))+2,(r.nextInt(SnakeFrame.COL-2))+2);
    }

    /**
     * 改变当前对象的位置
     */
    public void reAppear(){
        this.row = r.nextInt(SnakeFrame.ROW-2)+2;
        this.col = r.nextInt(SnakeFrame.COL-2)+2;
    }

    public void draw(Graphics g){
        Color c = g.getColor();
        g.setColor(color);
        g.fillOval(col*BLOCK_WIDTH, row*BLOCK_HIGHT, BLOCK_WIDTH,BLOCK_HIGHT);
        g.setColor(c);
        if (Color.RED == color){
            color = Color.BLUE;
        } else {
            color = Color.RED;
        }
    }

    /**
     * 添加碰撞方法
     * @return
     */
    public Rectangle getRect(){
        return new Rectangle(col*BLOCK_WIDTH, row * BLOCK_HIGHT, BLOCK_WIDTH, BLOCK_HIGHT);
    }
}
