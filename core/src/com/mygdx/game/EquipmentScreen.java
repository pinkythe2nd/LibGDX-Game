package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.Timer;

import java.util.HashMap;


public class EquipmentScreen implements Screen {
        Penguin game;
        Stage stage;

        final Table root = new Table();
        final Table left = new Table();
        Table middle = new Table();
        final Table right = new Table();
        final Table scroll = new Table();

        final TooltipManager tooltipManager = new TooltipManager();

        public EquipmentScreen(Penguin game){
            this.game = game;
        }

        @Override
        public void show() {
            stage = new Stage(new ScreenViewport());
            stage.getViewport().update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

            root.setFillParent(true);

            stage.addActor(root);

            Texture texture = new Texture("p.png");


            //middle
            Image sourceImage = new Image(texture);
            middle.add(sourceImage).width(320).height(320);
            sourceImage.setBounds(sourceImage.getX(), sourceImage.getY(), sourceImage.getWidth(), sourceImage.getHeight());

            //left and right
            int i = 0;
            for (Equipment.slots key : game.player.equiped.keySet()) {
                if (i < 6){
                    left.add(game.player.equiped.get(key)).width(64).height(64).padBottom(10);
                    left.row();
                } else {
                    right.add(game.player.equiped.get(key)).width(64).height(64).padBottom(10);
                    right.row();
                }
                i++;
            }
            root.add(left);
            root.add(middle);
            root.add(right);

            tooltipManager.subsequentTime = 999;
            tooltipManager.initialTime = 99;
            tooltipManager.hideAll();
            tooltipManager.maxWidth = 300;

            ScrollPane scrollPane = new ScrollPane(scroll, game.skin);
            scrollPane.setFadeScrollBars(false);
            scrollPane.setFlickScroll(false);

            scrollReset();

            root.add(scrollPane).width(500).height(512);
            Gdx.input.setInputProcessor(stage);

            root.row();
            TextButton textButton = new TextButton("back", game.skin);
            textButton.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    game.setScreen(new GameScreen(game));
                }
            });
            root.add(textButton);

        }


        private void leftRightReset(){
            int i = 0;
            left.reset();
            right.reset();
            for (Equipment.slots key : game.player.equiped.keySet()) {
                if (i < 6){
                    left.add(game.player.equiped.get(key)).width(64).height(64).padBottom(10);
                    left.row();
                } else {
                    right.add(game.player.equiped.get(key)).width(64).height(64).padBottom(10);
                    right.row();
                }
                i++;
            }
        }

        private void scrollRefresh() {
            scroll.reset();
            for (int i = game.player.equipment.size() - 1; i != -1; i--) {
                final Equipment equipment = game.player.equipment.get(i);
                final TextTooltip textTooltip = new TextTooltip(
                        "Name: " + equipment.getName() + "\nSlot: " + equipment.getSlot(), game.skin);

                if (i % 5 == 0) {
                    scroll.row();
                }
                scroll.add(equipment).width(64).height(64).pad(10).padTop(64);
            }
        }

        private void scrollReset(){
            tooltipManager.instant();
            scroll.reset();
            for (int i = game.player.equipment.size() -1; i != -1; i --){
                final Equipment equipment = game.player.equipment.get(i);
                final TextTooltip textTooltip = new TextTooltip(
                        "Name: " + equipment.getName() + "\nSlot: " + equipment.getSlot(),
                        tooltipManager, game.skin);

                textTooltip.getActor().setFontScale(0.5f);
                if (i % 5 == 0){
                    scroll.row();
                }

                equipment.addListener(textTooltip);
                equipment.addListener(new ClickListener(){
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        super.clicked(event, x, y);
                        Timer.schedule(new Timer.Task(){
                            @Override
                            public void run() {
                                if (getTapCount() == 2) {
                                    setTapCount(0);
                                    System.out.println("DOUBLE CLICK!!!!");
                                    if (game.player.equiped.get(equipment.getSlot()).equals(equipment)){
                                        System.out.println("default dance lmao xD EQDJSADNSJAODNSKLADASJO:");
                                        game.player.equipment.add(game.player.equiped.get(equipment.getSlot()));
                                        game.player.equiped.put(equipment.getSlot(), game.player.defaultEquiped.get(equipment.getSlot()));
                                        leftRightReset();
                                        scrollRefresh();
                                        tooltipManager.hideAll();
                                        return;
                                    }
                                    if (game.player.equiped.get(equipment.getSlot()).getName() != null) {
                                        System.out.println("SWAP!!!");
                                        //scroll.getCell(equipment).setActor(game.player.equiped.get(equipment.getSlot()));
                                        game.player.equipment.add(game.player.equiped.get(equipment.getSlot()));
                                        game.player.equiped.put(equipment.getSlot(), equipment);
                                        game.player.equipment.remove(equipment);
                                        leftRightReset();
                                    }
                                    if (left.getCell(game.player.equiped.get(equipment.getSlot())) != null) {
                                        System.out.println("not swap");
                                        left.getCell(game.player.equiped.get(equipment.getSlot())).setActor(equipment);
                                        game.player.equiped.put(equipment.getSlot(), equipment);
                                        game.player.equipment.remove(equipment);
                                    }
                                    if (right.getCell(game.player.equiped.get(equipment.getSlot())) != null) {
                                        System.out.println("not swap");
                                        right.getCell(game.player.equiped.get(equipment.getSlot())).setActor(equipment);
                                        game.player.equiped.put(equipment.getSlot(), equipment);
                                        game.player.equipment.remove(equipment);
                                    }

                                    scrollRefresh();
                                    tooltipManager.hideAll();
                                } else {
                                    if (getTapCount() == 0) return;
                                    tooltipManager.instant();
                                    tooltipManager.enter(textTooltip);
                                    setTapCount(0);
                                }
                                System.out.println("_------------------------------------_");
                            }
                        }, 0.2f);

                    }

                });
                scroll.add(equipment).width(64).height(64).pad(10).padTop(64);
            }
        }

        @Override
        public void render(float delta) {
            ScreenUtils.clear(0, 0, 0.2f, 1);

            stage.act();
            stage.draw();
        }

        @Override
        public void resize(int width, int height) {

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
