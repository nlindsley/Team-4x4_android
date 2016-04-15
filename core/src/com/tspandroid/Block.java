package com.tspandroid;

import java.lang.*;

/** This class creates the blocks used as barriers for the edges, as well as used to create puzzles. */
public class Block extends Character {

	public boolean unbreakable = false;	// Boolean check to ensure that the player can't walk off the edge of the map.

	public Block(TSPGame game, int x, int y, int world) {
		super(game, x, y);	// Uses Character constructor
		
		width = 32;
		height = 32;
		
		// Overwrites block texture
		switch(world){
		case 1:
			defText = Textures.GBLOCK;
			break;
		case 2:
			defText = Textures.W2BAR;	// TEMPORARY
			break;
		case 3:
			defText = Textures.W3BAR;	// TEMPORARY
			break;
		case 4:
			defText = Textures.W4BAR;	// TEMPORARY
			break;
		case 5:
			defText = Textures.W5BAR;	// TEMPORARY
			break;
		case 6:
			defText = Textures.W6BAR;	// TEMPORARY
			break;
		case 7:
			defText = Textures.W7BAR;	// TEMPORARY
			break;
		case 8:
			defText = Textures.W8BAR;	// TEMPORARY
			break;
		}
	}
}