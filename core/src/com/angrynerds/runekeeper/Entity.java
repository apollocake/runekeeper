/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.angrynerds.runekeeper;

import com.badlogic.gdx.math.Rectangle;

import com.badlogic.gdx.math.Vector2;

public interface Entity {

    public String getName();

    public EntityAnimation getAnimation();

    public Vector2 getPosition();

    public Rectangle getRec();

    public Vector2 getDimensions();

    public void setDimensions(Vector2 newDimensions);

    public void update();

    public void setPatrol(EnemyPatrol patrolType);

    public void setAlert(boolean A);

    public boolean getAlert();

    public HealthBar getHealthBar();

    public void damage(int damage);

}
