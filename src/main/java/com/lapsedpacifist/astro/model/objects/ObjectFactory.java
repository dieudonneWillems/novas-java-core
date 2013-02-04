package com.lapsedpacifist.astro.model.objects;

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

	public static short NOVAS_OBJECT_NUMBER_EXTRA_SOLAR_SYSTEM_OBJECT = 0;
	public static short NOVAS_OBJECT_NUMBER_MERCURY = 1;
	public static short NOVAS_OBJECT_NUMBER_VENUS = 2;
	public static short NOVAS_OBJECT_NUMBER_EARTH = 3;
	public static short NOVAS_OBJECT_NUMBER_MARS = 4;
	public static short NOVAS_OBJECT_NUMBER_JUPITER = 5;
	public static short NOVAS_OBJECT_NUMBER_SATURN = 6;
	public static short NOVAS_OBJECT_NUMBER_URANUS = 7;
	public static short NOVAS_OBJECT_NUMBER_NEPTUME = 8;
	public static short NOVAS_OBJECT_NUMBER_PLUTO = 9;
	public static short NOVAS_OBJECT_NUMBER_SUN = 10;
	public static short NOVAS_OBJECT_NUMBER_MOON = 11;
	
	/**
	 * Makes an instance of class <code>CelestialObject</code>, specifying a celestial object,
	 * based on the input parameters.<br/>
	 * This is the equivalent of the NOVAS-C function <code>make_object</code>.
	 * <br/>
	 * @param novasObjectType The object type to be created.
	 * @param novasObjectNumber The object number, use a flag for major planets, the minor planet number
	 * for a minor planet, or <code>NOVAS_OBJECT_NUMBER_EXTRA_SOLAR_SYSTEM_OBJECT</code>, for stars and 
	 * other extra solar sytem objects. 
	 * @param name The name of the object.
	 * @param cat_entry Catalogue data for extra solar system objects.
	 * @return Instance containing the object definition.
	 * @see #NOVAS_OBJECT_TYPE_MAJOR_SOLAR_SYSTEM_OBJECT
	 * @see #NOVAS_OBJECT_TYPE_MINOR_PLANET
	 * @see #NOVAS_OBJECT_TYPE_EXTRA_SOLAR_SYSTEM_OBJECT
	 * @see #NOVAS_OBJECT_NUMBER_EXTRA_SOLAR_SYSTEM_OBJECT
	 * @see #NOVAS_OBJECT_NUMBER_MERCURY
	 * @see #NOVAS_OBJECT_NUMBER_VENUS
	 * @see #NOVAS_OBJECT_NUMBER_EARTH
	 * @see #NOVAS_OBJECT_NUMBER_MARS
	 * @see #NOVAS_OBJECT_NUMBER_JUPITER
	 * @see #NOVAS_OBJECT_NUMBER_SATURN
	 * @see #NOVAS_OBJECT_NUMBER_URANUS
	 * @see #NOVAS_OBJECT_NUMBER_NEPTUME
	 * @see #NOVAS_OBJECT_NUMBER_PLUTO
	 * @see #NOVAS_OBJECT_NUMBER_SUN
	 * @see #NOVAS_OBJECT_NUMBER_MOON
	 */
	public CelestialObject createObject(int novasObjectType,int novasObjectNumber,String name,CatalogueObject cat_entry);
}
