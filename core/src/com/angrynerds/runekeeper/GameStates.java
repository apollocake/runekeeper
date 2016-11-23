/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.angrynerds.runekeeper;

import static com.angrynerds.runekeeper.GameStates.gsPlayerXpos;
import com.angrynerds.runekeeper.Rune.RuneType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author BG_01
 */
public class GameStates {

    public static float gsPlayerXpos;
    public static float gsPlayerYpos;
    public static Animation gsPlayerAnimation;
    public static Vector2 gsEnemyPos;
    public static float gsEnemyDist;
    public static ArrayList gsEnemyAnimation;
    public static int gsCurrentArea;
    public static int gsNumLives;
    public static float gsHealth;
    public static MusicCollision gsMusic;
    //public static boolean   gsRunes;
    public static int gsHue;
    public static boolean gsRuneFireGlove;
    public static boolean gsRuneWaterGlove;
    public static boolean gsRuneGrassGlove;
    public static boolean gsRuneOreGlove;
    public static boolean gsRuneFireSword;
    public static boolean gsRuneWaterSword;
    public static boolean gsRuneGrassSword;
    public static boolean gsRuneOreSword;

    static ArrayList<Object> gsObject = new ArrayList<Object>();

    public void musicGrab(MusicCollision mus) {
        RuneType gsMusic1 = mus.getCurrentMusicType();
        //GameStates.gsMusic = gsMusic1;

    }

    public static void gsExport(Player player) {

        gsPlayerXpos = player.pos.x;
        gsPlayerYpos = player.pos.y;
        gsPlayerAnimation = player.animation;

        gsNumLives = player.getPlayerLives();
        gsHealth = player.getCurrentHealth();
        gsRuneFireGlove = player.runeFireGlove;
        gsRuneWaterGlove = player.runeWaterGlove;
        gsRuneGrassGlove = player.runeGrassGlove;
        gsRuneOreGlove = player.runeOreGlove;
        gsRuneFireSword = player.runeFireSword;
        gsRuneWaterSword = player.runeWaterSword;
        gsRuneGrassSword = player.runeGrassSword;
        gsRuneOreSword = player.runeOreSword;

        //ArrayList<Object> gsObject = new ArrayList<Object>();
        gsObject.add(gsNumLives);               //0 player lives
        gsObject.add(gsPlayerXpos);             //1 player x position
        gsObject.add(gsPlayerYpos);             //2 player y position
        //gsObject.add(gsPlayerAnimation);
        gsObject.add(gsEnemyPos);               //3 enemy position
        gsObject.add(gsEnemyDist);              //4 enemy distance
        //gsObject.add(gsEnemyAnimation);
        gsObject.add(gsCurrentArea);            //5 current area
        gsObject.add(gsHealth);                 //6 health
        gsObject.add(gsRuneFireGlove);          //7 rune fire glove
        gsObject.add(gsRuneWaterGlove);         //8 rune water glove
        gsObject.add(gsRuneGrassGlove);         //9 rune grass glove
        gsObject.add(gsRuneOreGlove);           //10 rune ore glove
        gsObject.add(gsRuneFireSword);          //11 rune fire sword
        gsObject.add(gsRuneWaterSword);         //12 rune water sword
        gsObject.add(gsRuneGrassSword);         //13 rune grass sword
        gsObject.add(gsRuneOreSword);           //14 rune ore sword
        gsObject.add(gsMusic);                  //15 music

//gsObject.add(gsMusic);
        gsObject.add(gsHue);                    //16 hue
        Json json = new Json();
        json.setOutputType(JsonWriter.OutputType.minimal);
        //String gsFile = json.toJson(gsFile);

        //FileHandle file = Gdx.files.local("rkGamestate.json");
        ObjectOutputStream out;
        try {
            //System.setOut(new PrintStream(new File("output-file.txt")));
            //System.out.println(json.prettyPrint(gsObject));
            out = new ObjectOutputStream(new FileOutputStream("rkGamestate.json"));
            out.writeObject(gsObject);
            out.close();
            System.console();
        } catch (IOException ex) {
            Logger.getLogger(GameStates.class.getName()).log(Level.SEVERE, null, ex);
        }


    }

    public static void gsImport(Player player) throws ClassNotFoundException {
        ObjectInputStream in;
        try {
            //in = new ObjectInputStream(new FileInputStream("output-file.txt"));
            in = new ObjectInputStream(new FileInputStream("rkGamestate.json"));
            //gsObject = (ArrayList<Object>) in.readObject();
            ArrayList<Object> gsObject2 = (ArrayList<Object>) in.readObject();
            in.close();


            //gsPlayerXpos = gsObject2.add(0);
            System.out.println("number of lives = " + gsObject2.get(0));
            System.out.println("player x position = " + gsObject2.get(1));
            System.out.println("player y position = " + gsObject2.get(2));
            System.out.println("enemy position = " + gsObject2.get(3));
            System.out.println("enemy distance = " + gsObject2.get(4));
            System.out.println("current area = " + gsObject2.get(5));
            System.out.println("current health = " + gsObject2.get(6));
            System.out.println("rune fire glove = " + gsObject2.get(7));
            System.out.println("rune water glove = " + gsObject2.get(8));
            System.out.println("rune grass glove = " + gsObject2.get(9));
            System.out.println("rune ore glove = " + gsObject2.get(10));
            System.out.println("rune fire sword = " + gsObject2.get(11));
            System.out.println("rune water sword = " + gsObject2.get(12));
            System.out.println("rune grass sword = " + gsObject2.get(13));
            System.out.println("rune ore sword = " + gsObject2.get(14));
            System.out.println("music = " + gsObject2.get(15));
            System.out.println("hue = " + gsObject2.get(15));

            player.setPlayerLives((Integer)gsObject2.get(0));
            player.setPosition((Float)gsObject2.get(1),(Float)gsObject2.get(2));

            player.setGloveSword((Boolean)gsObject2.get(7), (Boolean)gsObject2.get(8), (Boolean)gsObject2.get(9), (Boolean)gsObject2.get(10), (Boolean)gsObject2.get(11), (Boolean)gsObject2.get(12), (Boolean)gsObject2.get(13), (Boolean)gsObject2.get(14));

            player.setCurrentHealth(100);

        } catch (IOException ex) {
            Logger.getLogger(GameStates.class.getName()).log(Level.SEVERE, null, ex);
        }

   }

    public static void gsInit(Player player) {
        gsPlayerXpos = 1;
        gsPlayerYpos = 2;
        gsPlayerAnimation = player.playerAnimation.downIdling;
        //gsEnemyPos = 5;
        gsEnemyDist = 5;
        //gsEnemyAnimation = 6;
        gsCurrentArea = 7;
        gsNumLives = 8;
        gsHealth = 9;
        //gsMusic = 10;
        //gsRunes = 11;
        gsHue = 12;

    }

}
