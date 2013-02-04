package com.lapsedpacifist.astro.novas.core;

import com.lapsedpacifist.astro.model.Time;
import com.lapsedpacifist.astro.model.objects.CatalogueObject;
import com.lapsedpacifist.astro.model.objects.CelestialObject;
import com.lapsedpacifist.astro.model.objects.DefaultObjectFactory;
import com.lapsedpacifist.astro.model.objects.ObjectFactory;
import com.lapsedpacifist.astro.model.observatory.EarthBasedObservatory;
import com.lapsedpacifist.astro.model.observatory.Observatory;
import com.lapsedpacifist.astro.model.position.CelestialPosition;


/**
 * The base class for the core NOVAS functionality. This is one of the
 * classes that implements the functionality provided by the NOVAS library.
 * @author don_willems
 *
 */
public class Novas {

	/** Julian Date (TDB) of epoch J2000.0 */
   public static double T0 = 2451545.00000000;

	/** Flag specifying that full accuracy is needed. */
	public static short NOVAS_FULL_ACCURACY = 0;
	
	/** Flag specifying that reduced accuracy is sufficient. */
	public static short NOVAS_REDUCED_ACCURACY = 1;
	
	private CelestialObject earth=null,sun=null;
	private ObjectFactory objectFactory;
	private static Novas singleton;
	
	public static Novas getNOVAS(){
		if(singleton==null) singleton = new Novas(new DefaultObjectFactory());
		return singleton;
	}
	
	/**
	 * Creates a new instance of Novas that can be used to access the different
	 * functions of the NOVAS library.
	 * @param objectfactory The object factory. Object factories provide the
	 * developer with the ability to create custom objects. 
	 */
	private Novas(ObjectFactory objectfactory){
		this.objectFactory = objectfactory;
		this.createEarthAndSun();
	}
	
	/**
	 * Creates the objects representing the Earth and the Sun. These class variables
	 * can be reused.
	 */
	private void createEarthAndSun() {
		earth = objectFactory.createObject(ObjectFactory.NOVAS_OBJECT_TYPE_MAJOR_SOLAR_SYSTEM_OBJECT, ObjectFactory.NOVAS_OBJECT_NUMBER_EARTH, "Earth", null);
		sun = objectFactory.createObject(ObjectFactory.NOVAS_OBJECT_TYPE_MAJOR_SOLAR_SYSTEM_OBJECT, ObjectFactory.NOVAS_OBJECT_NUMBER_SUN, "Sun", null);
	}

	/**
	 * Returns the object factory used for the creation of celestial objects and
	 * other associated classes. Object factories provide the developer with the
	 * ability to create custom objects. 
	 * @return The object factory.
	 */
	public ObjectFactory getObjectFactory(){
		return objectFactory;
	}
	
	/**
	 * Set the object factory used for the creation of celestial objects and
	 * other associated classes. Object factories provide the developer with the 
	 * ability to create custom objects. 
	 * @param factory The object factory.
	 */
	public void setObjectFactory(ObjectFactory factory){
		this.objectFactory = factory;
		this.createEarthAndSun();
	}
	
