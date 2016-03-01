package com.tspandroid;

public class Enemy extends Character {
	public Enemy(TSPGame game, int x, int y) {
		super(game, x, y);	// Uses Character constructor
		
		defText = Textures.DEFAULT;	// Overwrites player texture
		lives = 5;
	}
}
