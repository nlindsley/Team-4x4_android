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
	boolean isEnemy;
	boolean xPatrol;
	int width;
	int height;
	int lives;
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

		for(Enemy e : game.enemies) {
			if(this.isCollidingWith(e) && !isBullet) {
				x -= amount;
				game.keyBoardListener.keysPressed[Keys.LEFT] = false;
				game.keyBoardListener.keysPressed[Keys.RIGHT] = false;
			}
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

		for(Enemy e : game.enemies) {
			if(this.isCollidingWith(e) && !isBullet) { 
				y -= amount;
				game.keyBoardListener.keysPressed[Keys.UP] = false;
				game.keyBoardListener.keysPressed[Keys.DOWN] = false;
			}
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
			y = -64;
		}
		
		x += xVelocity;
		y += yVelocity;

		for(Enemy e : game.enemies) {
			for(int i = 0; i < game.blockArr.size(); i += 1) {
				if(game.blockArr.get(i).isCollidingWith(this)) {
					// removes the bullet when it hits a block
					if(isBullet) {
						alive = false;
						if(!game.blockArr.get(i).unbreakable) {
							game.blockArr.get(i).alive = false;
						}
					} else {
						e.turnEnemy();
					}
				}
			}
			if(game.player.isCollidingWith(e)) { e.turnEnemy(); game.player.lives -= 5;}
		}
		
		
		if(isPlayer) {	// handles room transitions
			if(y <= 0) {
				game.loadRoom(game.rooms[game.player.currentRoomX][++game.player.currentRoomY]);
				y = 480;	// 512 - 32
			}
			if(y >= 512) {
				game.loadRoom(game.rooms[game.player.currentRoomX][--game.player.currentRoomY]);
				y = 32;
			}
			if(x <= 0) {
				game.loadRoom(game.rooms[--game.player.currentRoomX][game.player.currentRoomY]);
				x = 480;	// 512 - 32
			}
			if(x >= 512) {
				game.loadRoom(game.rooms[++game.player.currentRoomX][game.player.currentRoomY]);
				x = 32;
			}
		}

		for(int i = 0; i < game.items.size(); i += 1) {
			if(game.player.isCollidingWith(game.items.get(i))) {
				if(game.ammo == 14) {
					continue;
				} else if(game.ammo+5 > 14) {	// prevent break ammo capacity
					game.ammo = 14;
				} else if( game.ammo+5 <= 14) {
					game.ammo += 5;
				}
				game.items.get(i).alive = false;
			}
		}
	}

	boolean isCollidingWith(Character other) {
		// Create a bounding rectangle over each character
		Rectangle thisCharacter = new Rectangle((int)x, (int)y, width, height);
		Rectangle otherCharacter = new Rectangle((int)other.x, (int)other.y, other.width, other.height);

		return thisCharacter.overlaps(otherCharacter);
	}

	/** draw all the textures. */
	public void draw(SpriteBatch batch) {
		batch.draw(defText, (int)x, (int)y);
		
	}
}
