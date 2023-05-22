package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;


public class CharacterName implements Screen {
    Penguin game;
    private Stage stage;
    TextField textField;
    Table root;

    public CharacterName(Penguin game){
        this.game = game;

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        root = new Table();
        root.setFillParent(true);
        stage.addActor(root);

        Label label =  new Label(" Enter your characters name: ", game.skin);
        label.setWrap(true);
        root.add(label).top().growX();
        root.row();
        textField = new TextField("", game.skin);
        textField.setMaxLength(20);
        root.add(textField).growX().padBottom(400).pad(20);
        stage.setKeyboardFocus(textField);

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.2f, 1);

        if(Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
            if (textField.getText().isEmpty()) {
                if (root.getCells().size < 3) {
                    System.out.println(root.getCells().size);
                    Label label = new Label("INVALID NAME! Try again.", game.skin);
                    root.row();
                    root.add(label);
                }
            } else {
                game.player.setName(textField.getText());
                game.setScreen(new NewGame(game));
                dispose();
            }
        }
        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
