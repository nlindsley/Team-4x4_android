package com.tspandroid;

import java.lang.*;

/** Class to control enemy characters. */
public class Enemy extends Character {
	public Enemy(TSPGame game, int x, int y, boolean xPatrol) {
		super(game, x, y);	// Uses Character constructor
		
		defText = Textures.DEFAULT;	// Overwrites player texture
		lives = 1;
		lastFacing = -1;
		isEnemy = true;
		this.xPatrol = xPatrol;
		width = 57;
		height = 62;
		
		if(this.xPatrol) 	{ this.lastFacing = 2; defText = Textures.ENEMY2; }
		else 				{ this.lastFacing = 3; defText = Textures.ENEMY3; }
	}

	/**
	 * Turns the enemy around when they run into a wall.
	 */
	public void turnEnemy() {
		if(isEnemy && xPatrol) { // if enemy walks into a block,
			x -= xVelocity;
			xVelocity = -xVelocity;	// turn him around
			if(xVelocity > 0) { this.lastFacing = 2; defText = Textures.ENEMY2; }
			if(xVelocity < 0) { this.lastFacing = 0; defText = Textures.ENEMY0; }
		} else if(isEnemy && !xPatrol) {	// if enemy is on y patrol pattern
			y -= yVelocity;
			yVelocity = -yVelocity;
			if(yVelocity > 0) { this.lastFacing = 3; defText = Textures.ENEMY3; }
			if(yVelocity < 0) { this.lastFacing = 1; defText = Textures.ENEMY1; }
		}
	}
	
	public void dropItem() {
		int chance = ((int)Math.round(Math.random()/5)*100);
		
		//System.out.println("Drop Chance = " + chance);
		if(chance > 70) {
			game.items.add(new Item(game, (int)x, (int)y));
		}
	}
}
