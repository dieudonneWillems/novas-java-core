package com.lapsedpacifist.astro.novas.core.objects;

public interface ObjectFactory {
	
	/**
	 * Object type number for major solar system object (Sun, Pluto, Jupiter, Moon, etc.).
	 */
	public static short NOVAS_OBJECT_TYPE_MAJOR_SOLAR_SYSTEM_OBJECT = 0;
	
	/**
	 * Object type number for a minor planet.
	 */
	public static short NOVAS_OBJECT_TYPE_MINOR_PLANET = 1;
	
	/**
	 * Object type number for an extra solar system object (star, nebula, galaxy, etc.).
	 */
	public static short NOVAS_OBJECT_TYPE_EXTRA_SOLAR_SYSTEM_OBJECT = 2;
	

	public CelestialObject createObject(int novasObjectType,int novasObjectNumber);
}
