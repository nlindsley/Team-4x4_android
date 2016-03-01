package com.tspandroid;

public class Player extends Character {
	public Player(TSPGame game, int x, int y) {
		super(game, x, y);	// Uses Character constructor

		defText = Textures.PLAYER1;	// Overwrites player texture
		lastFacing = 1;
		isPlayer = true;
	}
}
