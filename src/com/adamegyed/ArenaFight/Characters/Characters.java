package com.adamegyed.ArenaFight.Characters;

import java.util.Random;
import com.adamegyed.ArenaFight.*;
import com.adamegyed.*;
import javax.swing.JOptionPane;

public class Characters {
	
	public int health;
	public int strength;
	public int defense;
	public int agility;
	public int level;
	public int potions = 0;
	public String name;
    public String clss;
	
	public int xpos;
	public int ypos;
	
	protected int maxHealth;
	
	public Characters(){ // normal constructor
		
	}
	
	public int getAttackDamage() { //Base return
		return 0;
	} //needed here to have it be replaced by method from subclass
	
	public void dealDamageToPlayer(int whatever) {

	} //again, unnecessary function used as filler until replaced by function from subclass
	
	public Characters(int setHealth, int setStrength, int setDefense, int setAgility, int setLevel) { //Overloaded constructor
		health = setHealth;
		strength = setStrength;
		defense = setDefense;
		agility = setAgility;
		level = setLevel;
	}
	
	protected int getAttackFlux() {
		
		//Have the attack strength fluctuate by +- 15%
		int finalDamage = 0;
		Random getRand = new Random();
		double rFluct = getRand.nextDouble() * 0.15;
		boolean side = getRand.nextBoolean();
		if (!side) rFluct = -rFluct;
		rFluct++;
		finalDamage = (int) (strength * rFluct);
		
		return finalDamage;
	}
	
	
	protected int getDefenseFlux() {
		
		//Have the defense strength fluctuate by +- 8%
		int finalBlocked = 0;
		Random getRand = new Random();
		double rFluct = getRand.nextDouble() * 0.08;
		boolean side = getRand.nextBoolean();
		if (!side) rFluct = -rFluct;
		rFluct++;
		finalBlocked = (int) ((defense * rFluct) * 0.7);
		
		return finalBlocked;
	}

	
	
	public void drinkPotion() {
		
		if(this.health < maxHealth && potions > 0) {
			this.health = maxHealth;
			potions--;
			ArenaFight.GameWindow.consoleOut("You drank a potion and healed fully!");
		}
		else if (this.health >= maxHealth) {
			health = maxHealth;
			ArenaFight.GameWindow.consoleOut("You are already at max health!");
		}
		else if (potions <= 0) {
			potions = 0;
			ArenaFight.GameWindow.consoleOut("You have no potions!");
		}
	}
	
	public int getStat (String stat) {
		if (stat.equalsIgnoreCase("health")) {
			return this.health;
		}
		else if (stat.equalsIgnoreCase("strength")) {
			return this.strength;
		}
		else if (stat.equalsIgnoreCase("defense")) {
			return this.defense;
		}
		else if (stat.equalsIgnoreCase("agility")) {
			return this.agility;
		}
		else if (stat.equalsIgnoreCase("potions")) {
			return this.potions;
		}
		else if (stat.equalsIgnoreCase("level")) {
			return this.level;
		}
		else if (stat.equalsIgnoreCase("maxhealth")) {
			return this.maxHealth;
		}
		else {
            return -1;
        }
		
	}

    public void normalizeLocation() {

        if (xpos > 4) {
            xpos = 4;
        }
        if (xpos < 0) {
            xpos = 0;
        }
        if (ypos > 4) {
            ypos = 4;
        }
        if (ypos < 0) {
            ypos = 0;
        }

    }
    
    protected void checkIfPDead() {
    	
    	if (this.health <= 0) {
    		this.health = 0;
    		JOptionPane.showMessageDialog(null,"You died!","U DED",JOptionPane.PLAIN_MESSAGE);
    	}
    	
    }
    
    protected void levelUp() {
    	
    	ArenaFight.GameWindow.consoleOut("You leveled up!");
    	level++;
    	health += 5;
    	maxHealth += 5;
    	strength += 3;
    	defense += 3;
    	agility++;
    	
    	
    }
	
	

}
