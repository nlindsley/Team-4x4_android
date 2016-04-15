package com.tspandroid;

import com.badlogic.gdx.graphics.Texture;

/**
 * Class that creates the textures for the game.
 * 
 * @author Charles Heckel
 */
public class Textures {
	// image should be size 2^n
	// Barriers
	static Texture BLOCK	= new Texture("etc/default.png");
	static Texture CWBLOCK  = new Texture("Barriers/rock.png");
	static Texture GBLOCK   = new Texture("Barriers/smallbush.png");
	static Texture POISON	= new Texture("Floors/PosionFloor.png");
	static Texture W1BAR	= new Texture("Floors/longgrass.png");
	static Texture W1DOOR0	= new Texture("Barriers/woodleftdoor.png");
	static Texture W1DOOR1	= new Texture("Barriers/woodbottomdoor.png");
	static Texture W1DOOR2	= new Texture("Barriers/woodrightdoor.png");
	static Texture W1DOOR3	= new Texture("Barriers/woodtopdoor.png");
	static Texture W2BAR	= new Texture("Barriers/iceblock.png");
	static Texture W2DOOR3	= new Texture("Barriers/icetopdoor.png");
	static Texture W3BAR	= new Texture("Barriers/firepit.png");
	static Texture W3DOOR	= new Texture("etc/default.png");			//default
	static Texture W4BAR	= new Texture("Barriers/rock.png");
	static Texture W4DOOR	= new Texture("etc/default.png");			//default
	static Texture W5BAR	= new Texture("etc/default.png");			//default
	static Texture W5DOOR	= new Texture("etc/default.png");			//default
	static Texture W6BAR	= new Texture("etc/default.png");			//default
	static Texture W6DOOR	= new Texture("etc/default.png");			//default
	static Texture W7BAR	= new Texture("etc/default.png");			//default
	static Texture W7DOOR	= new Texture("etc/default.png");			//default
	static Texture W8BAR	= new Texture("etc/default.png");			//default
	static Texture W8DOOR	= new Texture("etc/default.png");			//default
	
	// Character
	static Texture PLAYER0	= new Texture("Character/knightleft.png");
	static Texture PLAYER1	= new Texture("Character/knightfront.png");
	static Texture PLAYER2	= new Texture("Character/knightright.png");
	static Texture PLAYER3	= new Texture("Character/knightback.png");
	
	// Enemies
	static Texture CENEMY0  = new Texture("Enemies/challengingenemyfront.png");
	static Texture CENEMY1  = new Texture("Enemies/challengingenemyfront.png");
	static Texture CENEMY2  = new Texture("Enemies/challengingenemyback.png");
	static Texture CENEMY3  = new Texture("Enemies/challengingenemyback.png");
	static Texture ENEMY0	= new Texture("Enemies/banditleft.png");
	static Texture ENEMY1	= new Texture("Enemies/banditfront.png");
	static Texture ENEMY2	= new Texture("Enemies/banditright.png");
	static Texture ENEMY3	= new Texture("Enemies/banditback.png");
	static Texture STENEMY0 = new Texture("Enemies/supertoughenemyfront.png");
	static Texture STENEMY1 = new Texture("Enemies/supertoughenemyfront.png");
	static Texture STENEMY2 = new Texture("Enemies/supertoughenemyback.png");
	static Texture STENEMY3 = new Texture("Enemies/supertoughenemyback.png");
	static Texture POISGUN	= new Texture("Enemies/poisongun.png");
	static Texture POISSHOT	= new Texture("Enemies/poisonshot.png");
	
