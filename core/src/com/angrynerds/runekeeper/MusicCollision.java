/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.angrynerds.runekeeper;

import com.angrynerds.runekeeper.sound.BgmTrack;
import com.angrynerds.runekeeper.sound.FireAreaMusic;
import com.angrynerds.runekeeper.sound.GrassAreaMusic;
import com.angrynerds.runekeeper.sound.OreAreaMusic;
import com.angrynerds.runekeeper.sound.SoundFile;
import com.angrynerds.runekeeper.sound.StartAreaMusic;
import com.angrynerds.runekeeper.sound.WaterAreaMusic;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import java.util.Observable;
import java.util.Observer;

public class MusicCollision implements Observer {

    private Music triggeredMusicType;
    private Music currentMusicType;

    /**
     * @return the currentMusicType
     */
    public Music getCurrentMusicType() {
        return currentMusicType;
    }

    /**
     * @param currentMusicType the currentMusicType to set
     */
    public void setCurrentMusicType(Music currentMusicType) {
        this.currentMusicType = currentMusicType;
    }

    public enum Music {
        START, GRASS, WATER, FIRE, ORE
    }
    TiledMapTileLayer collisionLayer;
    private Player player;
    private BgmTrack bgmMusic;

    public MusicCollision(TiledMapTileLayer collisionLayer) {
        this.collisionLayer = collisionLayer;
        SoundFile soundFile = new StartAreaMusic();
        bgmMusic = new BgmTrack(soundFile);
        bgmMusic.play();
        currentMusicType = Music.START;
    }
    
    public void pause(){
        bgmMusic.pause();
    }
    public void play(){
        bgmMusic.play();
    }

    @Override
    public void update(Observable obs, Object arg) {
        player = (Player) obs;
        if (collidesBottomMusic() && getCurrentMusicType() != triggeredMusicType) { // going left
            switch (triggeredMusicType) {
                case GRASS:
                    bgmMusic.dispose();
                    bgmMusic = new BgmTrack(new GrassAreaMusic());
                    setCurrentMusicType(Music.GRASS);
                    break;
                case WATER:
                    bgmMusic.dispose();
                    bgmMusic = new BgmTrack(new WaterAreaMusic());
                    setCurrentMusicType(Music.WATER);
                    break;
                case FIRE:
                    bgmMusic.dispose();
                    bgmMusic = new BgmTrack(new FireAreaMusic());
                    setCurrentMusicType(Music.FIRE);
                    break;
                case ORE:
                    bgmMusic.dispose();
                    bgmMusic = new BgmTrack(new OreAreaMusic());
                    setCurrentMusicType(Music.ORE);
                    break;
                case START:
                    bgmMusic.dispose();
                    bgmMusic = new BgmTrack(new StartAreaMusic());
                    setCurrentMusicType(Music.START);
                    break;
                
                default:
                    break;
            }
            
            bgmMusic.play();
        }

    }

    private boolean isMusicCell(float x, float y) {
        TiledMapTileLayer.Cell cell = collisionLayer.getCell((int) (x / collisionLayer.getTileWidth()), (int) (y / collisionLayer.getTileHeight()));
        if (cell == null || cell.getTile() == null) {
            return false;
        }
        if (cell.getTile().getProperties().containsKey("grass")) {
            triggeredMusicType = Music.GRASS;
            return true;
        } else if (cell.getTile().getProperties().containsKey("water")) {
            triggeredMusicType = Music.WATER;
            return true;
        } else if (cell.getTile().getProperties().containsKey("fire")) {
            triggeredMusicType = Music.FIRE;
            return true;
        } else if (cell.getTile().getProperties().containsKey("ore")) {
            triggeredMusicType = Music.ORE;
            return true;
        } else if (cell.getTile().getProperties().containsKey("start")) {
            triggeredMusicType = Music.START;
            return true;
        }
        return false;

    }

    public boolean collidesBottomMusic() {
        for (float step = 0; step < player.playerAnimation.getWidth(); step += collisionLayer.getTileWidth() / 4) {
            if (isMusicCell(player.getX() + step, player.getY())) {
                return true;
            }
        }
        return false;
    }

    public void dispose() {
        this.bgmMusic.dispose();
    }

}
