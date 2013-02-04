package com.lapsedpacifist.astro.model.position;


/**
 * Class specifying co-ordiates on the celestial sphere.
 * The co-ordinates are associated with a specific co-ordinate system.
 * @author don_willems
 *
 */
public class CelestialPosition extends Position {

	/** Flag specifying the ICRS Equatorial co-ordinate system. */
	public static short EQUATORIAL_COORDINATE_SYSTEM_ICRS=-1;
	
	/** Flag specifying the GCRS (or local GCRS) Equatorial co-ordinate system. */
	public static short EQUATORIAL_COORDINATE_SYSTEM_GCRS=0;
	
	/** Flag specifying the Equatorial co-ordinate system of the true equator and equinox of date. */
	public static short EQUATORIAL_COORDINATE_SYSTEM_TRUE_EQUATOR_AND_EQUINOX_OF_DATE=1;
	
	/** Flag specifying the Equatorial co-ordinate system of the true equator and CIO of date. */
	public static short EQUATORIAL_COORDINATE_SYSTEM_TRUE_EQUATOR_CIO_OF_DATE=2;
	
	/** Flag specifying the Astrometric Equatorial co-ordinate system without light deflection or aberration. */
	public static short EQUATORIAL_COORDINATE_SYSTEM_ASTROMETRIC=3;
	public static short ECLIPTICAL_COORDINATE_SYTSEM=10;
	public static short GALACTIC_COORDINATE_SYSTEM=20;
	public static short HORIZONTAL_COORDINATE_SYSTEM=30;
	
	private short coordinateSystem = EQUATORIAL_COORDINATE_SYSTEM_ICRS;
	
	public short getCoordinateSystem(){
		return coordinateSystem;
	}

}
