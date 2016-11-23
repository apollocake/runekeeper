/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.angrynerds.runekeeper;

import com.badlogic.gdx.math.Vector2;
import static java.lang.Math.abs;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author Christopher Pope
 */
public class AttackPatrol implements EnemyPatrol, Observer {
    
    private Player player ;
    private float enemyDistance;
    
    public AttackPatrol(Player player){
    this.player = player;
}

    @Override
    public Vector2 patrol(Enemy enemy) {
        enemyDistance = abs(enemy.getPosition().dst(player.pos));
        if (enemyDistance > 1.0f){
        enemy.getPosition().x = enemy.getPosition().x + ((player.pos.x - enemy.getPosition().x) * .01f);
        enemy.getPosition().y = enemy.getPosition().y + ((player.pos.y - enemy.getPosition().y) * .01f);
        }
        return enemy.getPosition();
        
    }

    @Override
    public void update(Observable o, Object o1) {
        player = (Player) o;
        
    }
    
}