	/**
	 * Computes the apparent place of a star at the specified time, given its catalogue 
	 * mean place, proper motion, parallax, and radial velocity.
	 * This is the equivalent of the NOVAS-C function <code>app_star</code>.
	 * <br/>
	 * <b>Reference:</b> <br/>
	 * Kaplan, G. H. et. al. (1989). Astron. Journ. 97, 1197-1210.
	 * Explanatory Supplement to the Astronomical Almanac (1992),
	 * Chapter 3.
	 * @param time The time for which the apparent place needs to be calculated.
	 * @param cat_entry The catalog entry (star,...) for which the apparent place needs
	 * to be calculated.
	 * @param accuracy Code specifying the accuracy needed in the calculation of the 
	 * apparent place.
	 * @return The apparent celestial position of the star.
	 * @throws IllegalNOVASParameterValue If one of the input arguments is not permitted.
	 * @see Novas#NOVAS_FULL_ACCURACY
	 * @see Novas#NOVAS_REDUCED_ACCURACY
	 */
	public CelestialPosition getApparentPlaceOfAStar(Time time,CatalogueObject cat_entry,short accuracy) throws IllegalNOVASParameterValue{
		if(accuracy<0 || accuracy>1) throw new IllegalNOVASParameterValue("Accuracy should be either 0 (full accuracy) or 1 (reduced accuracy).");
		if(cat_entry.getNOVASObjectType()!=ObjectFactory.NOVAS_OBJECT_TYPE_EXTRA_SOLAR_SYSTEM_OBJECT){
			throw new IllegalNOVASParameterValue("NOVAS type should be 2 (extra solar system object) for cat_entry in getApparentPlaceOfAStar.");
		}
		if(cat_entry.getNOVASObjectNumber()!=0){
			throw new IllegalNOVASParameterValue("NOVAS number should be 0 (extra solar system object) for cat_entry in getApparentPlaceOfAStar.");
		}
		EarthBasedObservatory location = EarthBasedObservatory.getGeocentricObservatory(); 
		double delta_t = 0.0;
		short coordinateSystem = CelestialPosition.EQUATORIAL_COORDINATE_SYSTEM_TRUE_EQUATOR_AND_EQUINOX_OF_DATE;
		CelestialPosition pos = this.getApparentDirection(time, cat_entry, location, delta_t, coordinateSystem, accuracy);
		return pos;
	}

	/**
	 * This function computes the apparent direction of a star or solar 
	 * system body at a specified time and in a specified coordinate
	 * system. The co-ordinate system in which the position is to be expressed
	 * can only be the GCRS equatorial system, or the equatorial systems for the true 
	 * equator and equinox of date, wht the true equator and CIO of date. or
	 * the astrometric co-ordinates, i.e. without light deflection or aberration.
	 * Other co-ordinate systems such as the ecliptical or galactic co-ordinates are
	 * not allowed for this method, an <code>IllegalNOVASParameterValue</code> will
	 * then be thrown.
	 * <br/>
	 * The value returned by <code>location.getNOVASWhere()</code> and the co-ordinate
	 * system dictate the kind of place:
	 * <table>
	 * <tr><td><b>Type</b></td><td><b>Where</b></td><td><b>Co-ordinate system</b></td></tr>
	 * <tr><td>apparent place</td><td><code>GEOCENTRIC_OBSERVATORY</code></td><td><code>EQUATORIAL_COORDINATE_SYSTEM_TRUE_EQUATOR_AND_EQUINOX_OF_DATE</code></td></tr>
	 * <tr><td>topocentric place</td><td><code>TOPOCENTRIC_OBSERVATORY</code></td><td><code>EQUATORIAL_COORDINATE_SYSTEM_TRUE_EQUATOR_AND_EQUINOX_OF_DATE</code></td></tr>
	 * <tr><td>virtual place</td><td><code>GEOCENTRIC_OBSERVATORY</code></td><td><code>EQUATORIAL_COORDINATE_SYSTEM_GCRS</code></td></tr>
	 * <tr><td>local place</td><td><code>TOPOCENTRIC_OBSERVATORY</code></td><td><code>EQUATORIAL_COORDINATE_SYSTEM_GCRS</code></td></tr>
	 * <tr><td>astrometric place</td><td><code>GEOCENTRIC_OBSERVATORY</code></td><td><code>EQUATORIAL_COORDINATE_SYSTEM_ASTROMETRIC</code></td></tr>
	 * <tr><td>topocentric astrometric place</td><td><code>TOPOCENTRIC_OBSERVATORY</code></td><td><code>EQUATORIAL_COORDINATE_SYSTEM_ASTROMETRIC</code></td></tr>
	 * </table><br/>
	 * This is the equivalent of the NOVAS-C function <code>place</code>.
	 * <br/>
	 * <b>References:</b><br/>
	 * Kaplan, G. et al. (1989), Astronomical Journal 97, 1197-1210.<br/>
	 * Klioner, S. (2003), Astronomical Journal 125, 1580-1597.
	 * @param time The time for which the apparent place needs to be calculated.
	 * @param cel_object The celestial object whose position needs to be calculated.
	 * @param location The location of the observer.
	 * @param delta_t Difference TT-UT1 at the specified time.
	 * @param coordinateSystem The co-ordinate system of the output position.
	 * @param accuracy Code specifying the accuracy needed in the calculation of the 
	 * apparent place.
	 * @return The apparent direction of an object.
	 * @throws IllegalNOVASParameterValue If one of the input arguments is not permitted, for
	 * instance when the co-ordinate system is not allowed (see above).
	 * @see CelestialPosition#EQUATORIAL_COORDINATE_SYSTEM_GCRS
	 * @see CelestialPosition#EQUATORIAL_COORDINATE_SYSTEM_TRUE_EQUATOR_AND_EQUINOX_OF_DATE
	 * @see CelestialPosition#EQUATORIAL_COORDINATE_SYSTEM_TRUE_EQUATOR_CIO_OF_DATE
	 * @see CelestialPosition#EQUATORIAL_COORDINATE_SYSTEM_ASTROMETRIC
	 * @see Novas#NOVAS_FULL_ACCURACY
	 * @see Novas#NOVAS_REDUCED_ACCURACY
	 */
	public CelestialPosition getApparentDirection(Time time, CelestialObject cel_object, Observatory location, double delta_t,short coordinateSystem, short accuracy) throws IllegalNOVASParameterValue{
		/* Check for invalid values for the co-ordinate system of accuracy flags. */
		if(coordinateSystem<0 || coordinateSystem>3) throw new IllegalNOVASParameterValue("Parameter value for coordinate system should be between 0 and 3 for NOVAS method getApparentDirection.");
		if(accuracy<0 || accuracy>1) throw new IllegalNOVASParameterValue("Accuracy should be either 0 (full accuracy) or 1 (reduced accuracy).");
		/* Cannot calculate the direction of the Earth if the observer is on Earth. */
		if ((cel_object.getNOVASObjectType()==ObjectFactory.NOVAS_OBJECT_TYPE_MAJOR_SOLAR_SYSTEM_OBJECT) && (cel_object.getNOVASObjectNumber() == ObjectFactory.NOVAS_OBJECT_NUMBER_EARTH) && (location.getNOVASWhere() != Observatory.NEAR_EARTH_OBSERVATORY)){
			throw new IllegalNOVASParameterValue("Cannot calculate the apparent direction of the Earth if the observer's position is on the Earth.");
		}
		// Get TDB
		double jdTDB = time.getJulianDateTDB();
		//TODO Implement method
		return null;
	}
	
