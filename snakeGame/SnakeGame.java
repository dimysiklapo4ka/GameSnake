package snakeGame;

import snakeObject.Mouse;
import snakeObject.Snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Created by d1mys1klapo4ka on 13.04.2017.
 */
public class SnakeGame extends JPanel implements ActionListener{
    /**
     * Константы для определения поля
     * SCALE - масштаб.
     * WIDTH - ширина.
     * HEIGHT - высота.
     * SPEED - скорость движения.
     */
    public static final int SCALE = 32;
    public static final int WIDTH = 20;
    public static final int HEIGHT = 20;
    public static final int SPEED = 5;

    Snake snake = new Snake(10, 10, 9, 10);//Задаем начальные координаты змейки.
    Mouse mouse = new Mouse((int)(Math.random()*WIDTH), (int)(Math.random()*HEIGHT));
    Timer timer = new Timer(1000/SPEED, this);//Задается скорость движения змейки.

    /**
     * Конструктор по умолчанию для создания экземпляра класса без параметров.
     */
    public SnakeGame(){
        timer.start();
        addKeyListener(new Keyboard());
        setFocusable(true);
    }

    /**
     * Метод предназначен для определения цвета.
     * выбор происходит по поиску в цветовом диапазоне rgb.
     * @param r red - отвечает за передачу красного.
     * @param g green - передача зеленного.
     * @param b blue - передача синего.
     * @return Color - возвращает заданный цвет.
     */
    public Color color(int r, int g, int b){
        return new Color(r, g, b);
    }

    /**
     * метод задает цвет поля и сетки.
     * @param graphics
     */
    public void paint(Graphics graphics){

        //задает цвет поля.
        graphics.setColor(color(10, 20, 65));
        graphics.fillRect(0, 0,(SCALE * WIDTH), (SCALE * HEIGHT));


        //задаем цвет сетки по оси X и Y.
        graphics.setColor(color(205,112,50));

        //прорисовываем координату X.
        for (int x = 0; x < WIDTH * SCALE; x += SCALE){
            graphics.drawLine(x, 0, x, WIDTH * SCALE);
        }
        //прорисовываем координату Y.
        for (int y = 0; y < HEIGHT * SCALE; y += SCALE){
            graphics.drawLine(0, y, HEIGHT * SCALE, y);
        }

        //прорисовываем змейку
        for (int d = 0; d < snake.lenght; d++){
            graphics.setColor(color(200, 20, 90));
            graphics.fillRect(snake.snakeX[d] * SCALE + 1, snake.snakeY[d] * SCALE + 1,
                    SCALE - 1, SCALE - 1);
        }

        //прорисовываем мышь
        //for ();
        graphics.setColor(color(255, 0, 0));
        graphics.fillRect(mouse.posX * SCALE + 1, mouse.posY * SCALE + 1, SCALE - 1, SCALE - 1);
    }

    public static void main(String[] args) {

        /**
         * Данный блок кода предназначен для создания окна и размещения его по центру монитора.
         * frame - создает окно.
         * setDefaultCloseOperation - отвечает за закрытие окна при нажатии на крестик.
         * setResizable() -
         * setLocationRelativeTo() - размещает окно по центру монитора.
         * setSize - Задает размер окна.
         * add(new SnakeGame) - создает обьект, который показывается на экране монитора.
         * setVisible - вкл отображения окна.
         */
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize((SCALE * WIDTH) + 6, (SCALE * HEIGHT) + 29);
        frame.setLocationRelativeTo(null);
        frame.add(new SnakeGame());
        frame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        snake.move();

        if ((snake.snakeX[0] == mouse.posX) && (snake.snakeY[0] == mouse.posY)){
            mouse.setRandomPosition();
            snake.lenght++;
        }

        for (int i = 1; i < snake.lenght; i++){
            if ((snake.snakeX[i] == mouse.posX) && (snake.snakeY[i] == mouse.posY)){
                mouse.setRandomPosition();
            }
        }

        if ((snake.snakeX[0] == mouse.posX) && (snake.snakeY[0] == mouse.posY)){
            mouse.setRandomPosition();
            snake.lenght++;
        }
        repaint();
    }

    private class Keyboard extends KeyAdapter{
        public void keyPressed(KeyEvent keyEvent){

            int key = keyEvent.getKeyCode();

            if ((key == KeyEvent.VK_RIGHT) & snake.direction != 2)snake.direction = 0;
            if ((key == KeyEvent.VK_DOWN) & snake.direction != 3)snake.direction = 1;
            if ((key == KeyEvent.VK_LEFT)  & snake.direction != 0)snake.direction = 2;
            if ((key == KeyEvent.VK_UP)  & snake.direction != 1)snake.direction = 3;
        }
    }
}
