package com.lapsedpacifist.astro.model.observatory;



public class SpaceBasedObservatory extends Observatory {
	
	@Override
	public short getNOVASWhere(){
		return Observatory.NEAR_EARTH_OBSERVATORY;
	}


}
