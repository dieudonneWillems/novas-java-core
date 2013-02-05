package com.lapsedpacifist.astro.novas.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lapsedpacifist.astro.model.Time;

import junit.framework.Assert;
import junit.framework.AssertionFailedError;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase
{
	private static Logger logger = LoggerFactory.getLogger(AppTest.class);
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }
    
    public void testTime(){
    	try{
    		double jdutc = 2456329.106400; // 5 feb 2013 14:33:13 UTC 
    		double jdtai = 2456329.106400+0.0004050925926;
    		double millisecondPrecisionInDays = 1.2e-8;
    		Time time = Time.getTimeForJulianDateUTC(jdutc);
    		Assert.assertEquals("Time to Julian Date conversion", jdutc, time.getJulianDateUTC(), millisecondPrecisionInDays);
    		Assert.assertEquals("TAI time",jdtai,time.getJulianDateTAI(),millisecondPrecisionInDays);
    		Assert.assertEquals("TT-TAI conversion", time.getTimeTT()-time.getTimeTAI(), 32184l);
    	}catch(Exception e){
            AssertionFailedError ase = new AssertionFailedError(e.getMessage());
            ase.initCause(e);
            throw ase;
    	}
    }
}
