package com.tspandroid;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Character {
	TSPGame game;	// reference to the "game" itself, allows for reference from any point
	double x;
	double y;
	double xVelocity;
	double yVelocity;
	boolean alive = true;
	boolean isBullet;
	boolean isPlayer;
	int lives = 3;
	int lastFacing; // 0 is left, 1 is down, 2 is right, 3 is up
	Texture defText = Textures.DEFAULT;

	public Character(TSPGame game, int x, int y) {
		this.x = x;
		this.y = y;
		this.game = game;
	}

	void setXVelocity(double v) { xVelocity = v; }	// mainly for bullets
	void setYVelocity(double v) { yVelocity = v; }	// mainly for bullets

	/** Part of collision handling. */
	public void xMove(int amount) {
		x += amount;	// allows movement (left-right)

		if(this.isCollidingWith(game.enemy) && !isBullet) {
			x -= 5*amount;
			game.keyBoardListener.keysPressed[Keys.LEFT] = false;
			game.keyBoardListener.keysPressed[Keys.RIGHT] = false;
		}
		for(int i = 0; i < game.blockArr.size(); i += 1) {
			if(this.isCollidingWith(game.blockArr.get(i))) {
				x -= amount;	// move back if touching a block
				return;
			}
		}
	}
	
	/** Part of collision handling. */
	public void yMove(int amount) {
		y += amount;	// allows movement (up-down)

		if(this.isCollidingWith(game.enemy) && !isBullet) { 
			y -= 5*amount;
			game.keyBoardListener.keysPressed[Keys.UP] = false;
			game.keyBoardListener.keysPressed[Keys.DOWN] = false;
		}
		for(int i = 0; i < game.blockArr.size(); i += 1) {
			if(this.isCollidingWith(game.blockArr.get(i))) {
				y -= amount;	// move back if touching a block
				return;
			}
		}
	}

	/** Update all character positions, life, and collisions. */
	public void update() {
		if(lives < 1 && !isBullet) {	// kill the player if 0 lives remain
			lives = 0;
			alive = false;
			x = -64;
			y = 64;
		}
		
		x += xVelocity;
		y += yVelocity;

		for(int i = 0; i < game.blockArr.size(); i += 1) {
			if(game.blockArr.get(i).isCollidingWith(this)) {
				// removes the bullet when it hits a block
				if(isBullet) {
					alive = false;
					game.blockArr.get(i).alive = false;
				}
			}
		}
		if(y <= 0 && (!isBullet)) {	// stops us from walking off the map, prevents bullets from respawning
			lives -= 1;
			x = 100;	// move the player back on screen
			y = 200;
		}

		for(int i = 0; i < game.items.size(); i += 1) {
			if(this.isCollidingWith(game.items.get(i)) && !isBullet) {
				if(game.ammo+5 > 14) {	// prevent break ammo capacity
					game.ammo = 14;
				} else {
					game.ammo += 5;
				}
				game.items.get(i).alive = false;
			}
		}
	}

	boolean isCollidingWith(Character other) {
		// Create a bounding rectangle over each character
		Rectangle thisCharacter = new Rectangle((int)x, (int)y, 57, 62);
		Rectangle otherCharacter = new Rectangle((int)other.x, (int)other.y, 32, 32);

		return thisCharacter.overlaps(otherCharacter);
	}

	/** draw all the textures. */
	public void draw(SpriteBatch batch) {
		batch.draw(defText, (int)x, (int)y);
		
	}
}
