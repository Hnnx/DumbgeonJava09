package dumb;

import java.util.Scanner;

public class MainGame {

	static boolean gameEnd = false;
	static String[] hN = new String[3];
	static Scanner ui = new Scanner(System.in);
	static Scanner uiINT = new Scanner(System.in);

	public static void main(String[] args) {

		int knightHP = retrieveHP(0);
		int mageHP = retrieveHP(1);
		int archHP = retrieveHP(2);

		//ENCOUNTER CHECK
		boolean encounter = true;
		boolean progress = false;
		boolean lvlchoice = true;

		//Level/Hero
		String heroChoice;
		String levelChoice;
		int userChoice;

		//Death
		String cod = causeOfDeath();


		//START GAME
		System.out.println("Welcome to goblin game - you have 3 heroes at your disposal, the fearless knight,  the enigmatic mage and last but not least, an archer who is presumed to be the village idiot who stumbled across a bow and found himself in this predicament");
		System.out.println("Your job is to select one of the ... uh... 'heroes' and defeat the foes whilst trying to reach the lowest floor in the dungeon." );
		System.out.println("each floor grants better equipment, you can exit the dungeon after an encounter or proceed and risk loosing all your valuable loot" );
		System.out.println("Initials work too (k or K for Knight, m or M for Mage etc" );		
		System.out.println("~~~~~~< Enter hero names >~~~~~");



		//Names prompt
		hN[0] = retrieveName(0);			
		hN[1] = retrieveName(1);				
		hN[2] = retrieveName(2);


		/*
		//Names prompt DEV TEST
		// turn this on to skip name selection

		hN[0] = "a"	;	
		hN[1] = "b";				
		hN[2] = "cc";

		 */

		//ease of use handles
		String knigName = hN[0];
		String magName = hN[1];
		String archName = hN[2];

		enterLevel();				


		// ENCOUNTER 1
		while(encounter) {
			try {
				System.out.println("\nRemember, you can use Knight (knight, k or K works too), Mage or Archer to choose the unfortunate fool");
				heroChoice = ui.nextLine();

				if ( heroChoice.equalsIgnoreCase("Mage") || heroChoice.equalsIgnoreCase("m")) {
					System.out.println(magName+ " threw fireballs and hit the enemies. And a few walls. Mostly walls though. #JusticeForWalls \n");					
					encounter = false;
					lvlchoice = true;					
				}
				else if (heroChoice.equalsIgnoreCase("Archer") || heroChoice.equalsIgnoreCase("A")) {
					archHP--;
					System.out.println(archName+ " hit the enemy but they appear immune, lost 1 hp - current HP: "+archHP);

					if (archHP == 0) {
						System.out.println(archName+ " died, "+cod);
						System.out.println("\nYou never left the dungeon");
						System.exit(0);
					}					
				}

				else if (heroChoice.equalsIgnoreCase("Knight") || heroChoice.equalsIgnoreCase("K")){		
					knightHP--;
					System.out.println( knigName+ " tried doing a plunging attack which resulted in a high medical bill. "+knigName+ " lost 1 hp - current HP: "+knightHP);									

					if (knightHP == 0) {
						System.out.println(knigName+ " Died, "+cod);
						System.out.println("\nYou never left the dungeon");
						System.exit(0);											
					}					
				}				
				else {
					encounter = true;					
					System.out.println("Error! pick a hero");										
				}
			}
			catch(Exception e) {				
				System.out.println("Runtime Error!");
				encounter = true;
			}				
		}	
		//LVL Choice	1		
		while(lvlchoice) {	
			try {		
				System.out.println("Descend to lower floor? Y/N");
				levelChoice = ui.nextLine();
				if(levelChoice.equalsIgnoreCase("Y")|| levelChoice.equalsIgnoreCase("yes")) {
					System.out.println("\nEntering floor 2...");
					progress = true;	
					lvlchoice = false;
					encounter = true;

				}
				else if( levelChoice.equalsIgnoreCase("N")||levelChoice.equalsIgnoreCase("No")) { 
					System.out.println("You acquired the following loot: ");
					setLoot(2);										
					System.out.println("GAME OVER - YOU SURVIVED AND REACHED FLOOR 2");
					lvlchoice = false;
					break;										

				}

				else {
					System.out.println("Select Yes or No");
					progress = false;
					lvlchoice = true;
				}				
			}

			catch(Exception e) {
				System.out.println("Error!");
				progress = false;
				lvlchoice = true;
			}
		}	

		// ENCOUNTER 2
		//>>>>>>>>>>>>>>
		if(progress) {			
			enterLevel(); 
		}
		while(encounter) {					
			try {				
				System.out.println("\nOk this looks bad, who should attack next?");
				heroChoice = ui.nextLine();

				if ( heroChoice.equalsIgnoreCase("Mage") || heroChoice.equalsIgnoreCase("m")) {

					mageHP--;						
					System.out.println(magName+" got ran over by a bus." +magName+" loses 1 hp, current HP: "+ mageHP);

					if (mageHP == 0) { 
						System.out.println(magName+ " died, "+cod);
						System.out.println("\nYou never left the dungeon");				
						lvlchoice = false;
						progress = false;
						encounter = false;
						break;						
					}

				}
				else if (heroChoice.equalsIgnoreCase("Archer")|| heroChoice.equalsIgnoreCase("A")) {
					System.out.println(archName+ " lands a clean shot right in the foot. It deals abysmall damage but fortunately for our team, the arrow incapacitated the enemy\n");
					encounter = false;
					lvlchoice = true;


				}

				else if (heroChoice.equalsIgnoreCase("Knight") || heroChoice.equalsIgnoreCase("K")){		
					knightHP--;
					System.out.println( "Karen took the kids. "+knigName+" begs her to stay but the lawyer made things very clear, he can see them twice a month on weekends. Lost 1 hp due to minor gardening injury  - current HP: "+knightHP);									

					if (knightHP == 0) {
						System.out.println(knigName+ " died,  "+cod);
						System.out.println("\nYou never left the dungeon");
						progress = false;
						lvlchoice = false;
						encounter = false;
						break;												
					}					
				}		
				else {
					encounter = true;
					System.out.println("Select a hero");										
				}
			}
			catch(Exception e) {
				System.out.println("Runtime Error!");
				encounter = true;
			}				
		}		


		//LVL Choice 2
		//>>>>>>>>>>>>>>>	
		while(lvlchoice) {	
			try {
				System.out.println("You spot another set of stairs going downwards, do you want to proceed? Y/N");
				levelChoice = ui.nextLine();
				if(levelChoice.equalsIgnoreCase("Y")|| levelChoice.equalsIgnoreCase("yes")) {
					System.out.println("\nEntering floor 3...\nCurrent hero HP: \nKnight: "+knightHP+" Mage: "+mageHP+" Archer: "+archHP);
					lvlchoice = false;				
					progress = true;
					encounter = true;
				}
				else if( levelChoice.equalsIgnoreCase("N")||levelChoice.equalsIgnoreCase("No")) { 
					System.out.println("You acquired the following loot: ");
					setLoot(3);		
					System.out.println("GAME OVER - YOU SURVIVED AND REACHED FLOOR 3");		
					lvlchoice = false;
					progress = false;
					break;										
				}

				else {
					System.out.println("Select Yes or No");
					lvlchoice = true;
				}				
			}

			catch(Exception e) {
				System.out.println("Error!");
				lvlchoice = true;			
			}
		}		

		// ENCOUNTER 3
		//>>>>>>>>>>>>>>
		if(progress) {
			enterLevel();		
		}
		while(encounter) {					
			try {				
				System.out.println("\nYou made it this far...next move?");
				heroChoice = ui.nextLine();

				if ( heroChoice.equalsIgnoreCase("Mage") || heroChoice.equalsIgnoreCase("m")) {

					mageHP--;						
					System.out.println(magName+ " sends lightning bolts but none of them seem to hit. "+magName+" stumbles and loses 1 hp, current HP: "+ mageHP);

					if (mageHP == 0) { 
						System.out.println(magName+ " died, "+cod);
						System.out.println("\nYou never left the dungeon");
						encounter = false;
						lvlchoice = false;
						progress = false;
						break;						
					}

				}
				else if (heroChoice.equalsIgnoreCase("Archer")|| heroChoice.equalsIgnoreCase("A")) {
					archHP--;
					System.out.println(archName+ " lands a bolt right BETWEEN THE... wait no, actually just hit the ground - current HP: "+archHP);	

					if (archHP == 0) { 
						System.out.println(archName+ " died, "+ cod);
						System.out.println("\nYou never left the dungeon");				
						lvlchoice = false;
						progress = false;
						encounter = false;
						break;						
					}

				}

				else if (heroChoice.equalsIgnoreCase("Knight") || heroChoice.equalsIgnoreCase("K")){		

					System.out.println(knigName+ " flaunts the two handed sword like a deranged maniac and, to everyone's surprise, deals with the enemies\n");
					encounter = false;
					lvlchoice = true;
				}				
				else {
					encounter = true;
					System.out.println("You're supposed to pick a hero");										
				}
			}
			catch(Exception e) {
				System.out.println("Runtime Error!");
				encounter = true;
			}				
		}		



		//LVL Choice 4
		//>>>>>>>>>>>>>>>	
		while(lvlchoice) {	
			try {
				System.out.println("You see a light shimering, looks like another set of stairs. Proceed to lower floor? Y/N");
				levelChoice = ui.nextLine();
				if(levelChoice.equalsIgnoreCase("Y")|| levelChoice.equalsIgnoreCase("yes")) {
					System.out.println("\nEntering floor 4... \nCurrent hero HP: \n"+knigName+": "+knightHP+" "+magName +": "+mageHP+" "+archName+ ": "+archHP);
					lvlchoice = false;				
					progress = true;
					encounter = true;

				}
				else if( levelChoice.equalsIgnoreCase("N")||levelChoice.equalsIgnoreCase("No")) { 
					System.out.println("You acquired the following loot: ");
					setLoot(4);		
					System.out.println("GAME OVER - YOU SURVIVED AND REACHED FLOOR 4");		
					encounter = false;
					progress = false;
					lvlchoice  = false;
					break;										
				}

				else {
					System.out.println("Select Yes or No");								
					lvlchoice = true;
				}				
			}						
			catch(Exception e) {
				System.out.println("Error!");
				lvlchoice = true;				

			}
		}


		// ENCOUNTER 4
		//>>>>>>>>>>>>>>
		if(progress) {			
			enterLevel();
		}

		while(encounter) {					
			try {				
				System.out.println("\nYou know the drill by now... pick a hero");
				heroChoice = ui.nextLine();

				if ( heroChoice.equalsIgnoreCase("Mage") || heroChoice.equalsIgnoreCase("m")) {
					mageHP--;
					System.out.println(magName+" is reading a Cookbook instead of the book of spells - current HP: "+mageHP);

					if (mageHP == 0) { 
						System.out.println(magName+ " died, "+cod);
						System.out.println("\nYou never left the dungeon");
						encounter = false;						
						lvlchoice = false;						
						progress = false;
						break;						
					}

				}
				else if (heroChoice.equalsIgnoreCase("Archer")|| heroChoice.equalsIgnoreCase("A")) {
					archHP--;
					System.out.println("Arrows flew, foes fell, victory was ours! alas, "+archName+" talked about Sun Tzu instead of attacking - current HP: "+ archHP );

					if (archHP == 0) { 
						System.out.println(archName+ " died, "+cod);
						System.out.println("\nYou never left the dungeon");				
						encounter  = false;
						lvlchoice = false;
						progress = false;
						break;						
					}

				}

				else if (heroChoice.equalsIgnoreCase("Knight")|| heroChoice.equalsIgnoreCase("K")){

					System.out.println(knigName+ " went on a rampage and dealt with the enemis. This will surely impress his ex wife.\n");
					encounter = false;
					lvlchoice = true;
				}				
				else {
					encounter = true;
					System.out.println("Select a hero!");										
				}
			}
			catch(Exception e) {
				System.out.println("Runtime Error!");
				encounter = true;
			}				
		}// END encounter




		//LVL Choice 5
		//>>>>>>>>>>>>>>>	
		while(lvlchoice) {	
			try {
				System.out.println("The dungeon turns dark, "+magName+" uses some of her fiery spells to light up the area. "+knigName+" finds another exit, continue?");
				levelChoice = ui.nextLine();
				if(levelChoice.equalsIgnoreCase("Y")|| levelChoice.equalsIgnoreCase("yes")) {
					System.out.println("\nEntering floor 5... \nCurrent hero HP: \n"+knigName+": "+knightHP+" "+magName +": "+mageHP+" "+archName+ ": "+archHP);
					lvlchoice = false;				
					progress = true;
					encounter = true;

				}
				else if( levelChoice.equalsIgnoreCase("N")||levelChoice.equalsIgnoreCase("No")) { 
					System.out.println("You acquired the following loot: ");
					setLoot(5);		
					System.out.println("GAME OVER - YOU SURVIVED AND REACHED FLOOR 5");				
					encounter = false;
					progress = false;
					lvlchoice  = false;
					break;										
				}

				else {
					System.out.println("Select Yes or No");								
					lvlchoice = true;
				}				
			}						
			catch(Exception e) {
				System.out.println("Error!");
				lvlchoice = true;				

			}
		}//END LVL CHOICE



		// ENCOUNTER 6
		//>>>>>>>>>>>>>>
		if(progress) {
			enterLevel();
		}
		while(encounter) {
			try {
				System.out.println(archName + " asks "+knigName+" if he can spot some arrows for the next encounter. Who's attacking?");
				heroChoice = ui.nextLine();

				if ( heroChoice.equalsIgnoreCase("Mage") || heroChoice.equalsIgnoreCase("m")) {
					archHP--;
					knightHP--;
					System.out.println(magName+ " cast a huge fireball that obliterated everyone, including "+knigName+" and "+archName+" - each lost 1 HP "+"("+knigName+" HP: "+knightHP+", "+archName+" HP: "+archHP+")\n");

					if (archHP == 0) { 
						System.out.println(archName+ " died, "+cod);
						System.out.println("\nYou never left the dungeon");
						encounter = false;
						lvlchoice = false;
						progress = false;
						break;						
					}
					if (knightHP == 0) { 
						System.out.println(knigName+ " died, "+cod);
						System.out.println("\nYou never left the dungeon");		
						encounter = false;
						lvlchoice = false;
						progress = false;
						break;						
					}

					lvlchoice = true;			
					encounter = false;

				}

				else if (heroChoice.equalsIgnoreCase("Archer")|| heroChoice.equalsIgnoreCase("A")) {

					archHP--;
					System.out.println(archName+ " is still looking for ammo  - current HP: "+archHP);


					if (archHP == 0) { 
						System.out.println(archName+ " died, "+cod);
						System.out.println("\nYou never left the dungeon");
						encounter = false;
						lvlchoice = false;
						progress = false;
						break;						
					}

				}

				else if (heroChoice.equalsIgnoreCase("Knight") || heroChoice.equalsIgnoreCase("K")){		

					knightHP--;
					System.out.println("Now I don't even know what the hell is "+ knigName+ "doing right now...  - current HP: "+knightHP);									

					if (knightHP == 0) { 
						System.out.println(knigName+ "died, "+cod);
						System.out.println("\nYou never left the dungeon");
						encounter = false;
						lvlchoice = false;
						progress = false;
						break;						
					}	
				}

				else {
					encounter = true;
					System.out.println("Pick a hero!");
				}
			}

			catch(Exception e) {
				System.out.println("Runtime Error!");
				encounter = true;
			}
		}


		//LVL Choice 6
		//>>>>>>>>>>>>>>>	
		while(lvlchoice) {	
			try {
				System.out.println(magName+" locates another exit, it seems to go even deeper, continue?");
				levelChoice = ui.nextLine();
				if(levelChoice.equalsIgnoreCase("Y")|| levelChoice.equalsIgnoreCase("yes")) {
					System.out.println("\nEntering floor 5... \nCurrent hero HP: \n"+knigName+": "+knightHP+" "+magName +": "+mageHP+" "+archName+ ": "+archHP);
					lvlchoice = false;				
					progress = true;
					encounter = true;

				}
				else if( levelChoice.equalsIgnoreCase("N")||levelChoice.equalsIgnoreCase("No")) { 
					System.out.println("You acquired the following loot: ");
					setLoot(6);		
					System.out.println("GAME OVER - YOU SURVIVED AND REACHED FLOOR 6");	
					encounter = false;					
					progress = false;
					lvlchoice  = false;
					break;										
				}

				else {
					System.out.println("Select Yes or No");								
					lvlchoice = true;
				}				
			}						
			catch(Exception e) {
				System.out.println("Error!");
				lvlchoice = true;				

			}
		}//END LVL CHOICE


		// ENCOUNTER 5
		//>>>>>>>>>>>>>>
		if(progress) {			
			enterLevel();
		}

		while(encounter) {					
			try {				
				System.out.println("Those guys look scary. Or at least better equipped. Who are you sending in?");
				heroChoice = ui.nextLine();

				if ( heroChoice.equalsIgnoreCase("Mage") || heroChoice.equalsIgnoreCase("m")) {
					mageHP--;
					knightHP++;
					archHP++;					
					System.out.println(magName+" heals your  team but loses 1 HP in the process: "+mageHP);

					if (mageHP == 0) { 
						System.out.println(magName+ " died, "+cod);
						System.out.println("\nYou never left the dungeon");
						encounter = false;
						lvlchoice = false;
						progress = false;
						break;						
					}

				}
				else if (heroChoice.equalsIgnoreCase("Archer")|| heroChoice.equalsIgnoreCase("A")) {

					System.out.println(archName +" really shines in this encounter, despite his shortcomings and jewish upbringing, he eliminates the enemies\n" );

					encounter = false;
					lvlchoice = true;

				}

				else if (heroChoice.equalsIgnoreCase("Knight") || heroChoice.equalsIgnoreCase("K")){	
					knightHP--;		
					System.out.println(knigName+ " commits sudoku - current HP: "+knightHP);

					if (knightHP == 0) { 
						System.out.println(knigName+ " died, "+cod);
						System.out.println("\nYou never left the dungeon");				
						encounter = false;
						lvlchoice = false;
						progress = false;
						break;						
					}

				}				
				else {
					encounter = true;
					System.out.println("Select a hero!");										
				}
			}
			catch(Exception e) {
				System.out.println("Runtime Error!");
				encounter = true;
			}				
		}// END encounter


		//LVL Choice 7
		//>>>>>>>>>>>>>>>	
		while(lvlchoice) {	
			try {
				System.out.println("Descend another floor?");
				levelChoice = ui.nextLine();
				if(levelChoice.equalsIgnoreCase("Y")|| levelChoice.equalsIgnoreCase("yes")) {
					System.out.println("Entering floor 5... \nCurrent hero HP: \n"+knigName+": "+knightHP+" "+magName +": "+mageHP+" "+archName+ ": "+archHP);
					lvlchoice = false;				
					progress = true;
					encounter = true;

				}
				else if( levelChoice.equalsIgnoreCase("N")||levelChoice.equalsIgnoreCase("No")) { 
					System.out.println("You acquired the following loot: ");
					setLoot(7);		
					System.out.println("GAME OVER - YOU SURVIVED AND REACHED FLOOR 7");			
					encounter = false;
					progress = false;
					lvlchoice  = false;
					break;										
				}

				else {
					System.out.println("Select Yes or No");								
					lvlchoice = true;
				}				
			}						
			catch(Exception e) {
				System.out.println("Error!");
				lvlchoice = true;				

			}
		}//END LVL CHOICE		


		// ENCOUNTER 6 MEDS
		//>>>>>>>>>>>>>>
		while(encounter) {					
			try {
				System.out.println("Finally, two sets of bandages and aid! A swarm of rats is headed towards them and you can only save one, act quick, which one do you choose? Select 1 or 2");
				userChoice = Integer.parseInt(ui.nextLine());

				int randomEncNumer = (int) (Math.random()*2+1);								

				if (userChoice == randomEncNumer) {
					mageHP = mageHP + 5;
					archHP = archHP + 5;
					knightHP = knightHP + 5;
					System.out.println("You got the bandages!\nHero HP: "+knigName+": "+knightHP+", "+magName+": "+mageHP+", "+archName+": "+archHP);
					lvlchoice = false;
					progress = true;
					encounter = false;					

				}			
				else  if (userChoice <= 0 || userChoice >= 3){
					encounter = true;					
					System.out.println("Pick a number between 1 and 2!");										
				}else {					
					System.out.println("Right ok look, they APPEARED like two bandage sets and ... well apparently only one was, and the rats got it. Rats gained 5 hp but how does that help you is beyond me");
					lvlchoice = false;
					progress = true;
					encounter = false;


				}				
			}
			catch(Exception e) {
				System.out.println("Runtime Error!");
				encounter = true;
			}				
		}// END encounter



		// ENCOUNTER 7
		//>>>>>>>>>>>>>>
		if(progress) {			
			enterLevel();
			encounter = true;
		}

		while(encounter) {
			try {
				System.out.println("There's blood allover the floor and a bunch of runes carved into the wall... it looks like a numeric sequence, pick a number between 1-3 to press one of the runes.");
				userChoice = Integer.parseInt(ui.nextLine());

				int randomEncNumer = (int) (Math.random()*4+1);

				if (userChoice == randomEncNumer) {
					System.out.println("Rune lights up and another door opens\n");
					lvlchoice = true;
					encounter = false;

				}			
				else  if (userChoice <= 0 || userChoice >= 4){
					encounter = true;
					System.out.println("Pick a number between 1 and 3!");										
				}else {
					mageHP--;
					archHP--;
					knightHP--;
					System.out.println("That wasn't the right rune... heroes suffered damage, the rune wheel on the wall changed. It's anyone guess now.\n"+"("+knigName+" HP: "+knightHP+", "+archName+" HP: "+archHP+", "+magName+" HP: "+mageHP+")");

					if (mageHP == 0) { 
						System.out.println(magName+ " died, "+cod);
						System.out.println("\nYou never left the dungeon");
						encounter = false;
						lvlchoice = false;
						progress = false;
						break;						
					}
					if (archHP == 0) { 
						System.out.println(archName+ " died, "+cod);
						System.out.println("\nYou never left the dungeon");
						encounter = false;
						lvlchoice = false;
						progress = false;
						break;						
					}
					if (knightHP == 0) { 
						System.out.println(knigName+ " died, "+cod);
						System.out.println("\nYou never left the dungeon");
						encounter = false;
						lvlchoice = false;
						progress = false;
						break;						
					}



				}				
			}

			catch(Exception e) {
				System.out.println("Runtime Error");				
				encounter = true;					
			}		
		}// END encounter




		//LVL Choice 7
		//>>>>>>>>>>>>>>>	
		while(lvlchoice) {	
			try {
				System.out.println("Continue?");
				levelChoice = ui.nextLine();
				if(levelChoice.equalsIgnoreCase("Y")|| levelChoice.equalsIgnoreCase("yes")) {
					System.out.println("Entering floor 5... \nCurrent hero HP: \n"+knigName+": "+knightHP+" "+magName +": "+mageHP+" "+archName+ ": "+archHP);
					lvlchoice = false;				
					progress = true;
					encounter = true;

				}
				else if( levelChoice.equalsIgnoreCase("N")||levelChoice.equalsIgnoreCase("No")) { 
					System.out.println("You acquired the following loot: ");
					setLoot(7);		
					System.out.println("GAME OVER - YOU SURVIVED AND REACHED FLOOR 7");			
					encounter = false;
					progress = false;
					lvlchoice  = false;
					break;										
				}

				else {
					System.out.println("Select Yes or No");								
					lvlchoice = true;
				}				
			}						
			catch(Exception e) {
				System.out.println("Error!");
				lvlchoice = true;				

			}
		}//END LEVEL CHOICE


		// ENCOUNTER 7
		//>>>>>>>>>>>>>>
		if(progress) {			
			//enterLevel();
			encounter = true;
		}

		while(encounter) {					
			try {				
				System.out.println("You are faced with the dark lord of the dungeons himself! Pick one hero to sacrifice in order to obtain MAXIUM LOOT");
				heroChoice = ui.nextLine();

				if ( heroChoice.equalsIgnoreCase("Mage") || heroChoice.equalsIgnoreCase("m")) {
					System.out.println("Wow you really went for it huh? Oh well, "+magName+" was sacrificed");
					System.out.println("You came out victorious, "+knigName+" and "+archName+" are celebrating the death of "+ magName+". Guess they weren't so close after all");
					encounter = false;
					lvlchoice = true;

				}
				else if (heroChoice.equalsIgnoreCase("Archer")|| heroChoice.equalsIgnoreCase("A")) {
					System.out.println(" \"Ah, a fine choice. But I don't really enjoy eating vegetables\", said the dark lord. Did we forget to mention "+archName+" is stuck on a wheelchair? Yeah would be great if we mentioned that before");
					encounter = false;
					lvlchoice = true;
				}

				else if (heroChoice.equalsIgnoreCase("Knight")|| heroChoice.equalsIgnoreCase("K")){

					System.out.println("\"This pleases the lord!\"."+knigName+", it's your turn to sacrifice yourself for the greater good, god only knows what loot awaits our heroes" );
					encounter = false;
					lvlchoice = true;
				}				
				else {
					encounter = true;
					System.out.println("Select a hero!");										
				}
			}
			catch(Exception e) {
				System.out.println("Runtime Error!");
				encounter = true;
			}				
		}// END encounter

		if(lvlchoice) {
			System.out.println("You reached the bottom floor and survived! Loot obtained: ");
			setLoot(8);
			System.out.println("and last but not least, Helen's rocking tits");
		}



	}//END MAIN

