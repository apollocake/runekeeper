package com.angrynerds.runekeeper;

import com.badlogic.gdx.math.Vector2;

/**
 *
 * @author Christopher Pope
 */
public class CrazyPatrol implements EnemyPatrol {

    private int boxCounter;
    private int boxCounter2;
    private int boxCounter3;
    private int boxCounter4;
    private boolean bleft = false;
    private boolean bright = false;
    private boolean bdown = false;
    private boolean bup = false;
    private float xadjustment = 0.0f;
    private float yadjustment = 0.0f;

    private Vector2 pos = new Vector2();

    @Override
    public Vector2 patrol(Vector2 pos) {
        pos.y++;
        return pos;
    }

}
