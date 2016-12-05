/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.angrynerds.runekeeper;
import java.io.Serializable;
/**
 *
 * @author Noah
 */
public class BuffAgainstWater implements Buff, Serializable {

    @Override
    public int buffEntity(EnemyType enemyType, int currentAttackPower) {
        return (enemyType.getClass() == (new WaterEnemyType()).getClass()) ? currentAttackPower + 4 : currentAttackPower;
    }

    @Override
    public String getName() {
        return "Water";
    }

    @Override
    public int getValue() {
        return 4;
    }

}