	public static void enterLevel() {			
		//randomHeroGen
		int rhg = (int) (Math.random()*3);

		//enemyNum
		int enemyNum = (int) (Math.random()*3+2);

		//Levels
		String[] ranLevel = {"of the fallen souls","that kinda stinks ","of 'that guy'","Moonshine","of soviet conspiracy","with extra cheese","of brown beans",
				"<DLC_AREA_2> DEAR USER PLEASE UPGRADE TO PREMIUM ACCOUNT","abandoned Sears building with Vaporwave music faintly playing in the background","broken elevators (there's plenty of stairs though)",
				"... wait this is just a DVD rental store, nevermind"};
		int rl = (int) (Math.random()* ranLevel.length-1);

		//Enemiz		
		String[] ranEnemy = {"skeletons", "bloated rats","fat chipmunks","one eyed donkeys","construction workers","lawyers","used car salesmen","Trip-Hop artists from downtown LA",
				"evil dark lords... FROM POLAND. Yeah, don't ask","German Quartet \"Junge Wurst\"","a bunch of cats on horse. The drug, no the animal.",
				"Scarecrows. Just scarecrows","a bunch of Pro Life activists"};
		int rEnemy = (int) (Math.random()* ranEnemy.length);
		//String randomEnemy = ranEnemy[rEnemy];

		//Enemiz State
		String[] ranEnemyState = {"in shock ", "just chilling","confused?","listening to the latest Weezer album","reading Jane Austin","working on UNI","debunking alien myths","getting yelled at by the drunk uncle",
				"snoring oh God it's so loud make it STOP SOMEONE","calling child protection services","from the IRA and those YouTube monetization earnings are looking very suspicious"};
		int rEnemyState = (int) (Math.random()* ranEnemyState.length);

		System.out.println("\n...Now entering the dungeon "+ranLevel[rl]);				
		System.out.println(hN[rhg]+ " spots "+ enemyNum + " "+ranEnemy[rEnemy]+", they are "+ ranEnemyState[rEnemyState]);		

	}

