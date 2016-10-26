/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.angrynerds.runekeeper;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import java.util.Observable;
import java.util.Observer;
/**
 *
 * @author Christopher Pope
 */
public class HealTotem implements Observer  {
    
    private Player player;
    private String totemName;
    private int healCounter;
     TiledMapTileLayer collisionLayer;
    
    public HealTotem(String newName, TiledMapTileLayer collisionLayer){
        this.totemName = newName;  
        this.healCounter = 0;
        this.collisionLayer = collisionLayer;
        
    }

    @Override
    public void update(Observable o, Object o1) {
        player = (Player) o;
        
        TiledMapTileLayer.Cell cell = collisionLayer.getCell((int) (player.pos.x / collisionLayer.getTileWidth()), (int) (player.pos.y / collisionLayer.getTileHeight()));      
        
        if (cell == null || cell.getTile() == null) {

        }else if (healCounter < 1000 && cell.getTile().getProperties().containsKey("totem")){
            player.addhealth(1.0f);
            healCounter++;
        }       
    }    
    
}
