package com.lapsedpacifist.astro.novas.core.objects;

import com.lapsedpacifist.astro.novas.core.Catalogue;
import com.lapsedpacifist.astro.novas.core.Position;
import com.lapsedpacifist.astro.novas.core.ProperMotion;

public interface CatalogueObject extends CelestialObject {
	 
	public Catalogue getCatalogue();
	public long getNumberInCatalogue();
	public Position getICRSPosition();
	public ProperMotion getICRSProperMotion();
	public double getParallax();
	public double getRadialVelocity();
}
