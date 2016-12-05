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
public interface Buff {

    public int buffEntity(EnemyType enemyType, int currentAttackPower);

    public String getName();

    public int getValue();

}
