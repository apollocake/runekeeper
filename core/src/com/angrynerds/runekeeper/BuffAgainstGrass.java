/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.angrynerds.runekeeper;

/**
 *
 * @author Noah
 */
public class BuffAgainstGrass implements Buff{

    @Override
    public int buffEntity(EnemyType enemyType, int currentAttackPower) {
        return (enemyType.getClass() == (new GrassEnemyType()).getClass()) ? currentAttackPower + 5 : 0;
    }

}
