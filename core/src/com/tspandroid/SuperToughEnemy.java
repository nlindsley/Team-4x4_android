package com.tspandroid;

/**
 * Creates a super tough enemy for some of the levels.
 *	Does even more damage than challenging enemy.
 */

public class SuperToughEnemy extends Enemy{

	public SuperToughEnemy(TSPGame game, int x, int y) {
		super(game, x, y,true);
		lives = 10;
		
		if(this.xPatrol) 	{ this.lastFacing = 2; defText = Textures.STENEMY2; }
		else 				{ this.lastFacing = 3; defText = Textures.STENEMY3; }
	}
	
	/**
	 * Turns the enemy around when they run into a wall.
	 */
	public void turnEnemy() {
		if(isEnemy && xPatrol) { // if enemy walks into a block,
			x -= xVelocity;
			xVelocity = -xVelocity;	// turn him around
			if(xVelocity > 0) { this.lastFacing = 2; defText = Textures.STENEMY2; }
			if(xVelocity < 0) { this.lastFacing = 0; defText = Textures.STENEMY0; }
		} else if(isEnemy && !xPatrol) {	// if enemy is on y patrol pattern
			y -= yVelocity;
			yVelocity = -yVelocity;
			if(yVelocity > 0) { this.lastFacing = 3; defText = Textures.STENEMY3; }
			if(yVelocity < 0) { this.lastFacing = 1; defText = Textures.STENEMY1; }
		}
	}

}
