package com.tspandroid;

public class Player extends Character {
	public Player(TSPGame game, int x, int y) {
		super(game, x, y);	// Uses Character constructor

		defText = Textures.PLAYER1;	// Overwrites player texture
		lives = 30;
		lastFacing = 1;
		isPlayer = true;
		width = 57;
		height = 60;
	}
	
	int currentRoomX;
	int currentRoomY;
}
