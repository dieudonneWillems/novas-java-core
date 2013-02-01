package com.lapsedpacifist.astro.novas.core.calculation;

import com.lapsedpacifist.astro.novas.core.CelestialPosition;
import com.lapsedpacifist.astro.novas.core.EarthBasedObservatory;
import com.lapsedpacifist.astro.novas.core.Observatory;
import com.lapsedpacifist.astro.novas.core.Time;
import com.lapsedpacifist.astro.novas.core.objects.CatalogueObject;
import com.lapsedpacifist.astro.novas.core.objects.CelestialObject;
import com.lapsedpacifist.astro.novas.core.objects.ObjectFactory;

public class Novas {

	public static short NOVAS_FULL_ACCURACY = 0;
	public static short NOVAS_REDUCED_ACCURACY = 0;
	
	private ObjectFactory objectFactory;
	
	public ObjectFactory getObjectFactory(){
		return objectFactory;
	}
	
	public void setObjectFactory(ObjectFactory factory){
		this.objectFactory = factory;
	}
	
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

	public CelestialPosition getApparentDirection(Time time, CelestialObject cel_object, Observatory location, double delta_t,short coordinateSystem, short accuracy) throws IllegalNOVASParameterValue{
		if(coordinateSystem<0 || coordinateSystem>3) throw new IllegalNOVASParameterValue("Parameter value for coordinate system should be between 0 and 3 for NOVAS method getApparentDirection.");
		if(accuracy<0 || accuracy>1) throw new IllegalNOVASParameterValue("Accuracy should be either 0 (full accuracy) or 1 (reduced accuracy).");
		//TODO Implement method
		return null;
	}
}
