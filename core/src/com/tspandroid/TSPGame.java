package com.tspandroid;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
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
	Stage 			stage;
	SpriteBatch 	batch;
	Player			player;
	String[]		levels;
	String[][]		rooms		= new String[8][8];			// contains all rooms for the level
	List<Boss>		bosses		= new ArrayList<Boss>();
	List<Enemy>		enemies		= new ArrayList<Enemy>();
	List<Bullet>	bullets		= new ArrayList<Bullet>(); 		// of type character because they have the same traits
	List<Block>		blockArr	= new ArrayList<Block>();		// an array list allows for multiple on screen
	List<Background>bgArr		= new ArrayList<Background>();
	List<Item>		items		= new ArrayList<Item>();		// list of active items
	Listener		keyBoardListener;
	TouchListener 	touchListener;
	BitmapFont font;	// pre-made font for libgdx

	private State state = State.RUN;

	private int enemiesKilled = 0;

	boolean deadState = false;
	boolean miniKilled = false;
	boolean bossKilled = false;

	int[] startRoom = new int[2];
	int levelNum = 1;
	int ammo = 10;

	final int gameHeight = 512;
	final int gameWidth = 512;
	int screenHeight;
	int screenWidth;

	/** Initialize all variables when game starts. */
	@Override
	public void create () {
		// Scales the screen with black bars
		screenHeight = Gdx.graphics.getHeight();
		screenWidth = Gdx.graphics.getWidth();
		camera = new OrthographicCamera();
		viewport = new StretchViewport(gameWidth, gameHeight, camera);
		viewport.apply();
		camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);


		// Loads level and room
		levels = new String[] {"", "level1maps", "level2maps", "level3maps", "level4maps", "level5maps", "level6maps", "level7maps", "level8maps"};
		loadLevel("level1maps/level1.txt");

		player.isKnight = true;


		batch	= new SpriteBatch();
		font	= new BitmapFont();				// default 15pt arial from libgdx JAR file
		keyBoardListener = new Listener();
		touchListener = new TouchListener(gameHeight, gameWidth, screenHeight, screenWidth);
		Gdx.input.setInputProcessor(touchListener);


		// Creates overlay for UI
		loadUI();
	}

	/** Creates UI buttons and draws them over the screen. */
	public void loadUI() {
		Skin skin = new Skin(Gdx.files.internal("UI/uiskin.json"));
		stage = new Stage(viewport);

		// Create buttons
		final TextButton buttonUp = new TextButton("Up", skin, "default");
		final TextButton buttonDown = new TextButton("Down", skin, "default");
		final TextButton buttonLeft = new TextButton("Left", skin, "default");
		final TextButton buttonRight = new TextButton("Right", skin, "default");
		final TextButton buttonA = new TextButton("A", skin, "default");
		final TextButton buttonB = new TextButton("B", skin, "default");
		final TextButton buttonPause = new TextButton("Pause", skin, "default");

		// Position buttons
		buttonUp.setWidth(50);
		buttonUp.setHeight(50);
		buttonUp.setPosition(50, 100);

		buttonDown.setWidth(50);
		buttonDown.setHeight(50);
		buttonDown.setPosition(50, 0);

		buttonLeft.setWidth(50);
		buttonLeft.setHeight(50);
		buttonLeft.setPosition(0, 50);

		buttonRight.setWidth(50);
		buttonRight.setHeight(50);
		buttonRight.setPosition(100, 50);

		buttonA.setWidth(50);
		buttonA.setHeight(50);
		buttonA.setPosition(387, 50);

		buttonB.setWidth(50);
		buttonB.setHeight(50);
		buttonB.setPosition(462, 50);

		buttonPause.setWidth(50);
		buttonPause.setHeight(50);
		buttonPause.setPosition(231, 0);

		// Add buttons to stage to be drawn
		stage.addActor(buttonUp);
		stage.addActor(buttonDown);
		stage.addActor(buttonLeft);
		stage.addActor(buttonRight);
		stage.addActor(buttonA);
		stage.addActor(buttonB);
		stage.addActor(buttonPause);

		Gdx.input.setInputProcessor(touchListener);
	}

	/** loads the level based on a file input of 0's (air), 1's (blocks), and x's(spawns). */
	public void loadLevel(String levelx) {
		miniKilled = false;
		bossKilled = false;

		int lineNum = 0;
		FileHandle fileHandle;
		try {
			// Reads level file into string
			fileHandle = Gdx.files.internal("levels/"+levelx);
			String fileContents = fileHandle.readString();

			String[] lines = fileContents.split("\n");
			int j = 0;	// file is read in line-by-line, so we'll use a simple counter for height
			// level will be upside-down from txt file
			while(!lines[lineNum].isEmpty()) {
				String[] levelGrid = lines[lineNum].split(" ");	// puts everything in-between white-spaces into an array spot

				for(int i = 0; i < levelGrid.length; i += 1) {
					if(levelGrid[i].equals("0")) { continue; }
					else {
						rooms[i][j] = levelGrid[i];
					}
					if(rooms[i][j].contains("r1.txt")) {
						startRoom[0] = i;
						startRoom[1] = j;
					}
				}
				j += 1;

				lineNum++;
			}
		} catch (Exception e) {
			System.out.println("CUSTOM ERROR: NEEDS A LEVEL FILE");
			e.printStackTrace();
		}

		loadRoom(levels[levelNum] + "/l" + levelNum + "r1.txt");
	}

	/** loads the room based on a file input. */
	public void loadRoom(String lxrx) {
		int lineNum = 0;
		FileHandle fileHandle;
		try {
			fileHandle = Gdx.files.internal("levels/"+lxrx);
			String fileContents = fileHandle.readString();
			int blockHeight = 0;	// file is read in line-by-line, so we'll use a simple counter for height
			// level will be upside-down from txt file

			// remove everything from the previous level before adding next elements
			for(int i = 0; i < bgArr.size(); i += 1)	{ bgArr.remove(i);		i -= 1;	}
			for(int i = 0; i < bullets.size(); i += 1)	{ bullets.remove(i);	i -= 1;	}
			for(int i = 0; i < blockArr.size(); i += 1)	{ blockArr.remove(i);	i -= 1;	}
			for(int i = 0; i < enemies.size(); i += 1)	{ enemies.remove(i);	i -= 1;	}
			for(int i = 0; i < items.size(); i += 1)	{ items.remove(i);		i -= 1;	}
			for(int i = 0; i < bosses.size(); i += 1)	{ bosses.remove(i);		i -= 1; }

			String[] lines = fileContents.split("\n");
			while(!lines[lineNum].isEmpty()) {
				String[] levelGrid = lines[lineNum].split(" ");	// puts everything in-between white-spaces into an array spot

				for(int i = 0; i < levelGrid.length; i += 1) {
					Background grass = new Background(this, i*32, blockHeight*32, levelNum);
					bgArr.add(grass);
					if(levelGrid[i].equals("1")) {
						Block block = new Block(this, i*32, blockHeight*32, levelNum);
						if(i == 0 || i == levelGrid.length-1 || blockHeight == 0 || blockHeight == levelGrid.length-1) {
							block.unbreakable = true;
						}
						blockArr.add(block);
					}
					if(levelGrid[i].equals("p")) {
						if(player == null) {
							player = new Player(this, i * 32, blockHeight * 32);
						}

						player.currentRoomX = startRoom[0];
						player.currentRoomY = startRoom[1];
					}
					if(levelGrid[i].equals("e")) {
						enemies.add(0, new Enemy(this,i*32,blockHeight*32, true));
						enemies.get(0).setXVelocity(0);
						enemies.get(0).defText = Textures.ENEMY1;
					}
					if(levelGrid[i].equals("x")) {
						enemies.add(0, new Enemy(this, i * 32, blockHeight * 32, true));
						enemies.get(0).setXVelocity(1);
						//enemies.get(0).xPatrol = true;
					}
					if(levelGrid[i].equals("y")) {
						enemies.add(0, new Enemy(this, i * 32, blockHeight * 32, false));
						enemies.get(0).setYVelocity(1);
						//enemies.get(0).xPatrol = false;
					}
					if(levelGrid[i].equals("mb")) {
						if(miniKilled) { continue; }
						bosses.add(0, new Boss(this,i*32,blockHeight*32));
						bosses.get(0).mini = true;
					}
					if(levelGrid[i].equals("b")) {
						if(bossKilled) { continue; }
						bosses.add(0, new Boss(this,i*32,blockHeight*32));
						bosses.get(0).mini = false;
					}
					if(levelGrid[i].equals("i")) {
						//items.add(new Item(this, i*32,blockHeight*32));
					}
				}
				blockHeight += 1;
				//screenWidth = levelGrid.length;
				lineNum++;
			}
			//screenHeight = blockHeight;
		} catch (Exception e) {
			System.out.println("CUSTOM ERROR: NEEDS A ROOM FILE");
		}
	}

	/** gets called hundreds of times per second. Similar to tick or frames. */
	@Override
	public void render () {
		switch (state) {
			case RUN:

				Gdx.gl.glClearColor(0, 0, 0, 1);    // r,g,b,alpha (values: 0-1);
				Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
				camera.update();
				player.update();
				for (Enemy e : enemies) {
					e.update();
				}

				// Player Management
				if (player.alive) {
					if (touchListener.buttonPressed[touchListener.LEFT]) {
						player.xMove(-5);
						player.lastFacing = 0;
						player.defText = Textures.PLAYER0;
					}
					if (touchListener.buttonPressed[touchListener.RIGHT]) {
						player.xMove(5);
						player.lastFacing = 2;
						player.defText = Textures.PLAYER2;
					}
					if (touchListener.buttonPressed[touchListener.UP]) {
						player.yMove(5);
						player.lastFacing = 3;
						player.defText = Textures.PLAYER3;
					}
					if (touchListener.buttonPressed[touchListener.DOWN]) {
						player.yMove(-5);
						player.lastFacing = 1;
						player.defText = Textures.PLAYER1;
					}
					if (touchListener.buttonPressed[touchListener.A]) {
						touchListener.buttonPressed[touchListener.A] = false;    // fires once per press
						player.accessInventory();
					}
					if (touchListener.buttonPressed[touchListener.PAUSE]) {
						touchListener.buttonPressed[touchListener.PAUSE] = false;
						state = State.PAUSE;
						break;
					}
				}

				// Everything that is drawn to the screen should be between ".begin" and ".end"
				batch.setProjectionMatrix(camera.combined);
				batch.begin();

				for (Background c : bgArr) 	{ c.draw(batch); }
				for (Bullet c : bullets) 	{ c.draw(batch); }
				for (Block c : blockArr) 	{ c.draw(batch); }
				for (Item c : items) 		{ c.draw(batch); }
				for (Enemy e : enemies) 	{ e.draw(batch); }
				for (Boss b : bosses) 		{ b.draw(batch); }
				player.draw(batch);

				// bullet management
				for (int i = 0; i < bullets.size(); i += 1) {
					bullets.get(i).update();

					for (Enemy e : enemies) {
						if (bullets.get(i).isCollidingWith(e)) {
							e.lives -= 1;
							bullets.get(i).alive = false;
						}
					}
					if (!bullets.get(i).alive) {    // ... check if 'dead'
						bullets.remove(i);        // ... remove if 'dead'
						i -= 1;                    // prevents the loop from skipping the next bullet
					}
				}
				// block management
				for (int i = 0; i < blockArr.size(); i += 1) {    // for each bullet
					if (!blockArr.get(i).alive) {                // ... check if 'dead'
						blockArr.remove(i);                        // ... remove if 'dead'
						i -= 1;                                    // prevents the loop from skipping the next bullet
					}
				}
				// item management
				for (int i = 0; i < items.size(); i += 1) {
					if (!items.get(i).alive) {
						items.remove(i);
						i -= 1;
					}
				}
				// enemy management
				for (int i = 0; i < enemies.size(); i += 1) {
					if (enemies.get(i).lives <= 0) {
						enemies.get(i).dropItem();
						enemies.remove(i);
						i -= 1;
						enemiesKilled++;
					}
				}
				// boss management
				for (int i = 0; i < bosses.size(); i += 1) {
					if (bosses.get(i).lives <= 0) {
						if (bosses.get(i).mini) {
							miniKilled = true;
						} else {
							bossKilled = true;
						}
						bosses.remove(i);

						if (bossKilled && miniKilled) {
							player = null;    // nulling the player makes respawning in the next floor easier
							loadLevel(levels[++levelNum] + "/level" + levelNum + ".txt");
						}
					}
				}
				// Inventory management
				if (touchListener.buttonPressed[touchListener.A]) {
					batch.draw(player.getSelectedInventory().defText, (float) player.x, (float) player.y);
				}

				// HUD management
				batch.draw(Textures.HUD, 0, gameHeight - 64);    // must go last, has to display over everything else
				font.draw(batch, "Your lives: " + player.lives, 10, (gameHeight - 64) + 48);
				for (int i = 0; i < ammo; i += 1) {
					batch.draw(Textures.BULLET, i * 7, gameHeight - 64);
				}

				if (touchListener.buttonPressed[touchListener.A]) {
					touchListener.buttonPressed[touchListener.A] = false;
					player.attack();
					batch.draw(player.getSelectedInventory().defText, (float) player.getSelectedInventory().x, (float) player.getSelectedInventory().y);
				}

				if (deadState) {
					DBHookUp db = new DBHookUp();
					db.updateDB(enemiesKilled);
					Gdx.app.exit();
				}

				batch.end();
				break;

			case PAUSE:
				if (touchListener.buttonPressed[touchListener.PAUSE]) {
					touchListener.buttonPressed[touchListener.PAUSE] = false;
					state = State.RUN;
					break;
				}
				batch.begin();

				for(Background b: bgArr)	{	b.draw(batch); }
				for(Bullet b	: bullets)	{	b.draw(batch); }
				for(Block b		: blockArr)	{	b.draw(batch); }
				for(Enemy e		: enemies)	{	e.draw(batch); }
				for(Item i		: items)	{	i.draw(batch); }
				for(Boss b		: bosses)	{	b.draw(batch); }
				player.draw(batch);

				// bullet management
				for(int i = 0; i < bullets.size(); i += 1) {
					bullets.get(i).update();

					for(Enemy e : enemies) {
						if(bullets.get(i).isCollidingWith(e)) {
							e.lives -= 1;
							bullets.get(i).alive = false;
						}
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
				// enemy management
				for(int i = 0; i < enemies.size(); i += 1) {
					if(enemies.get(i).lives <= 0) {
						enemies.get(i).dropItem();
						enemies.remove(i);
						i -= 1;
					}
				}
				// boss management
				for(int i = 0; i < bosses.size(); i += 1) {
					if(bosses.get(i).lives <= 0) {
						if(bosses.get(i).mini) { miniKilled = true; }
						else { bossKilled = true; }
						bosses.remove(i);

						if(bossKilled && miniKilled) {
							player = null;	// nulling the player makes respawning in the next floor easier
							loadLevel(levels[++levelNum] + "/level" + levelNum + ".txt");
						}
					}
				}
				// HUD management
				batch.draw(Textures.HUD, 0, gameHeight - 64);    // must go last, has to display over everything else
				font.draw(batch, "Your lives: " + player.lives, 10, (gameHeight - 64) + 48);
				if(state == State.PAUSE) {
					font.setColor(Color.ORANGE);
					font.getData().setScale(3,3);
					font.draw(batch, "Paused", screenWidth*11, screenHeight*19);
					font.setColor(Color.WHITE);
					font.getData().setScale(1,1);
				}
				for (int i = 0; i < ammo; i += 1) {
					batch.draw(Textures.BULLET, i * 7, gameHeight - 64);
				}
				if(deadState) {
					font.setColor(Color.ORANGE);
					font.draw(batch, "You have died...\nEnjoy the afterlife", (float)player.x-16, (float)player.y+96);
					font.draw(batch, "kill me to restart", screenWidth*4, screenHeight*14);
					font.draw(batch, "kill me to exit", screenWidth*24, screenHeight*14);
					font.setColor(Color.WHITE);
				}
				batch.end();
				break;
			case STOPPED:
				break;
			case RESUME:
				break;
		}

		stage.act();
		stage.draw();
		// Everything that is drawn to the screen should be between ".begin" and ".end"
	}

	public int getScore() {
		return enemiesKilled;
	}

	@Override
	public void dispose() {
		batch.dispose();
	}

	@Override
	public void resize(int width, int height) {
		viewport.update(width,height);
		camera.position.set(camera.viewportWidth/2,camera.viewportHeight/2,0);
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
}
