package com.angrynerds.runekeeper;

import com.angrynerds.runekeeper.Sword;
import com.angrynerds.runekeeper.sound.GameOverScreenMusic;
import java.util.Timer;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import org.junit.runner.RunWith;

@RunWith(GdxTestRunner.class)
public class WorkingTest 
{
    @Test
    public void thisAlwaysPasses()
    {
        Sword s = new Sword();
        GameOverScreenMusic g = new GameOverScreenMusic();
        g.start();        
        g.pause();
        g.stop();
        g.dispose();
        g.dispose();
        g.start();
    }

    @Test
    @Ignore
    public void thisIsIgnored() 
    {
    }
}