package com.adamegyed.ArenaFight.Characters;

import java.util.Random;

import com.adamegyed.ArenaFight.ArenaFight;

public class Mage extends Characters {
	
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
	public Mage() {
		
		//Set the Mage's stats - health, strength, defense, agility, and level
		super( 105, 38, 39, 9, 1);
        this.clss = "Mage";
        maxHealth = 105;
		
	}
	
	public void dealDamageToPlayer(int enemyAStrength) {
		
		boolean didDodge = false;
		
		int chanceToDodge;
		
		int blockedDamage;
		
		int finalTakenD;
		
		int chanceToCounter;
		
		int healedDamage;
		
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
				
				chanceToCounter = randGen.nextInt(5) + 1;
				finalTakenD = enemyAStrength - blockedDamage;
				
				if(chanceToCounter != 1) { //Failed Counter
					
					ArenaFight.GameWindow.consoleOut("The enemy dealt " + enemyAStrength + " damage, but you "
							+ "blocked " + blockedDamage + " \nand received " + finalTakenD +".");
					health -= finalTakenD;
					checkIfPDead();
				}
				else { //Countered attack
					
					
					ArenaFight.GameWindow.consoleOut("You used your magical powers to counter the attack!");
					
					healedDamage = (int) (finalTakenD * 0.5);
					ArenaFight.GameWindow.consoleOut("THe enemy would have done " + finalTakenD + " damage, however, you healed " + healedDamage + " instead!");
					
					health += healedDamage;
					
					if(health >= maxHealth) {
						health = maxHealth;
						ArenaFight.GameWindow.consoleOut("You healed fully!");
					}
					
					
				}
					
			}
		}
		else { // IF SUCCESSFUL DODGE
			
			ArenaFight.GameWindow.consoleOut("You dodged the enemy's attack!");
			
		}
		
		
		
	}

	
	

}
