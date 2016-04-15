package com.tspandroid;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

/** This class is the super class for Background, Block, Bullet, Enemy, Item, and Player and defines attributes, movement, and collision. */
public class Character {
	TSPGame game;	// reference to the "game" itself, allows for reference from any point
	String name;
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

	/**
	 * Constructor method for Character.
	 * @param game
	 * @param x coordinate the character is being spawned at
	 * @param y coordinate the character is being spawned at
	 */
	public Character(TSPGame game, int x, int y) {
		this.x = x;
		this.y = y;
		this.game = game;
	}

	/**
	 * Controls projectile speed on the X-axis
	 * @param v which the projectile is being set to
	 */
	void setXVelocity(double v) { xVelocity = v; }	// mainly for bullets

	/**
	 * Controls projectile speed on the Y-axis
	 * @param v which the projectile is being set to
	 */
	void setYVelocity(double v) { yVelocity = v; }	// mainly for bullets

	/** 
	 * Part of collision handling. Controls movement on the X-axis 
	 * @param amount amount that the character is moving.
	 */
	public void xMove(double amount) {
		x += amount;	// allows movement (left-right)

		for(Enemy e : game.enemies) {
			if(this.isCollidingWith(e) && !isBullet) {
				x -= amount;							// move out of invalid space
				this.knockback(this.lastFacing, 10, 1);	// knockback player and damage
			}
		}
		for(Block b : game.blockArr) {
			if(this.isCollidingWith(b)) {
				x -= amount;	// move back if touching a block
				return;
			}
		}
	}

	/** 
	 * Part of collision handling. Controls movement on the Y-axis 
	 * @param amount amount that the character is moving.
	 */
	public void yMove(double amount) {
		y += amount;	// allows movement (up-down)

		for(Enemy e : game.enemies) {
			if(this.isCollidingWith(e) && !isBullet) { 
				y -= amount;
				this.knockback(this.lastFacing, 10, 1);
			}
		}
		for(Block b : game.blockArr) {
			if(this.isCollidingWith(b)) {
				y -= amount;	// move back if touching a block
				return;
			}
		}
	}

	/** 
	 * Updates all character positions, life, and collisions. 
	 */
	public void update() {
		if(game.player.lives <= 0) {	// kill the player if 0 lives remain
			lives = 0;
			alive = false;
			game.player = null;
			//Gdx.app.exit();
			game.loadRoom("DeadState.txt");
			game.deadState = true;
		}
		
		x += xVelocity;
		y += yVelocity;

		for(Block b : game.blockArr) {		// for each block
			for(Enemy e : game.enemies) {
				if(e.isCollidingWith(b)) {	// if enemy touches it
					e.turnEnemy();			// turn enemy
				}
				if(e.isCollidingWith(game.player)) {	// if player touches enemy
					e.turnEnemy(); 						// turn enemy
					game.player.knockback(e.lastFacing, 15, 5);		// knockback player and damage
				}
				for(Enemy f: game.enemies) {	// for every other enemy
					if(e.isCollidingWith(f) && (!e.equals(f))) {
						e.turnEnemy();			// turn both enemies
						f.turnEnemy();
					}
				}
			}
			if(this.isCollidingWith(b) && isBullet) {	// if bullet touches it
				this.alive = false;						// kill bullet
				if(!b.unbreakable) {					// if block is breakable
					b.alive = false;					// kill block
				}
			}
		}
		
		for(Boss b : game.bosses) {
			// X movement
			if(b.x - game.player.x >= 10) { // move closer to player
				if(b.mini) { b.xMove(-1.5); }
				else { b.xMove(-2); }
			} else if(b.x - game.player.x <= -10){ // 'else-if' to prevent boss from twitching on approach
				if(b.mini) { b.xMove(1.5); }
				else { b.xMove(2); }
			}
			// Y movement
			if(b.y - game.player.y >= 10) { // move closer to player
				if(b.mini) { b.yMove(-1.5); }
				else { b.yMove(-2); }
			} else if(b.y - game.player.y <= -10){ // 'else-if' to prevent boss from twitching on approach
				if(b.mini) { b.yMove(1.5); }
				else { b.yMove(2); }
			}
			
			if(b.isCollidingWith(game.player)) {
				game.player.lives -= 1;
			}
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

	/**
	 * Class that moves the character back a given distance if no obstacle is in the way
	 * @params Integer the distance to be knocked backward
	 */
	public void knockback(int direction, int amount, int damage) {

		
		boolean noBlock = false;
		boolean noEnemy = false;
		Rectangle thisCharacter = new Rectangle((int)this.x, (int)this.y, this.width, this.height);
		Rectangle otherCharacter;

		// check to see if character will get knocked back into a block
		for(Block b : game.blockArr) {
			otherCharacter = new Rectangle((int)b.x, (int)b.y, b.width+20, b.height+20);
			
			if(thisCharacter.overlaps(otherCharacter)) { 
				noBlock = false; 
				break;
			} else { noBlock = true; }
		}

		// check to see if character will get knocked back into an enemy
		for(Enemy e : game.enemies) {
			if(this.equals(e)) { continue; }
			otherCharacter = new Rectangle((int)e.x, (int)e.y, e.width, e.height);
			
			if(thisCharacter.overlaps(otherCharacter)) { 
				noEnemy = false; 
				break;
			} else { noEnemy = true; }
		}
		
		if(noBlock && noEnemy) {
			if(direction == 0) { this.x += amount;	this.lives -= damage; }
			if(direction == 1) { this.y += amount;	this.lives -= damage; }
			if(direction == 2) { this.x -= amount;	this.lives -= damage; }
			if(direction == 3) { this.y -= amount;	this.lives -= damage; }
		}
		else{
			this.lives -= damage;
		}
	}
	
	/**
	 * This class checks if two characters are colliding and returns a boolean value.
	 * @param other unit possibly being collided with.
	 * @return True if they have collided; false otherwise.
	 */
	boolean isCollidingWith(Character other) {
		// Create a bounding rectangle over each character
		Rectangle thisCharacter = new Rectangle((int)x, (int)y, width, height);
		Rectangle otherCharacter = new Rectangle((int)other.x, (int)other.y, other.width, other.height);

		return thisCharacter.overlaps(otherCharacter);
	}

	/** 
	 * Draw all the textures. 
	 * @param batch batch used to draw.
	 */
	public void draw(SpriteBatch batch) {
		batch.draw(defText, (int)x, (int)y);
	}
}
