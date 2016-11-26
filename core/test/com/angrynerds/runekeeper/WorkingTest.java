package com.angrynerds.runekeeper;

import com.angrynerds.runekeeper.Sword;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class WorkingTest 
{
    @Test
    public void thisAlwaysPasses() 
    {
        Sword s = new Sword();
        assertTrue(true);
    }

    @Test
    @Ignore
    public void thisIsIgnored() 
    {
    }
}