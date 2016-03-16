package com.tspandroid;

public class Enemy extends Character {
	public Enemy(TSPGame game, int x, int y) {
		super(game, x, y);	// Uses Character constructor
		
		defText = Textures.DEFAULT;	// Overwrites player texture
		lives = 3;
		lastFacing = -1;
		isEnemy = true;
		xPatrol = true;
		width = 57;
		height = 62;
		
		if(this.xPatrol) 	{ this.lastFacing = 2; defText = Textures.ENEMY2; }
		else 				{ this.lastFacing = 3; defText = Textures.ENEMY3; }
	}
	
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
}
