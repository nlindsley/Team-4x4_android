package com.tspandroid;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.ArrayList;
import java.util.List;

/** 
 * Team 4x4, CS3141-Team Software Project, Spring 2016, Video Game
 * 
 * @author Alexander Friebe
 * @author Charles Heckel
 * @author Nick Lindsley
 * @author Ben McWethy
 */
public class TSPGame extends ApplicationAdapter {
	Camera			camera;
	Viewport		viewport;
	SpriteBatch 	batch;
	Player			player;
	Enemy			enemy;
	List<Bullet>	bullets		= new ArrayList<Bullet>(); 	// of type character because they have the same traits
	List<Block>		blockArr	= new ArrayList<Block>();	// an array list allows for multiple on screen
	List<Background>bgArr		= new ArrayList<Background>();
	List<Item>		items		= new ArrayList<Item>();	// list of active items
	String[][]		world		= new String[8][8];
	Listener		keyBoardListener;
	int ammo = 10;
	BitmapFont font;	// pre-made font for libgdx
	int screenHeight;
	int screenWidth;

	/** loads the level based on a file input of 0's (air), 1's (blocks), and x's(spawns). */
	public void loadLevel() {
		int lineNum = 0;
		FileHandle fileHandle;
		try {
			// Reads level file into string
			fileHandle = Gdx.files.internal("levels/level00.txt");
			String fileContents = fileHandle.readString();

			String[] lines = fileContents.split("\n");
			int blockHeight = 0;	// file is read in line-by-line, so we'll use a simple counter for height
			// level will be upside-down from txt file
			while(!lines[lineNum].isEmpty()) {
				String[] levelGrid = lines[lineNum].split(" ");	// puts everything in-between white-spaces into an array spot

				for(int i = 0; i < levelGrid.length; i += 1) {
					Background grass = new Background(this, i*32, blockHeight*32);
					bgArr.add(grass);
					if(levelGrid[i].equals("1")) {
						Block block = new Block(this, i*32, blockHeight*32);
						blockArr.add(block);
					}
					if(levelGrid[i].equals("p")) {
						player = new Player(this,i*32,blockHeight*32);
					}
					if(levelGrid[i].equals("e")) {
						enemy = new Enemy(this,i*32,blockHeight*32);
					}
					if(levelGrid[i].equals("i")) {
						items.add(new Item(this, i*32,blockHeight*32));
					}
				}
				blockHeight += 1;
				//screenWidth = levelGrid.length;

				lineNum++;
			}
			//screenHeight = blockHeight;
		} catch (Exception e) {
			System.out.println("CUSTOM ERROR: NEEDS A LEVEL FILE");
			e.printStackTrace();
		}
	}

	/** Initialize all variables when game starts. */
	@Override
	public void create () {
		screenHeight = Gdx.graphics.getHeight();
		screenWidth = Gdx.graphics.getWidth();
		loadLevel();

		batch	= new SpriteBatch();
		font	= new BitmapFont();				// default 15pt arial from libgdx JAR file
		keyBoardListener = new Listener();
		Gdx.input.setInputProcessor(keyBoardListener);
	}

	/** gets called hundreds of times per second. Similar to tick or frames. */
	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);	// r,g,b,alpha (values: 0-1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		player.update();
		enemy.update();

		// Player Management
		if(player.alive) {
			if(keyBoardListener.keysPressed[Keys.LEFT])		{ player.xMove(-5);	player.lastFacing = 0; player.defText = Textures.PLAYER0; }
			if(keyBoardListener.keysPressed[Keys.RIGHT])	{ player.xMove(5);	player.lastFacing = 2; player.defText = Textures.PLAYER2; }
			if(keyBoardListener.keysPressed[Keys.UP])		{ player.yMove(5);	player.lastFacing = 3; player.defText = Textures.PLAYER3; }
			if(keyBoardListener.keysPressed[Keys.DOWN])		{ player.yMove(-5);	player.lastFacing = 1; player.defText = Textures.PLAYER1; }
			if(keyBoardListener.keysPressed[Keys.Z])		{
				keyBoardListener.keysPressed[Keys.Z] = false;	// fires once per press
				if(ammo > 0) {
					ammo -= 1;
					Bullet bullet = new Bullet(this,(int)player.x, (int)player.y+8);
					if(keyBoardListener.keysPressed[Keys.UP]	||	player.lastFacing == 3)		{ bullet.setYVelocity(15); }
					if(keyBoardListener.keysPressed[Keys.DOWN]	||	player.lastFacing == 1) 	{ bullet.setYVelocity(-15); }
					if(keyBoardListener.keysPressed[Keys.LEFT]	||	player.lastFacing == 0) 	{ bullet.setXVelocity(-15); }
					if(keyBoardListener.keysPressed[Keys.RIGHT]	||	player.lastFacing == 2) 	{ bullet.setXVelocity(15); }
					bullet.isBullet = true;
					bullets.add(bullet);
				}
			}
		}
		
		// Everything that is drawn to the screen should be between ".begin" and ".end"
		batch.begin();

		for(Background c : bgArr) {	c.draw(batch); }
									player.draw(batch);
									enemy.draw(batch);
		for(Bullet c : bullets) {	c.draw(batch); }
		for(Block c : blockArr) {	c.draw(batch); }
		for(Item c : items) {		c.draw(batch); }

		// bullet management
		for(int i = 0; i < bullets.size(); i += 1) {
			bullets.get(i).update();

			if(bullets.get(i).isCollidingWith(enemy)) {
				enemy.lives -= 1;
				bullets.get(i).alive = false;
			}
			if(!bullets.get(i).alive) {	// ... check if 'dead'
				bullets.remove(i);		// ... remove if 'dead'
				i -= 1;					// prevents the loop from skipping the next bullet
			}
		}
		// block management
		for(int i = 0; i < blockArr.size(); i += 1) {	// for each bullet
			if(!blockArr.get(i).alive) {	// ... check if 'dead'
				blockArr.remove(i);			// ... remove if 'dead'
				i -= 1;						// prevents the loop from skipping the next bullet
			}
		}
		// item management
		for(int i = 0; i < items.size(); i += 1) {
			if(!items.get(i).alive) {
				items.remove(i);
				i -= 1;
			}
		}

		// HUD management
		batch.draw(Textures.HUD,  0, screenHeight-64);	// must go last, has to display over everything else
		font.draw(batch,  "Your lives: " + player.lives,  10, screenHeight-10);
		batch.draw(Textures.HUD, screenWidth-128, screenHeight-64);	// must go last, has to display over everything else
		font.draw(batch,  "Enemy lives: " + enemy.lives,  screenWidth-118, screenHeight-10);
		for(int i = 0; i < ammo; i += 1) {
			batch.draw(Textures.BULLET,  i*7,  screenHeight-64);
		}

		batch.end();
		// Everything that is drawn to the screen should be between ".begin" and ".end"
	}
}