	/**
	 * Computes the difference (in seconds) between Terrestrial Time (TT) 
	 * or Terrestrial Dynamical Time (TDT) and Barycentric Dynamical Time
	 * (TDB). TT=TDB-secdif.
	 * <br/>
	 * This is method is based on the NOVAS-C function <code>tdb2tt</code>.
	 * It only returns the seconds difference.
	 * <br/>
	 * <b>References:</b><br/>
	 * Fairhead, L. & Bretagnon, P. (1990) Astron. & Astrophys. 229, 240.<br/>
	 * Kaplan, G. (2005), US Naval Observatory Circular 179.
	 * @param julianDateTT
	 * @return
	 */
	public double getTDBtoTTSecondDifference(double julianDateTDB){
		double t = (julianDateTDB - Novas.T0) / 36525.0;
		double secdiff = 0.001657 * Math.sin ( 628.3076 * t + 6.2401)
		            + 0.000022 * Math.sin ( 575.3385 * t + 4.2970)
		            + 0.000014 * Math.sin (1256.6152 * t + 6.1969)
		            + 0.000005 * Math.sin ( 606.9777 * t + 4.0212)
		            + 0.000005 * Math.sin (  52.9691 * t + 0.4444)
		            + 0.000002 * Math.sin (  21.3299 * t + 5.5431)
		            + 0.000010 * t * Math.sin ( 628.3076 * t + 4.2490);
		return secdiff;
	}
}