	// Bosses
	static Texture W1BOSS0	= new Texture("Enemies/GrassBossLeft.png");
	static Texture W1BOSS1	= new Texture("Enemies/GrassBossFront.png");
	static Texture W1BOSS2	= new Texture("Enemies/GrassBossRight.png");
	static Texture W1BOSS3	= new Texture("Enemies/GrassBossBack.png");
	static Texture W1MBOSS0	= new Texture("Enemies/ButterflyLeft.png");
	static Texture W1MBOSS1	= new Texture("Enemies/ButterflyFront.png");
	static Texture W1MBOSS2	= new Texture("Enemies/ButterflyRight.png");
	static Texture W1MBOSS3	= new Texture("Enemies/ButterflyBack.png");
	static Texture W2BOSS0	= new Texture("Enemies/IceBossLeft.png");
	static Texture W2BOSS2	= new Texture("Enemies/IceBossRight.png");
	static Texture W2BOSS3	= new Texture("Enemies/IceBossBack.png");
	static Texture W2MBOSS0	= new Texture("Enemies/penguinleft.png");
	static Texture W2MBOSS1	= new Texture("Enemies/penguindown.png");
	static Texture W2MBOSS2	= new Texture("Enemies/penguinright.png");
	static Texture W2MBOSS3	= new Texture("Enemies/penguinup.png");
	static Texture W3BOSS	= new Texture("badlogic.jpg");
	static Texture W3MBOSS	= new Texture("badlogic.jpg");
	static Texture W4BOSS	= new Texture("badlogic.jpg");
	static Texture W4MBOSS	= new Texture("badlogic.jpg");
	static Texture W5BOSS	= new Texture("badlogic.jpg");
	static Texture W5MBOSS	= new Texture("badlogic.jpg");
	static Texture W6BOSS	= new Texture("badlogic.jpg");
	static Texture W6MBOSS	= new Texture("badlogic.jpg");
	static Texture W7BOSS	= new Texture("badlogic.jpg");
	static Texture W7MBOSS	= new Texture("badlogic.jpg");
	static Texture W8BOSS1	= new Texture("Enemies/blackknightfront.png");
	static Texture W8MBOSS	= new Texture("badlogic.jpg");
	
	// etc
	static Texture DEFAULT	= new Texture("etc/default.png");
	static Texture HUD		= new Texture("etc/overlay.png");
	static Texture PAUSE	= new Texture("etc/Pause Button.png");
	
	// Floors
	static Texture ABYSS	= new Texture("Floors/Abyss.png");
	static Texture BRIDGE	= new Texture("Floors/Bridge.png");
	static Texture W1FLO	= new Texture("Floors/grass.png");
	static Texture W2FLO	= new Texture("Floors/icefloor.png");
	static Texture W3FLO	= new Texture("Floors/burningcavefloor.png");
	static Texture W4FLO	= new Texture("Floors/cavefloor.png");
	static Texture W5FLO	= new Texture("Floors/World 5 Floor.png");
	static Texture W6FLO	= new Texture("Floors/checkerfloor.png");
	static Texture W7FLO	= new Texture("Floors/World 7 Floor.png");
	static Texture W8FLO	= new Texture("Floors/World 8 Floor.png");
	
	// Items
	static Texture ARROWPACK= new Texture("Items/arrowbundle.png");
	static Texture BOMB		= new Texture("Items/bomb.png");
	static Texture BOW		= new Texture("Items/bow.png");
	static Texture BULLET	= new Texture("bullet.png");
	static Texture EXPLOSION= new Texture("Items/explosion.png");
	static Texture HEART	= new Texture("Items/heart.gif");
	static Texture LSWORD0	= new Texture("Items/lightswordleft.png");
	static Texture LSWORD1	= new Texture("Items/lightsworddown.png");
	static Texture LSWORD2	= new Texture("Items/lightswordright.png");
	static Texture LSWORD3	= new Texture("Items/lightswordup.png");
	static Texture MSHIELD0	= new Texture("Items/megashieldleft.png");
	static Texture MSHIELD1	= new Texture("Items/megashielddown.png");
	static Texture MSHIELD2	= new Texture("Items/megashieldright.png");
	static Texture MSHIELD3	= new Texture("Items/megashieldup.png");
	static Texture SCYTHE0	= new Texture("Items/scytheleft.png");
	static Texture SCYTHE1	= new Texture("Items/scythedown.png");
	static Texture SCYTHE2	= new Texture("Items/scytheright.png");
	static Texture SCYTHE3	= new Texture("Items/scytheup.png");
	static Texture SWORD0	= new Texture("Items/swordleft.png");
	static Texture SWORD1	= new Texture("Items/sworddown.png");
	static Texture SWORD2	= new Texture("Items/swordright.png");
	static Texture SWORD3	= new Texture("Items/swordup.png");
	static Texture TORCH0	= new Texture("Items/torchleft.png");
	static Texture TORCH1	= new Texture("Items/torchdown.png");
	static Texture TORCH2	= new Texture("Items/torchright.png");
	static Texture TORCH3	= new Texture("Items/torchup.png");
	
}
