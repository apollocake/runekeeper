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
public class BossDifficultyType implements DifficultyType {

    private Vector2 dimensions;

    public BossDifficultyType(Vector2 newDimensions) {
        this.dimensions = newDimensions;
    }

    @Override
    public void TransformEntity(Entity entityToTransform) {
        entityToTransform.setDimensions(this.dimensions);
    }

    @Override
    public Vector2 getDifficultyDimensions() {
        return this.dimensions;
    }

}