	public static String retrieveName(int rotate) {			
		boolean checkName = true;			
		String heroName = "";						

		while(checkName) {																
			if(rotate == 0) {
				System.out.println("Knight: ");
			} else if (rotate == 1) {
				System.out.println("Mage: ");
			} else {
				System.out.println("Archer: ");
			}		
			try {					
				heroName = ui.nextLine();				
				checkName = false;
			}catch (Exception e){
				System.out.println("Encountered error, type a name(text)");
				checkName = true;					
			}
		}						
		return heroName;
	}

	public static int retrieveHP(int heroPostion) {

		int[] hp = new int[3];				
		//Health = base + rdm		
		int r1 = (int) (Math.random()*2+5);
		int r2 = (int) (Math.random()*2+5);
		int r3 = (int) (Math.random()*2+5);

		hp[0] = r1;
		hp[1] = r2;
		hp[2] = r3;		

		if (heroPostion == 0) {
			return hp[0];
		} else if (heroPostion == 1) {
			return hp[1];
		}else {
			return hp[2];
		}

	}

	public static String getLoot(int l) {

		String[] loot1 = {"plastic fork","cat","sandal","perfume","keys","money","white out","soap","stockings","sharpie","spoon","shoe lace","slipper","bed","packing peanuts","rusty nail","flowers","carrots","monitor","lamp shade",};
		String[] loot2 = {"cell phone","rubber duck","sailboat","playing card","hair brush","USB drive","lip gloss","credit card","shawl","bow"};
		String[] loot3 = {"purse","deodorant","plate","food","wallet","drill press","bookmark","towel","piano","cork"};
		String[] loot4 = {"caviar","pretzels","quiche","granola","clam","pepper","paella","relish","sardines","gatorade"};
		String[] loot5 = {"toothbrush","photo album","flag","USB drive","screw","stockings","nail file","sidewalk","bracelet","bread"};
		String[] loot6 = {"cup","buckel","white out","tooth picks","glass","thread","chair","clay pot","playing card","credit card"};
		String[] loot7 = {"toilet","knife","rusty nail","twezzers","car","nail clippers","lace","chocolate","canvas","sand paper"};
		String[] loot8 = {"packing peanuts","apple","conditioner","controller","eye liner","bananas","sand paper","outlet","sofa","keys"};
		String[] loot9 = {"archerfish","leveret","linnet","crane","smelt","pigeon","polarbear","lizard","hog","porcupine"};
		String[] loot10 = {"limb","nodule","tissue","plantar","navel","carpal","rearend","eyeballs","palm","ligament"};


		if (l == 1) {
			return loot1[l];
		}
		else if(l == 2){
			return loot2[l];
		}
		else if(l == 3){
			return loot3[l];
		}
		else if(l == 4){
			return loot4[l];
		}
		else if(l == 5){
			return loot5[l];
		}
		else if(l == 6){
			return loot6[l];
		}
		else if(l == 7){
			return loot7[l];
		}		
		else if(l == 8){
			return loot8[l];
		}
		else if(l == 9){
			return loot9[l];
		}
		else {
			return loot10[l];
		}

	}

	static public void setLoot(int lootNum) {

		for (int x = 1; x < lootNum; x++) {			
			getLoot(x);
			System.out.println(getLoot(x));
		}

	}

	static public String causeOfDeath() {	
		String[] cod = {
				"cause of death was a peculiar incident involving a box of tissues and a wine glass."
				,"cause of death was a peculiar incident involving a multitool and a piano."
				,"death was caused by an aneurysm.","death was caused by sharp force trauma to the chest."
				,"cause of death was blood loss resulting from a severed foot."
				,"cause of death was a peculiar incident involving a wishbone and a club."
				,"cause of death was a bizarre gardening accident","Killed trying to recreate something seen on YouTube",
				"Killed by a rival chess player"
		};

		int x = (int) (Math.random()* cod.length);				

		return cod[x];

	}



}// END CLASS
