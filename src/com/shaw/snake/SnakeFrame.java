package com.shaw.snake;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @Package com.shaw.snake
 * @Description: 贪吃蛇框架
 * @Author shawn
 * @Date Created in 2018-5-16
 */
public class SnakeFrame extends Frame {
    // 方块与边界
    public static final int BLOCK_WIDTH = 15;
    public static final int BLOCK_HIGHT = 15;
    // 界面方格 行列
    public static final int ROW = 40;
    public static final int COL = 40;

    private Image offScreenImage = null;
    private Snake snake = new Snake(this);

    public static void main(String[] args) {
        new SnakeFrame().launchFrame();
    }

    public void launchFrame() {
        this.setTitle("Snake");
        this.setSize(ROW * BLOCK_HIGHT, COL * BLOCK_WIDTH);
        this.setLocation(300, 400);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }

        });
        this.setResizable(false);
        this.setVisible(true);

        // 为界面添加监听事件
        this.addKeyListener(new KeyMonitor());
        new Thread(new MaPaintThread()).start();
    }

    /**
     * 画线条
     *
     * @param g
     */
    @Override
    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.GRAY);

        /**
         * 将界面化成由 ROW * COL 的小方格，使用两个for循环
         */
        for (int i = 0; i < ROW; i++) {
            g.drawLine(0, i * BLOCK_HIGHT, COL * BLOCK_WIDTH, i * BLOCK_HIGHT);
        }
        for (int i = 0; i < COL; i++) {
            g.drawLine(i * BLOCK_WIDTH, 0, i * BLOCK_WIDTH, ROW * BLOCK_HIGHT);
        }
        g.setColor(c);

    }

    /**
     * 利用双缓冲解决闪烁的问题
     *
     * @param g
     */
    @Override
    public void update(Graphics g) {
        if (null == offScreenImage) {
            offScreenImage = this.createImage(ROW * BLOCK_WIDTH, COL * BLOCK_HIGHT);
        }
        Graphics offg = offScreenImage.getGraphics();
        // 将内容画在虚拟画布上
        paint(offg);
        g.drawImage(offScreenImage,0,0,null);
        snake.draw(g);
    }

    /**
     * 属于蛇的重画，用线程进行
     */
    private class MaPaintThread implements Runnable {
        @Override
        public void run() {
            // 每隔50ms 重画一次
            while (true) {
                // 自动调用 paint方法
                repaint();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private class KeyMonitor extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e) {
            snake.keyPressed(e);
        }
    }
}


