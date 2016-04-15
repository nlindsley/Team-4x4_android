package com.tspandroid;

import java.lang.*;

/**
 * This class creates the background (i.e. the floor) for the game.
 *
 */

public class Background extends Character {
	public Background(TSPGame game, int x, int y, int world) {
		super(game, x, y);	// Uses Character constructor
		
		// Overwrites block texture
		switch(world){
		case 1:
			defText = Textures.W1FLO;
			break;
		case 2:
			defText = Textures.W2FLO;
			break;
		case 3:
			defText = Textures.W3FLO;
			break;
		case 4:
			defText = Textures.W4FLO;
			break;
		case 5:
			defText = Textures.W5FLO;
			break;
		case 6:
			defText = Textures.W6FLO;
			break;
		case 7:
			defText = Textures.W7FLO;
			break;
		case 8:
			defText = Textures.W8FLO;
			break;
		}
	}
}