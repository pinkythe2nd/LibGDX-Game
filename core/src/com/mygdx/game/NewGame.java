package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class NewGame implements Screen {
    private Penguin game;
    Music music;
    Stage stage;

    Player.backStoriesEnum[] enumArray = Player.backStoriesEnum.values();

    public NewGame(final Penguin game){
        this.game = game;

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        Table root = new Table();
        root.setFillParent(true);

        stage.addActor(root);

        game.skin.get(Label.LabelStyle.class).font.getData().markupEnabled = true;
        Label title = new Label("Hello: [RED]" + game.player.getName() + "[]\nChoose your backstory!", game.skin);

        root.add(title).pad(30);
        root.row();
        Table table = new Table();
        ScrollPane scrollPane = new ScrollPane(table, game.skin);
        scrollPane.setFadeScrollBars(false);
        scrollPane.setFlickScroll(false);


        final String[] backStories =
                {"You are a poor farmer's son who is fed up of having to plow the fields for your father. One night you decide to sneak of to carve your own legacy!",

                        "You are the infamous ruler of babylon and after a particularly hard 10 years reign you decide to go on a adventure!",

                        "As a battlefield surgeon you have seen you fair share of blood, guts and gaping wounds, after a particularly harrowing battle you decide its time to hang up the bone saw and pursue greener and most importantly, less bloodier pastures!",

                        "you were raised in a small constantly moving group of hunters. But after a disastrous plague struck you are the only one of your clan left, how will you continue your peoples legacy!",

                        "You used to be an adventure, until you took an arrow to the knee, you decide go out on one more adventure before your bones ache too much!",

                        "As a sailor of the famous boat the Ironclad, you spent most of your times longing to get back to dry land to see your wife and infant baby after a particularly lonely 6 months at sea you return to find you wife and infant baby gone! with your cutlass in hand you set of to find them! ",

                        "you are a town guard, while your cousin was off fighting dragons you were stuck with guard duty, bored of it all, you decide, one day to venture outside the city walls!",

                        "The dwarves of moria were a stout, hardworking, foul smelling bunch of miners but aspired to greatness. But because of your peoples aspirations you were doomed to dig too deep and awaken something in the darkness, you saw its claws tear pillars of stone and its lash of flame cremate your brothers, you escaped, but now at the turn of the century you want revenge.",

                        "You are a homeless poor whore son, you were born on the streets of londinium amongst the piss and shit of rats and men alike, you learnt to fend for yourself and by some miracle kept yourself alive, today you have scrounged enough breadcrumbs to go have a look outside of the hustle and bustle of londinium",

                        "As the apprentice of the great j'zargo you have learnt a lot about destruction magic, but after a particular nasty 'experiment' involving fire salts and a 100 dollar bills you have been relieved of your position",

                        "You worked as a conjuration expert for the british empire until you tested your luck with teleportation magic and ended up half way across the world!",

                        "No Back story"};
        for (int i = 0; i < backStories.length; i ++) {
            TextButton button = new TextButton(backStories[i], game.skin);
            button.getLabel().setWrap(true);
            button.getLabel().setFontScale(1f);
            table.add(button).growX().pad(10);
            final int finalI = i;
            button.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    game.player.setBackstory(enumArray[finalI]);
                }
            });
            table.row();
        }
        root.add(scrollPane).growX();
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

    }
}
