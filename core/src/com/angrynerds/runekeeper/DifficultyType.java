/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.angrynerds.runekeeper;

import com.badlogic.gdx.math.Vector2;

/**
 *
 * @author Noah
 */
public interface DifficultyType {

    public Vector2 getDifficultyDimensions();
    public void TransformEntity(Entity entityToTransform);
    
}
