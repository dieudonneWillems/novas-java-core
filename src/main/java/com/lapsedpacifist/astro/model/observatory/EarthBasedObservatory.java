package com.lapsedpacifist.astro.model.observatory;

import com.lapsedpacifist.astro.model.position.GeographicLocation;



public class EarthBasedObservatory extends Observatory {

	private GeographicLocation location;
	private double temperature;
	private double pressure;
	private short where = Observatory.GEOCENTRIC_OBSERVATORY;
	private static EarthBasedObservatory geocentricObservatory = new EarthBasedObservatory();

	public static EarthBasedObservatory getGeocentricObservatory() {
		return geocentricObservatory;
	}
	
	// For geocentric observatory
	private EarthBasedObservatory(){
	}
	
	public GeographicLocation getGeographicLocation(){
		return location;
	}
	
	public double getTemperature(){
		return temperature;
	}
	
	public double getPressure(){
		return pressure;
	}
	
	@Override
	public short getNOVASWhere(){
		return where;
	}

}
