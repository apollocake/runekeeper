/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
       
        if(boxCounter<100){
           pos.x --;
           //xadjustment = -1.0f;
           boxCounter++;
        }
        if(boxCounter>=60){
            bleft = true;
        }
        if(bleft == true && boxCounter2<100){
           pos.y --;
           
           boxCounter2++;
        }
        if(boxCounter2>=85){
            bdown = true;
        }
        if(bdown == true && boxCounter3<100){
           pos.x ++;
           
           boxCounter3++;
        }
        if(boxCounter3>=75){
            bright = true;
        }
        if(bright == true && boxCounter4<100){
           pos.y ++;
          // adjustment += 1.0f;
           
           boxCounter4++;
        }
        if(boxCounter4>=100){
            bup = true;
        }
        if(bleft == true && bright == true && bup == true && bdown == true){
            boxCounter = 0;
            boxCounter2 = 0;
            boxCounter3 = 0;
            boxCounter4 = 0;
            
            bleft = false;
            bright = false;
            bup = false;
            bdown = false;
        }
        return pos;
    }
    
}
