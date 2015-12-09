package com.adamegyed.ArenaFight.Characters;

import java.util.Random;

import com.adamegyed.ArenaFight.ArenaFight;

public class Warrior extends Characters {
	
	private Random randGen = new Random();
	
	//Get the attack strength
	public int getAttackDamage() {
		
		/* No Crit Chance
		boolean isCrit = randGen.nextBoolean();
		if(isCrit) {
			damage= damage * 2;
		}
		*/
		
		return this.getAttackFlux();
	}
	
	//Constructor
	public Warrior() {
		
		//Set the Warrior's stats - health, strength, defense, agility, and level
		super( 130, 30, 50, 5, 1);
		//Give warrior two potions
		this.potions = 2;
        this.clss = "Warrior";
        maxHealth = 130;
		
	}
	
	public void dealDamageToPlayer(int enemyAStrength) {
		
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
			if (blockedDamage >= enemyAStrength) {     //Blocked All Damage
				
				ArenaFight.GameWindow.consoleOut("You blocked all damage from the enemy.");
				
			}
			else { // Block is less than attack
				
				finalTakenD = enemyAStrength - blockedDamage;
				ArenaFight.GameWindow.consoleOut("The enemy dealt " + enemyAStrength + " damage, but you "
						+ "blocked " + blockedDamage + " \nand received " + finalTakenD +".");
				health -= finalTakenD;
				checkIfPDead();
				
			}
		}
		else { // IF SUCCESSFUL DODGE
			
			ArenaFight.GameWindow.consoleOut("You dodged the enemy's attack!");
			
		}
		
		
		
	}
	
	

}
