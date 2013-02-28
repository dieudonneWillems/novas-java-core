package com.lapsedpacifist.astro.novas.core;

/**
 * This class models the position and velocity vectors of a body.
 * @author don_willems
 *
 */
public class PositionAndVelocityVector {
	
	/** The x component of the position vector */
	public double x;
	/** The y component of the position vector */
	public double y;
	/** The z component of the position vector */
	public double z;
	/** The x component of the velocity vector */
	public double vx;
	/** The y component of the velocity vector */
	public double vy;
	/** The z component of the velocity vector */
	public double vz;
	
	/**
	 * Returns the x component of the position vector;
	 * @return x
	 */
	public double getX(){
		return x;
	}
	
	/**
	 * Returns the y component of the position vector;
	 * @return y
	 */
	public double getY(){
		return y;
	}
	
	/**
	 * Returns the z component of the position vector;
	 * @return z
	 */
	public double getZ(){
		return z;
	}
	
	/**
	 * Returns the x component of the velocity vector;
	 * @return vx
	 */
	public double getVx(){
		return vx;
	}
	
	/**
	 * Returns the y component of the velocity vector;
	 * @return vy
	 */
	public double getVy(){
		return vy;
	}
	
	/**
	 * Returns the z component of the velocity vector;
	 * @return vz
	 */
	public double getVz(){
		return vz;
	}
}
