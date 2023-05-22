package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector3;

public class Map extends ApplicationAdapter implements InputProcessor  {
    Penguin game;
    TiledMap tiledMap;
    OrthographicCamera camera;
    TiledMapRenderer tiledMapRenderer;
    SpriteBatch spriteBatch;
    int mapWidth, mapHeight;
    int howManyTilesWide = 30;
    int howManyTilesHigh = 20;
    public Map(Penguin game){
        this.game = game;
        camera = new OrthographicCamera();

        tiledMap = new TmxMapLoader().load("map.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap, 1);
        spriteBatch = new SpriteBatch();

        mapWidth = tiledMap.getProperties().get("width", Integer.class);
        mapHeight = tiledMap.getProperties().get("height", Integer.class);

        System.out.println(mapHeight);

        camera.setToOrtho(false, game.tilesize * howManyTilesWide, game.tilesize * howManyTilesHigh);
        camera.update();
    }

    @Override
    public void render() {
        super.render();
        camera.update();

        //render
        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();

        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.begin();
        game.player.draw(spriteBatch);
        game.enemy.draw(spriteBatch);
        spriteBatch.end();
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    private void bounds(){
        int mapLeft = 0;
        int mapRight = mapWidth * game.tilesize;
        int mapBottom = 0;
        int mapTop = mapHeight * game.tilesize;

        float cameraHalfWidth = camera.viewportWidth * .5f;
        float cameraHalfHeight = camera.viewportHeight * .5f;

        float cameraLeft = camera.position.x - cameraHalfWidth;
        float cameraRight = camera.position.x + cameraHalfWidth;
        float cameraBottom = camera.position.y - cameraHalfHeight;
        float cameraTop = camera.position.y + cameraHalfHeight;

        if (mapWidth * game.tilesize < camera.viewportWidth) {
            camera.position.x = mapRight / 2;
        } else if (cameraLeft <= mapLeft) {
            camera.position.x = mapLeft + cameraHalfWidth;
        } else if (cameraRight >= mapRight) {
            camera.position.x = mapRight - cameraHalfWidth;
        }

        if (mapHeight * game.tilesize < camera.viewportHeight) {
            camera.position.y = mapTop / 2;
        } else if (cameraBottom <= mapBottom) {
            camera.position.y = mapBottom + cameraHalfHeight;
        } else if (cameraTop >= mapTop) {
            camera.position.y = mapTop - cameraHalfHeight;
        }
    }


    private boolean mapCollisions(float x, float y){
        TiledMapTileLayer layer = (TiledMapTileLayer) tiledMap.getLayers().get(0);
        int id = layer.getCell((int) (x / game.tilesize), (int) (y / game.tilesize)).getTile().getId();
        System.out.println(id);
        if (id == 31){
            return true;
        }
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Vector3 clickCoordinates = new Vector3(screenX, screenY, 0);
        Vector3 position = camera.unproject(clickCoordinates);

        position.x = game.tilesize * (Math.round((position.x - game.tilesize / 2) / game.tilesize));
        position.y = game.tilesize * (Math.round((position.y - game.tilesize / 2) / game.tilesize));

        if (!mapCollisions(position.x, position.y)) {

            int x = (int) (position.x - game.player.getX());
            int y = (int) (position.y - game.player.getY());

            int xSize = Math.abs(x * y);

            if (xSize == 256) {
                game.player.setPosition(position.x, position.y);
                camera.position.set(position.x, position.y, 0f);
            } else if (xSize == 0) {
                if ((game.player.getX() + game.tilesize == position.x || game.player.getX() - game.tilesize == position.x) ||
                        (game.player.getY() + game.tilesize == position.y || game.player.getY() - game.tilesize == position.y)) {
                    game.player.setPosition(position.x, position.y);
                    camera.position.set(position.x, position.y, 0f);
                }
            }
        }

        bounds();


        if (game.player.getX() == game.enemy.getX() && game.player.getY() == game.enemy.getY())
            System.out.println("ATTACK!");

        game.enemy.update();
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
