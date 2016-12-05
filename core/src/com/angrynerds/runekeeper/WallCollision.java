package com.angrynerds.runekeeper;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import java.util.Observable;
import java.util.Observer;

public class WallCollision implements Observer {

    private static final float OFFSET_X_AMOUNT = 40;
    private static final float OFFSET_Y_AMOUNT = 5;
    TiledMapTileLayer wallLayer;
    private Player player;
    //number of collision points checked along an edge of the sprite
    private final static float COLLISION_POINTS = 4;
    //number of pixels to reposition sprite after collision
    private final static int REPOSITION = 2;
    private float pLocationX;
    private float pLocationY;
    private float pSpriteWidth;
    private float pSpriteHeight;

    //needs layer 2!
    public WallCollision(TiledMapTileLayer wallLayer) {
        this.wallLayer = wallLayer;
    }

    @Override
    public void update(Observable obs, Object arg) {
        player = (Player) obs;
        /*
         Spite width is too fat for doorways, but this creates a smaller hitbox for wall collision
         */
        pLocationX = offsetX(player.getX());
        pLocationY = offsetY(player.getY());
        pSpriteWidth = trimWidth(player.getBounds().width);
        pSpriteHeight = trimHeight(player.getBounds().height);

        if (collidesTop()) {
            //going up
            player.setY(player.getY() - REPOSITION);
        }
        if (collidesLeft()) {
            //going left
            player.setX(player.getX() + REPOSITION);
        }
        if (collidesRight()) {
            //going right
            player.setX(player.getX() - REPOSITION);
        }
        if (collidesBottom()) {
            //going down
            player.setY(player.getY() + REPOSITION);
        }

        collidesDiagonally();

    }

    private boolean isBlocked(float x, float y) {
        TiledMapTileLayer.Cell cell = wallLayer.getCell((int) (x / wallLayer.getTileWidth()), (int) (y / wallLayer.getTileHeight()));
        if (cell == null || cell.getTile() == null) {
            return false;
        }
        if (cell.getTile().getProperties().containsKey("blocked")) {
            return true;
        }
        return false;
    }

    public boolean collidesTop() {
        for (float step = 0; step < pSpriteWidth; step += pSpriteWidth / COLLISION_POINTS) {
            if (isBlocked(pLocationX + step, pLocationY + pSpriteHeight)) {
                return true;
            }
        }
        return false;
    }

    public boolean collidesLeft() {
        for (float step = 0; step < pSpriteHeight; step += pSpriteHeight / COLLISION_POINTS) {
            if (isBlocked(pLocationX, pLocationY + step)) {
                return true;
            }
        }
        return false;
    }

    public boolean collidesRight() {
        for (float step = 0; step < pSpriteHeight; step += pSpriteHeight / COLLISION_POINTS) {
            if (isBlocked(pLocationX + pSpriteWidth, pLocationY + step)) {
                return true;
            }
        }
        return false;
    }

    public boolean collidesBottom() {
        for (float step = 0; step < pSpriteWidth; step += pSpriteWidth / COLLISION_POINTS) {
            if (isBlocked(pLocationX + step, pLocationY)) {
                return true;
            }
        }
        return false;
    }

    public boolean collidesDiagonally() {
        for (float step = 0; step != pSpriteWidth && step != pSpriteHeight; step += pSpriteWidth / COLLISION_POINTS) {
            if (collidesTop()) {
                //going up
                player.setY(player.getY() - REPOSITION);
            }
            if (collidesLeft()) {
                //going left
                player.setX(player.getX() + REPOSITION);
            }
            if (collidesRight()) {
                //going right
                player.setX(player.getX() - REPOSITION);
            }
            if (collidesBottom()) {
                //going down
                player.setY(player.getY() + REPOSITION);
            }
        }
        return false;
    }

    private float offsetX(float x) {
        return x + OFFSET_X_AMOUNT / 2;
    }

    private float offsetY(float y) {
        return y + OFFSET_Y_AMOUNT / 2;
    }

    private float trimWidth(float w) {
        return w - OFFSET_X_AMOUNT;
    }

    private float trimHeight(float h) {
        return h - OFFSET_Y_AMOUNT;
    }

}
