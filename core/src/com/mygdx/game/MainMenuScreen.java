package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class MainMenuScreen implements Screen{
    final Penguin game;
    private Stage stage;

    public MainMenuScreen(final Penguin game) {
        this.game = game;

        stage = new Stage(new ScreenViewport());

        Gdx.input.setInputProcessor(stage);

        Table root = new Table();
        root.setFillParent(true);

        stage.addActor(root);

        Label title = new Label("Penguin RPG!", game.skin);
        title.setFontScale(2f);

        TextButton playButton = new TextButton("Play!", game.skin);
        playButton.getLabel().setFontScale(1.5f);
        TextButton newButton = new TextButton("New Game!", game.skin);
        newButton.getLabel().setFontScale(1.5f);
        TextButton exitButton = new TextButton("Exit!", game.skin);
        exitButton.getLabel().setFontScale(1.5f);

        root.add(title).pad(30);
        root.row();
        root.add(playButton).pad(20);
        playButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new GameScreen(game));
                dispose();
            }
        });
        root.row();
        root.add(newButton).pad(20);
        newButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new CharacterName(game));
                dispose();
            }
        });
        root.row();
        root.add(exitButton).pad(20);
        exitButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                dispose();
                Gdx.app.exit();
            }
        });

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.2f, 1);

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
