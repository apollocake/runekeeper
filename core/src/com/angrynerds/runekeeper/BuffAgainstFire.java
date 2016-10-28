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
public class BuffAgainstFire implements Buff {

    @Override
    public int buffEntity(EnemyType enemyType, int currentAttackPower) {
        return (enemyType.getClass() == (new FireEnemyType()).getClass()) ? currentAttackPower + 1 : currentAttackPower;
    }

    @Override
    public String getName() {
        return "Fire";
    }

    @Override
    public int getValue() {
        return 1;
    }

}
