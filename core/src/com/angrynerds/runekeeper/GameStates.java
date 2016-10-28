/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.angrynerds.runekeeper;

import static com.angrynerds.runekeeper.GameStates.gsPlayerXpos;
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
        MusicCollision.Music gsMusic1 = mus.getCurrentMusicType();
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
        gsObject.add(gsPlayerXpos);
        gsObject.add(gsPlayerYpos);
        gsObject.add(gsPlayerAnimation);
        gsObject.add(gsEnemyPos);
        gsObject.add(gsEnemyDist);
        gsObject.add(gsEnemyAnimation);
        gsObject.add(gsCurrentArea);
        gsObject.add(gsNumLives);
        gsObject.add(gsHealth);
        gsObject.add(gsRuneFireGlove);
        gsObject.add(gsRuneWaterGlove);
        gsObject.add(gsRuneGrassGlove);
        gsObject.add(gsRuneOreGlove);
        gsObject.add(gsRuneFireSword);
        gsObject.add(gsRuneWaterSword);
        gsObject.add(gsRuneGrassSword);
        gsObject.add(gsRuneOreSword);
        gsObject.add(gsMusic);

//gsObject.add(gsMusic);
        gsObject.add(gsHue);

        Json json = new Json();
        json.setOutputType(JsonWriter.OutputType.minimal);
        //String gsFile = json.toJson(gsFile);

        //System.out.println(json.prettyPrint(gsObject));
        //FileHandle file = Gdx.files.local("rkGamestate.json");
        ObjectOutputStream out;
        try {
            System.setOut(new PrintStream(new File("output-file.txt")));
            //out = new ObjectOutputStream(new FileOutputStream("rkGamestate.json"));
        //out.writeObject(gsObject);
            //out.close();
            System.console();
        } catch (IOException ex) {
            Logger.getLogger(GameStates.class.getName()).log(Level.SEVERE, null, ex);
        }

        //file.writeString(gsObject);
        //json.writeObjectStart();
        //json.writeFields(gsObject);
        //json.writeObjectEnd();
    }

    public static void gsImport() throws ClassNotFoundException {
        ObjectInputStream in;
        try {
            in = new ObjectInputStream(new FileInputStream("output-file.txt"));

            gsObject = (ArrayList<Object>) in.readObject();
            in.close();
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
