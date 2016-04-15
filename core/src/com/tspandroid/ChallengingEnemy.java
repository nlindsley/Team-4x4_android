package com.tspandroid;

/**
 * Creates a more challenging enemy for some of the rooms with fewer enemies.
 * Thought is to have them deal more damage as well, but not sure if possible.
 */

public class ChallengingEnemy extends Enemy {

	public ChallengingEnemy(TSPGame game, int x, int y) {
		super(game, x, y,true);
		lives = 7;
		
		if(this.xPatrol) 	{ this.lastFacing = 2; defText = Textures.CENEMY2; }
		else 				{ this.lastFacing = 3; defText = Textures.CENEMY3; }
	}
	
	/**
	 * Turns the enemy around when they run into a wall.
	 */
	public void turnEnemy() {
		if(isEnemy && xPatrol) { // if enemy walks into a block,
			x -= xVelocity;
			xVelocity = -xVelocity;	// turn him around
			if(xVelocity > 0) { this.lastFacing = 2; defText = Textures.CENEMY2; }
			if(xVelocity < 0) { this.lastFacing = 0; defText = Textures.CENEMY0; }
		} else if(isEnemy && !xPatrol) {	// if enemy is on y patrol pattern
			y -= yVelocity;
			yVelocity = -yVelocity;
			if(yVelocity > 0) { this.lastFacing = 3; defText = Textures.CENEMY3; }
			if(yVelocity < 0) { this.lastFacing = 1; defText = Textures.CENEMY1; }
		}
	}
}
