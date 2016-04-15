package com.tspandroid;

public class Boss extends Enemy {

	boolean mini = false;	// Boolean value to determine whether a mini boss or stage boss

	public Boss(TSPGame game, int x, int y) {
		super(game, x, y, true);	// Uses Character constructor

		defText = Textures.DEFAULT;	// Overwrites player texture
		lives = 50;
		lastFacing = -1;
		isEnemy = true;
		width = 64;
		height = 64;

		switch (game.levelNum){
		case 1:
			if(!mini) {
				name = "Salae";
				lives = 13;
				defText = Textures.W1BOSS1;
			} else {
				lives = 13;
				defText = Textures.W1MBOSS1;
			}
			break;
		case 2:
			if(!mini) {
				name = "Keza";
				lives = 15;
				defText = Textures.W2BOSS2;
			} else {
				lives = 13;
				defText = Textures.W2MBOSS1;
			}
			break;
		case 3:
			if(!mini) {
				name = "Zefin";
				lives = 17;
				defText = Textures.W3BOSS;
			} else {
				lives = 13;
				defText = Textures.W3MBOSS;
			}
			break;
		case 4:
			if(!mini) {
				name = "Marble";
				lives = 19;
				defText = Textures.W4BOSS;
			} else {
				lives = 13;
				defText = Textures.W4MBOSS;
			}
			break;
		case 5:
			if(!mini) {
				name = "Sashen";
				lives = 17;
				defText = Textures.W5BOSS;
			} else {
				lives = 13;
				defText = Textures.W5MBOSS;
			}
			break;
		case 6:
			if(!mini) {
				name = "Blint";
				lives = 19;
				defText = Textures.W6BOSS;
			} else {
				lives = 13;
				defText = Textures.W6MBOSS;
			}
			break;
		case 7:
			if(!mini) {
				name = "Vivian";
				lives = 22;
				defText = Textures.W7BOSS;
			} else {
				lives = 13;
				defText = Textures.W7MBOSS;
			}
			break;
		case 8:
			if(!mini) {
				name = "TDK";
				lives = 25;
				defText = Textures.W8BOSS1;
			} else {
				lives = 13;
				defText = Textures.W8MBOSS;
			}
			break;
		}
	}
}
