package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Enemy extends Sprite {

    Penguin game;
    int level;

    public Enemy(Penguin game){
        super(new Sprite(new Texture("redskull.png")));

        this.game = game;
        this.level = RandyRandom.getRandomNumber(game.player.getLevel() - 3, game.player.getLevel() + 5);

        if (level < game.player.getLevel()){
            this.setTexture(new Texture("greenskull.png"));
        }
        if (level == game.player.getLevel()) {
            this.setTexture(new Texture("orangeskull.png"));
        }

    }

    public void update(){
        int randomnum = RandyRandom.getRandomNumber(1, 14);
        if (randomnum < 9){
            switch (randomnum) {
                case 1:
                    setX(this.getX() + 16);
                    break;
                case 2:
                    setX(this.getX() - 16);
                    break;
                case 3:
                    setY(this.getY() + 16);
                    break;
                case 4:
                    setY(this.getY() - 16);
                    break;
                case 5:
                    setPosition(this.getX() + 16, this.getY() + 16);
                    break;
                case 6:
                    setPosition(this.getX() - 16, this.getY() - 16);
                    break;
                case 7:
                    setPosition(this.getX() - 16, this.getY() + 16);
                    break;
                case 8:
                    setPosition(this.getX() + 16, this.getY() - 16);
                    break;
            }
        }
        if (this.getX() < 0) this.setX(0);
        if (this.getY() < 0) this.setY(0);
        if (this.getX() > game.map.mapWidth * 16) this.setX(game.map.mapWidth * 16);
        if (this.getY() > game.map.mapHeight * 16) this.setY(game.map.mapHeight * 16);

        //System.out.println("pos x: " + this.getX() + " pos y: " + this.getY());

    }

}
