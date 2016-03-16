package com.tspandroid;

import com.badlogic.gdx.graphics.Texture;

/**
 * Class that creates the textures for the game.
 * 
 * @author Charles Heckel
 */
public class Textures {
	// image should be size 2^n
	static Texture DEFAULT	= new Texture("default.png");
	static Texture BLOCK	= new Texture("block.png");
	static Texture PLAYER0	= new Texture("knightleft.png");
	static Texture PLAYER1	= new Texture("knightfront.png");
	static Texture PLAYER2	= new Texture("knightright.png");
	static Texture PLAYER3	= new Texture("knightback.png");
	static Texture BULLET	= new Texture("bullet.png");
	static Texture ENEMY0	= new Texture("banditfront.png");
	static Texture ENEMY1	= new Texture("banditfront.png");
	static Texture ENEMY2	= new Texture("banditback.png");
	static Texture ENEMY3	= new Texture("banditback.png");
	static Texture HUD		= new Texture("overlay.png");
	static Texture AMMO		= new Texture("ammo.png");
	static Texture BG		= new Texture("Grass.png");
}
