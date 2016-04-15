Version 2.0 This program is a Legend of Zelda style adventure game featuring 3 demo levels: a combat level, puzzle level, and boss level. Both a desktop and Android version of the game are being produced using LibGDX. We are using Mitch Davis' (mitchhelp.me) code from his game lectures presented in 2014 and 2015 as a basis (permission granted) and then building features on top of that basis. All art, screens, and level designs are our own work.

Credits 4 Byte Warrior was produced by Team 4x4 for Kim Tracy's Team Software Product class Spring 2016. Team 4x4 consists of Alex Friebe, Nick Lindsley, Ben McWerthy, and Charlie Heckel.

System Requirements: Desktop Version: A Java compiler. Given the simplistic art and small number of sprites on the screen, the game is not at all system intensive. Run from Eclipse IDE using configuration instructions. Android Version: To run the game, Android 4.2.2 or higher is need. Launch from the widget.

Configuration: Android: Download the files from Github and open the Team-4x4_android folder as an existing project in Android Studio. Start an emulator or connect an Android device and click run. Desktop: Open Eclipse and import the two projects. Open TSPGame-core and right click on the assets folder. Open properties and copy its location. After doing this, right click on TSPGame-Desktop and remove the TSPGame-desktop/assets folder. Close properties and delete the assets folder from TSPGame-desktop. Open properties and click link source. Paste your copied location into the linked folder location and ensure that the folder name that is generated is assets. Click finish. Click apply and then ok. Right click Primary.java and run as a Java application.

Files List: Impelemented: Background, Block, Boss, Character, DBHookUp, DBHookUpTest, Enemy, Equipable Item, Item, Listener, Player, State, Sword, Textures, and TSPGame from TSPGame-core and DesktopLauncher and Primary from TSPGame-desktop

Unimplemented: Abyss, Bombs, Bow, Bridge, Bullet, ChallengingEnemy, Cracked Wall, GrassBlakc, Hammer, Lantern, LightSword, MegaShield, Poison, Sycthe, SnowBlock, and SuperToughEnemy
