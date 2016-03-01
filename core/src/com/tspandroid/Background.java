package com.tspandroid;

public class Background extends Character {
	public Background(TSPGame game, int x, int y) {
		super(game, x, y);	// Uses Character constructor
		
		defText = Textures.BG;	// Overwrites block texture
	}
}