package com.adamegyed.ArenaFight.Characters;

import java.util.Random;

import com.adamegyed.ArenaFight.ArenaFight;
import com.adamegyed.ArenaFight.Windows.*;

public class LizardMan extends Characters {

	private Random randGen = new Random();
	
	public int timeToWander = 3;

	//Get the attack strength
	public int getAttackDamage() {
		
		return this.getAttackFlux();
	}

	//Constructor
	public LizardMan() {
		
		//Set the Enemy's stats - health, strength, defense, agility, and level
		super( 50, 30, 20, 5, 1);

        this.clss = "Lizardman";
        maxHealth = 50;
        randomSpawn();
        timeToWander = randGen.nextInt(4) + 1;
		
	}
	
	public void randomSpawn() {
		
		xpos = randGen.nextInt(5);
		ypos = randGen.nextInt(5);
		
		//LizardMan enemyMaybe = ArenaFight.GameWindow.enemyMap.get(ArenaFight.GameWindow.coordToHKey(this.xpos,this.ypos));
		
		
		
	}
	
	public void makeKing() {
		clss = "Lizard King";
		health = 200;
		strength = 60;
		defense = 60;
		agility = 10;
		level = 10;
	}
	
	public void makeWarrior() {
		clss = "Lizard Warrior";
		health = 80;
		strength = 40;
		defense = 30;
		agility = 7;
		level = 5;
	}
	
public void dealDamageToEnemy(int playerAStrength) {
		
		boolean didDodge = false;
		
		int chanceToDodge;
		
		int blockedDamage;
		
		int finalTakenD;
		
		chanceToDodge = randGen.nextInt(100) + 1;
		
		if (chanceToDodge < agility) {
			didDodge = true;
		}
		if(!didDodge) { // IF FAILED TO DODGE
			
			blockedDamage = getDefenseFlux();
			if (blockedDamage >= playerAStrength) {     //Blocked All Damage
				
				ArenaFight.GameWindow.consoleOut("The enemy blocked all damage.");
				
			}
			else { // Block is less than attack
				
				finalTakenD = playerAStrength - blockedDamage;
				ArenaFight.GameWindow.consoleOut("You dealt " + playerAStrength + " damage, but the enemy "
						+ "blocked " + blockedDamage + " \nand received " + finalTakenD +".");
				
				if (playerAStrength >= (ArenaFight.player.strength * 1.5)) { // If a critical hit
					ArenaFight.GameWindow.consoleOut("*** CRITICAL HIT ***");
					
					
				}
				
				health -= finalTakenD;
				checkIfEDead();
				
			}
		}
		else { // IF SUCCESSFUL DODGE
			
			ArenaFight.GameWindow.consoleOut("The enemy dodged your attack!");
			
		}
		
}

private void checkIfEDead() {
	
	if (health <= 0) {
		ArenaFight.GameWindow.consoleOut("You defeated the enemy!");
		ArenaFight.GameWindow.enemyMap.remove(ArenaFight.GameWindow.coordToHKey(xpos,  ypos));
		health = 0;
		ArenaFight.GameWindow.enemiesKilled++;
		ArenaFight.player.levelUp();
		
	}
	
}


}
