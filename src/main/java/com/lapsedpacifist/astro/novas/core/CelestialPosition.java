package com.lapsedpacifist.astro.novas.core;

public class CelestialPosition extends Position {

	public static short EQUATORIAL_COORDINATE_SYSTEM_ICRS=-1;
	public static short EQUATORIAL_COORDINATE_SYSTEM_GCRS=0;
	public static short EQUATORIAL_COORDINATE_SYSTEM_TRUE_EQUATOR_AND_EQUINOX_OF_DATE=1;
	public static short EQUATORIAL_COORDINATE_SYSTEM_TRUE_EQUATOR_CIO_OF_DATE=2;
	public static short EQUATORIAL_COORDINATE_SYSTEM_ASTROMETRIC=3;
	public static short ECLIPTICAL_COORDINATE_SYTSEM=10;
	public static short GALACTIC_COORDINATE_SYSTEM=20;
	public static short HORIZONTAL_COORDINATE_SYSTEM=30;
	
	private short coordinateSystem = EQUATORIAL_COORDINATE_SYSTEM_ICRS;
	
	public short getCoordinateSystem(){
		return coordinateSystem;
	}

}
