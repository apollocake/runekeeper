package com.angrynerds.runekeeper;

import com.angrynerds.runekeeper.Rune.RuneType;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import java.util.Observable;
import java.util.Observer;

public class DoorCollision implements Observer {

    TiledMapTileLayer collisionLayer;
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
    public DoorCollision(TiledMapTileLayer collisionLayer) {
        this.collisionLayer = collisionLayer;
    }

    @Override
    public void update(Observable obs, Object arg) {
        player = (Player) obs;
        /*
        Spite width is too fat for doorways, but this creates a smaller hitbox for wall collision
         */
        pLocationX = player.getX();
        pLocationY = player.getY();
        pSpriteWidth = player.getBounds().width;
        pSpriteHeight = player.getBounds().height;

        if (collidesTop()) {
            //going up
            player.setY(player.getY() - REPOSITION);
            System.out.println("top collision");
        }
        if (collidesLeft()) {
            //going left
            player.setX(player.getX() + REPOSITION);
            System.out.println("left collision");
        }
        if (collidesRight()) {
            //going right
            player.setX(player.getX() - REPOSITION);
            System.out.println("right collision");
        }
        if (collidesBottom()) {
            //going down
            player.setY(player.getY() + REPOSITION);
            System.out.println("bottom collision");
        }

    }

    private boolean isLocked(float x, float y) {
        TiledMapTileLayer.Cell cell = collisionLayer.getCell((int) (x / collisionLayer.getTileWidth()), (int) (y / collisionLayer.getTileHeight()));
        if (cell == null || cell.getTile() == null) {
            return false;
        }
        if (cell.getTile().getProperties().containsKey("grass") && (player.hasRuneEquipped(RuneType.GRASS))) {
            cell.setTile(null);
            return false;
        } else if (cell.getTile().getProperties().containsKey("water") && (player.hasRuneEquipped(RuneType.WATER))) {
            cell.setTile(null);
            return false;
        } else if (cell.getTile().getProperties().containsKey("fire") && (player.hasRuneEquipped(RuneType.FIRE))) {
            cell.setTile(null);
            return false;
        } else if (cell.getTile().getProperties().containsKey("ore") && (player.hasRuneEquipped(RuneType.ORE))) {
            cell.setTile(null);
            return false;
        }
        return true;
    }

    public boolean collidesTop() {
        for (float step = 0; step < pSpriteWidth; step += pSpriteWidth / COLLISION_POINTS) {
            if (isLocked(pLocationX + step, pLocationY + pSpriteHeight)) {
                return true;
            }
        }
        return false;
    }

    public boolean collidesLeft() {
        for (float step = 0; step < pSpriteHeight; step += pSpriteHeight / COLLISION_POINTS) {
            if (isLocked(pLocationX, pLocationY + step)) {
                return true;
            }
        }
        return false;
    }

    public boolean collidesRight() {
        for (float step = 0; step < pSpriteHeight; step += pSpriteHeight / COLLISION_POINTS) {
            if (isLocked(pLocationX + pSpriteWidth, pLocationY + step)) {
                return true;
            }
        }
        return false;
    }

    public boolean collidesBottom() {
        for (float step = 0; step < pSpriteWidth; step += pSpriteWidth / COLLISION_POINTS) {
            if (isLocked(pLocationX + step, pLocationY)) {
                return true;
            }
        }
        return false;
    }
}
