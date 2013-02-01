package com.lapsedpacifist.astro.novas.core;

public abstract class Observatory {
	
	public static short GEOCENTRIC_OBSERVATORY = 0;
	public static short TOPOCENTRIC_OBSERVATORY = 1;
	public static short NEAR_EARTH_OBSERVATORY = 2;
	
	/**
	 * Where parameter used int the NOVAS library.
	 * @return
	 */
	public abstract short getNOVASWhere();
}
