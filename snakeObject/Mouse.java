package snakeObject;

import snakeGame.SnakeGame;

/**
 * Created by root on 13.04.17.
 */
public class Mouse {

    SnakeGame main;

    public int posX;
    public int posY;

    public Mouse(int startX, int startY){
        posX = startX;
        posY = startY;
    }

    public void setRandomPosition(){
        posX = (int)(Math.random() * main.WIDTH);
        posY = (int)(Math.random() * main.HEIGHT);
    }

}
