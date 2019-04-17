package com.shaw.snake;

import java.awt.*;
import java.awt.event.KeyEvent;

import static com.shaw.snake.Snake.Node.BLOCK_HEIGHT;
import static com.shaw.snake.Snake.Node.BLOCK_WIDTH;

/**
 * @Package com.shaw.snake
 * @Description:
 * @Author shawn
 * @Date Created in 2018-5-16
 */
public class Snake {
    private Node head = null;
    private Node tail = null;

    private SnakeFrame sf;
    // 初始化是蛇位置
    private Node node = new Node(3, 4, Direction.D);

    private int size = 0;

    public Snake(SnakeFrame sf) {
        head = node;
        tail = node;
        size++;
        this.sf = sf;
    }

    public void draw(Graphics g) {
        if (null == head) {
            return;
        }
        move();
        for (Node node = head; node != null; node = node.next) {
            node.draw(g);
        }
    }

    public void move() {
        addNodeInHead();
        deleteNodeInTail();
    }

    private void deleteNodeInTail() {
        Node node = tail.pre;
        tail = null;
        node.next = null;
        tail = node;
    }

    // 移动头
    private void addNodeInHead() {
        Node node = null;
        switch (head.dir) {
            case L:
                node = new Node(head.row, head.clo - 1, head.dir);
                break;
            case R:
                node = new Node(head.row, head.clo + 1, head.dir);
                break;
            case U:
                node = new Node(head.row - 1, head.clo, head.dir);
                break;
            case D:
                node = new Node(head.row + 1, head.clo, head.dir);
                break;
        }
        node.next = head;
        head.pre = node;
        head = node;
    }
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_LEFT:
                if (head.dir != Direction.R) {
                    head.dir = Direction.L;
                }
                break;
            case KeyEvent.VK_RIGHT:
                if (head.dir != Direction.L) {
                    head.dir = Direction.R;
                }
                break;
            case KeyEvent.VK_UP:
                if (head.dir != Direction.D) {
                    head.dir = Direction.U;
                }
                break;
            case KeyEvent.VK_DOWN:
                if (head.dir != Direction.U) {
                    head.dir = Direction.D;
                }
                break;
        }
    }

    public class Node {

        public static final int BLOCK_WIDTH = SnakeFrame.BLOCK_WIDTH;
        public static final int BLOCK_HEIGHT = SnakeFrame.BLOCK_HIGHT;
        // 每个节点的位置
        private int row;
        private int clo;
        private Direction dir;
        private Node pre;
        private Node next;
        public Node(int row, int clo, Direction dir) {
            this.row = row;
            this.clo = clo;
            this.dir = dir;
        }

        public void draw(Graphics g) {
            Color c = g.getColor();
            g.setColor(Color.BLACK);
            g.fillRect(clo * BLOCK_WIDTH, row * BLOCK_HEIGHT, BLOCK_WIDTH, BLOCK_HEIGHT);
            g.setColor(c);
        }
    }

    public Rectangle getRect(){
        return new Rectangle(head.clo * BLOCK_WIDTH, head.row * BLOCK_HEIGHT, BLOCK_WIDTH, BLOCK_HEIGHT);
    }
    Boolean eatEgg(Egg egg){
        if (this.getRect().intersects(egg.getRect())){
            addNodeInHead();
            egg.getRect();
            return true;
        }
        return false;
    }

}
