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
public class BuffAgainstOre implements Buff {

    @Override
    public int buffEntity(EnemyType enemyType, int currentAttackPower) {
        return (enemyType.getClass() == (new OreEnemyType()).getClass()) ? currentAttackPower + 5 : 0;
    }

}
