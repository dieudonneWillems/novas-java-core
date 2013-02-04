package com.lapsedpacifist.astro.model.objects;

import com.lapsedpacifist.astro.model.Catalogue;
import com.lapsedpacifist.astro.model.position.Position;
import com.lapsedpacifist.astro.model.position.ProperMotion;

public interface CatalogueObject extends CelestialObject {
	 
	public Catalogue getCatalogue();
	public long getNumberInCatalogue();
	public Position getICRSPosition();
	public ProperMotion getICRSProperMotion();
	public double getParallax();
	public double getRadialVelocity();
}
