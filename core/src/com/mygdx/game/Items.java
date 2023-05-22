package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class Items extends ImageButton {
    String name;

    public Items(String name, String fileName) {
        super(new TextureRegionDrawable(new Texture(fileName)));
        super.getImage().scaleBy(4);
        super.setBounds(super.getX(), super.getY(), super.getWidth(), super.getHeight());
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
