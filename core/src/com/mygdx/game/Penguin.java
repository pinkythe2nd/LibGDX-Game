package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class Penguin extends Game {
    public Skin skin;
    public Player player;
    public Enemy enemy;
    public BitmapFont font;
    public Integer tilesize;
    public Map map;

    public void create() {
        //globals
        tilesize = 16;
        //skin and font
        skin = new Skin(Gdx.files.internal("lml/skin/skin.json"));

        Texture fontTex = new Texture(Gdx.files.internal("font2.png"), true);
        fontTex.setFilter(Texture.TextureFilter.MipMapLinearNearest, Texture.TextureFilter.Linear);
        font = new BitmapFont(Gdx.files.internal("font2.fnt"), new TextureRegion(fontTex), false);

        //Pixmap pixmap = new Pixmap(Gdx.files.internal("bigsword.png"));
        //int xHotspot = pixmap.getWidth() / 2;
        //int yHotspot = pixmap.getHeight() / 2;
        //Cursor cursor = Gdx.graphics.newCursor(pixmap, xHotspot, yHotspot);
        //Gdx.graphics.setCursor(cursor);
        //pixmap.dispose();


        //making the game
        this.player = new Player();
        this.enemy = new Enemy(this);
        map = new Map(this);
        this.setScreen(new MainMenuScreen(this));
    }

    public void render() {
        super.render(); // important!
    }

    public void dispose() {
        font.dispose();
        skin.dispose();
    }

}
