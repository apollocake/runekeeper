package com.angrynerds.runekeeper;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class WorkingTest 
{
    @Test
    public void thisAlwaysPasses() 
    {
        assertTrue(false);
    }

    @Test
    @Ignore
    public void thisIsIgnored() 
    {
    }
}