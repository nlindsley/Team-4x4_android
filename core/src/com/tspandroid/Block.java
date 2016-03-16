package com.tspandroid;

public class Block extends Character {
	public boolean unbreakable = false;

	public Block(TSPGame game, int x, int y) {
		super(game, x, y);	// Uses Character constructor
		
		defText = Textures.BLOCK;	// Overwrites block texture
		width = 32;
		height = 32;
	}
}
