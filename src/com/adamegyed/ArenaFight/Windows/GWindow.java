package com.adamegyed.ArenaFight.Windows;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Random;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;

import com.adamegyed.ArenaFight.ArenaFight;
import com.adamegyed.ArenaFight.Characters.LizardMan;
import com.adamegyed.ArenaFight.Characters.Mage;
import com.adamegyed.ArenaFight.Characters.Rogue;
import com.adamegyed.ArenaFight.Characters.Warrior;

public class GWindow extends JFrame implements ActionListener {
	
	//Global Components
	JTextArea map;
	JTextArea aConsole;
	
	JScrollPane cScroll;
	
    JButton loadPlayer;
    JButton reloadMap;

    JButton bUp;
    JButton bDown;
    JButton bLeft;
    JButton bRight;
    
    JButton bAttack;
    JButton bHeal;
    JButton bDrinkPotion;
    
    JButton bResizeW;

    char[] pMD = new char[25]; // player map display
    char[] eMD = new char[25]; // enemy map display

    JLabel lname;
    JLabel levelcls;
    JLabel lhealth;
    JLabel lstrength;
    JLabel ldefense;
    JLabel lagility;
    JLabel lpotions;
    
    JLabel LEname;
    JLabel LElevelclss;
    JLabel LEhealth;
    JLabel LEstrength;
    JLabel LEdefense;
    JLabel LEagility;
    JLabel LEpotions;

    JPanel mapPanel;
    JPanel playerPanel;
    JPanel buttonPanel;
    JPanel enemyPanel;
    JPanel consolePanel;

    public Hashtable<String, LizardMan> enemyMap = new Hashtable<String, LizardMan>();
    
    LizardMan lm1;
    LizardMan lm2;
    LizardMan lm3;
    LizardMan lm4;
    LizardMan lking;
    LizardMan lw1;
    LizardMan lw2;
    LizardMan lw3;
    
    LizardMan spawnEnemyInPlace;
    
    public int enemiesKilled = 0;
    boolean hasShownWinMessage = false;
    
    String welcomeMessage = "Welcome to Arena Fight!!\nYou start in the center of the Arena. Move around and try to defeat all the enemy Lizardmen."
    		+ "\nOn the map, L is a Lizardman, W is a Lizard Warrior, and K is the Lizard King. \nDefeat enemies to level up"
    		+ " and raise you stats. Defeat all enemies to win!!";

    
    // LizardMan lm1 = new LizardMan();
    // when finding an instance of LizardMan by coordinates, type:
    // LizardMan maybeEnemy = enemyMap.get(coordToHKey(x,y));
    // if (maybeEnemy != null ) {
    //     maybeEnemy.stuff();
    // } else {
    //   ; // nobody was there
    // }
    //
    // lm1.xpos = 1;
    // lm1.ypos = 3;
    //
    //
    //
    //


	
	//Constructor
	public GWindow() {
		super("Arena Fight!!");
		init();
		this.setVisible(false);
		this.setSize(600, 600);
		this.setResizable(true);
        this.setLocationRelativeTo(null);
        
        initEnemies();
	}
	
	//Add Components
	void init() {

        //Labels

        // Player stat labels, blank initialization
        lname = new JLabel("  Name");
        levelcls = new JLabel("Lv. 1 Class");
        lhealth = new JLabel("  Health: -1");
        lstrength = new JLabel("Strength: -1");
        ldefense = new JLabel("  Defense: -1");
        lagility = new JLabel("Agility: -1");
        lpotions = new JLabel("  Potions: -1");

        //Enemy statpanel labels
		LEname = new JLabel("Name");
		LElevelclss = new JLabel("Level -1 Class  ");
		LEhealth = new JLabel("Health: -1");
        LEstrength = new JLabel("Strength: -1  ");
        LEdefense = new JLabel("Defense: -1");
        LEagility = new JLabel("Agility: -1  ");

        
        // JLabel blankLabel = new JLabel(":)"); // was previously used for testing



		// Map
		map = new JTextArea(16,21);
		map.setEditable(false);
		map.setFont(new Font("Consolas", Font.BOLD, 18));
		map.setText("+---+---+---+---+---+\n"
				+   "|   |   |   |   |   |\n"
				+   "|   |   |   |   |   |\n"
				+ 	"+---+---+---+---+---+\n"
				+   "|   |   |   |   |   |\n"
				+   "|   |   |   |   |   |\n"
				+ 	"+---+---+---+---+---+\n"
				+   "|   |   | P |   |   |\n"
				+   "|   |   |   |   |   |\n"
				+ 	"+---+---+---+---+---+\n"
				+   "|   |   |   |   |   |\n"
				+   "|   |   |   |   |   |\n"
				+ 	"+---+---+---+---+---+\n"
				+   "|   |   |   |   |   |\n"
				+   "|   |   |   |   |   |\n"
				+ 	"+---+---+---+---+---+");
		
		aConsole = new JTextArea(8,40);
		aConsole.setEditable(false);
		aConsole.setFont(new Font("Consolas", Font.PLAIN, 17));
		aConsole.setText("Welcome to Arena Fight!!\n");
		cScroll = new JScrollPane(aConsole,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        //Buttons
        loadPlayer = new JButton("Load Player");
        loadPlayer.addActionListener(this);
        reloadMap = new JButton("Reload Map");
        reloadMap.addActionListener(this);

        bUp = new JButton("North");
        bDown = new JButton("South");
        bLeft = new JButton("West");
        bRight = new JButton("East");
        bUp.addActionListener(this);
        bDown.addActionListener(this);
        bLeft.addActionListener(this);
        bRight.addActionListener(this);
        
        bAttack = new JButton("Attack");
        bHeal = new JButton("Heal");
        bAttack.addActionListener(this);
        bHeal.addActionListener(this);
        bDrinkPotion = new JButton("Drink Potion");
        bDrinkPotion.addActionListener(this);
        
        bResizeW = new JButton("Reset Window");
        bResizeW.addActionListener(this);

        //Panels
		mapPanel = new JPanel();
        playerPanel = new JPanel(new GridLayout(4,2));
        enemyPanel = new JPanel(new GridLayout(4,2));
        buttonPanel = new JPanel(new GridLayout(3,3));
        consolePanel = new JPanel(new GridLayout(1,1));
		
		//Add components to Panels
		mapPanel.add(map);

        playerPanel.add(lname);
        playerPanel.add(levelcls);
        playerPanel.add(lhealth);
        playerPanel.add(lstrength);
        playerPanel.add(ldefense);
        playerPanel.add(lagility);
        playerPanel.add(lpotions);
        playerPanel.add(loadPlayer);
        
        enemyPanel.add(LEname);
        enemyPanel.add(LElevelclss);
        enemyPanel.add(LEhealth);
        enemyPanel.add(LEstrength);
        enemyPanel.add(LEdefense);
        enemyPanel.add(LEagility);

        buttonPanel.add(reloadMap);
        buttonPanel.add(bUp);
        buttonPanel.add(bHeal);
        buttonPanel.add(bLeft);
        buttonPanel.add(bAttack); // Middle Button - will be attack
        buttonPanel.add(bRight);
        buttonPanel.add(bResizeW);
        buttonPanel.add(bDown);
        buttonPanel.add(bDrinkPotion);
        
        consolePanel.add(cScroll);



		//Add Borders and Colors to Panels
		Border mapBorder = BorderFactory.createLineBorder(Color.BLACK, 4, true);
		mapPanel.setBorder(mapBorder);
		

        BorderLayout GWBordLay = new BorderLayout();
        GWBordLay.setHgap(2);

		this.setLayout(GWBordLay);
		
		this.add(mapPanel, BorderLayout.CENTER);
        this.add(playerPanel,BorderLayout.WEST);
        this.add(enemyPanel, BorderLayout.EAST);
        this.add(buttonPanel, BorderLayout.SOUTH);
        this.add(consolePanel, BorderLayout.PAGE_START);
		
	}

	public void actionPerformed(ActionEvent e) {
        if(e.getSource() == loadPlayer) {
            initPlayer();
        }
        else if (e.getSource() == reloadMap) {
            mapUpdate();
            
        }
        else if (e.getSource() == bUp) {
            ArenaFight.player.ypos++;
            ArenaFight.player.normalizeLocation();
            endOfTurn();
        }
        else if (e.getSource() == bDown) {
            ArenaFight.player.ypos--;
            ArenaFight.player.normalizeLocation();
            endOfTurn();
        }
        else if (e.getSource() == bLeft) {
            ArenaFight.player.xpos--;
            ArenaFight.player.normalizeLocation();
            endOfTurn();
        }
        else if (e.getSource() == bRight) {
            ArenaFight.player.xpos++;
            ArenaFight.player.normalizeLocation();
            endOfTurn();
        }
        else if (e.getSource() == bResizeW) {
        	this.pack();
        	this.setLocationRelativeTo(null);
        }
        else if (e.getSource() == bHeal) {
        	if (ArenaFight.player.health != ArenaFight.player.getStat("maxHealth")) {
        	ArenaFight.player.health += 15;
        	if (ArenaFight.player.health > ArenaFight.player.getStat("maxhealth")) {
        		ArenaFight.player.health = ArenaFight.player.getStat("maxhealth");
        		consoleOut("You healed fully");
        		endOfTurn();
        	}
        	else consoleOut("You healed 15 HP.");
        	endOfTurn();
        	}
        	else consoleOut("You are already at full health!");
        	endOfTurn();
        }
        else if (e.getSource() == bAttack) {
        	playerAttack();
        	endOfTurn();
        	
        }
        else if (e.getSource() == bDrinkPotion) {
        	ArenaFight.player.drinkPotion();
        	endOfTurn();
        	
        }
        


		
	}


    private void mapUpdate() {
        clearPMap();
        findPMap();
        
        clearEMap();
        findEMap();

        displayMap();
    }

    private void findPMap() {

        if (ArenaFight.player.ypos == 0) {
            if (ArenaFight.player.xpos == 0) {
                pMD[0] = 'P';
            } else if (ArenaFight.player.xpos == 1) {
                pMD[1] = 'P';
            } else if (ArenaFight.player.xpos == 2) {
                pMD[2] = 'P';
            } else if (ArenaFight.player.xpos == 3) {
                pMD[3] = 'P';
            } else if (ArenaFight.player.xpos == 4) {
                pMD[4] = 'P';
            }
        }
        else if (ArenaFight.player.ypos == 1) {
            if (ArenaFight.player.xpos == 0) {
                pMD[5] = 'P';
            } else if (ArenaFight.player.xpos == 1) {
                pMD[6] = 'P';
            } else if (ArenaFight.player.xpos == 2) {
                pMD[7] = 'P';
            } else if (ArenaFight.player.xpos == 3) {
                pMD[8] = 'P';
            } else if (ArenaFight.player.xpos == 4) {
                pMD[9] = 'P';
            }
        }
        else if (ArenaFight.player.ypos == 2) {
            if (ArenaFight.player.xpos == 0) {
                pMD[10] = 'P';
            } else if (ArenaFight.player.xpos == 1) {
                pMD[11] = 'P';
            } else if (ArenaFight.player.xpos == 2) {
                pMD[12] = 'P';
            } else if (ArenaFight.player.xpos == 3) {
                pMD[13] = 'P';
            } else if (ArenaFight.player.xpos == 4) {
                pMD[14] = 'P';
            }
        }
        else if (ArenaFight.player.ypos == 3) {
            if (ArenaFight.player.xpos == 0) {
                pMD[15] = 'P';
            } else if (ArenaFight.player.xpos == 1) {
                pMD[16] = 'P';
            } else if (ArenaFight.player.xpos == 2) {
                pMD[17] = 'P';
            } else if (ArenaFight.player.xpos == 3) {
                pMD[18] = 'P';
            } else if (ArenaFight.player.xpos == 4) {
                pMD[19] = 'P';
            }
        }
        else if (ArenaFight.player.ypos == 4) {
            if (ArenaFight.player.xpos == 0) {
                pMD[20] = 'P';
            } else if (ArenaFight.player.xpos == 1) {
                pMD[21] = 'P';
            } else if (ArenaFight.player.xpos == 2) {
                pMD[22] = 'P';
            } else if (ArenaFight.player.xpos == 3) {
                pMD[23] = 'P';
            } else if (ArenaFight.player.xpos == 4) {
                pMD[24] = 'P';
            }
        }


    }

    private void clearPMap() {
        for(int i = 0; i < 25; i++) {
            pMD[i] = ' ';
        }

    }
    
    private void clearEMap() {
    	
    	for(int i = 0; i < 25; i++) {
    		eMD[i] = ' ';
    	}
    	
    }
    
    private void findEMap() {
    	
    	
        // LizardMan maybeEnemy = enemyMap.get(coordToHKey(x,y));
        // if (maybeEnemy != null ) {
        //     maybeEnemy.stuff();
        // } else {
        //   ; // nobody was there
        // }
    	
    	LizardMan maybeEnemy;
    	
    	maybeEnemy = enemyMap.get(coordToHKey(0,0));
    	if (maybeEnemy!= null) {
    		if (maybeEnemy.clss.equals("Lizardman")) {
        		eMD[0] = 'L';	
    		}
    		else if (maybeEnemy.clss.equals("Lizard King")) {
    			eMD[0] = 'K';
    		}
    		else if (maybeEnemy.clss.equals("Lizard Warrior")) {
    			eMD[0] = 'W';
    		}
    	}
    	maybeEnemy = enemyMap.get(coordToHKey(1,0));
    	if (maybeEnemy!= null) {
    		if (maybeEnemy.clss.equals("Lizardman")) {
        		eMD[1] = 'L';	
    		}
    		else if (maybeEnemy.clss.equals("Lizard King")) {
    			eMD[1] = 'K';
    		}
    		else if (maybeEnemy.clss.equals("Lizard Warrior")) {
    			eMD[1] = 'W';
    		}
    	}
    	maybeEnemy = enemyMap.get(coordToHKey(2,0));
    	if (maybeEnemy!= null) {
    		if (maybeEnemy.clss.equals("Lizardman")) {
        		eMD[2] = 'L';	
    		}
    		else if (maybeEnemy.clss.equals("Lizard King")) {
    			eMD[2] = 'K';
    		}
    		else if (maybeEnemy.clss.equals("Lizard Warrior")) {
    			eMD[2] = 'W';
    		}
    	}
    	maybeEnemy = enemyMap.get(coordToHKey(3,0));
    	if (maybeEnemy!= null) {
    		if (maybeEnemy.clss.equals("Lizardman")) {
        		eMD[3] = 'L';	
    		}
    		else if (maybeEnemy.clss.equals("Lizard King")) {
    			eMD[3] = 'K';
    		}
    		else if (maybeEnemy.clss.equals("Lizard Warrior")) {
    			eMD[3] = 'W';
    		}
    	}
    	maybeEnemy = enemyMap.get(coordToHKey(4,0));
    	if (maybeEnemy!= null) {
    		if (maybeEnemy.clss.equals("Lizardman")) {
        		eMD[4] = 'L';	
    		}
    		else if (maybeEnemy.clss.equals("Lizard King")) {
    			eMD[4] = 'K';
    		}
    		else if (maybeEnemy.clss.equals("Lizard Warrior")) {
    			eMD[4] = 'W';
    		}
    	}
    	maybeEnemy = enemyMap.get(coordToHKey(0,1));
    	if (maybeEnemy!= null) {
    		if (maybeEnemy.clss.equals("Lizardman")) {
        		eMD[5] = 'L';	
    		}
    		else if (maybeEnemy.clss.equals("Lizard King")) {
    			eMD[5] = 'K';
    		}
    		else if (maybeEnemy.clss.equals("Lizard Warrior")) {
    			eMD[5] = 'W';
    		}
    	}
    	maybeEnemy = enemyMap.get(coordToHKey(1,1));
    	if (maybeEnemy!= null) {
    		if (maybeEnemy.clss.equals("Lizardman")) {
        		eMD[6] = 'L';	
    		}
    		else if (maybeEnemy.clss.equals("Lizard King")) {
    			eMD[6] = 'K';
    		}
    		else if (maybeEnemy.clss.equals("Lizard Warrior")) {
    			eMD[6] = 'W';
    		}
    	}
    	maybeEnemy = enemyMap.get(coordToHKey(2,1));
    	if (maybeEnemy!= null) {
    		if (maybeEnemy.clss.equals("Lizardman")) {
        		eMD[7] = 'L';	
    		}
    		else if (maybeEnemy.clss.equals("Lizard King")) {
    			eMD[7] = 'K';
    		}
    		else if (maybeEnemy.clss.equals("Lizard Warrior")) {
    			eMD[7] = 'W';
    		}
    	}
    	maybeEnemy = enemyMap.get(coordToHKey(3,1));
    	if (maybeEnemy!= null) {
    		if (maybeEnemy.clss.equals("Lizardman")) {
        		eMD[8] = 'L';	
    		}
    		else if (maybeEnemy.clss.equals("Lizard King")) {
    			eMD[8] = 'K';
    		}
    		else if (maybeEnemy.clss.equals("Lizard Warrior")) {
    			eMD[8] = 'W';
    		}
    	}
    	maybeEnemy = enemyMap.get(coordToHKey(4,1));
    	if (maybeEnemy!= null) {
    		if (maybeEnemy.clss.equals("Lizardman")) {
        		eMD[9] = 'L';	
    		}
    		else if (maybeEnemy.clss.equals("Lizard King")) {
    			eMD[9] = 'K';
    		}
    		else if (maybeEnemy.clss.equals("Lizard Warrior")) {
    			eMD[9] = 'W';
    		}
    	}
    	maybeEnemy = enemyMap.get(coordToHKey(0,2));
    	if (maybeEnemy!= null) {
    		if (maybeEnemy.clss.equals("Lizardman")) {
        		eMD[10] = 'L';	
    		}
    		else if (maybeEnemy.clss.equals("Lizard King")) {
    			eMD[10] = 'K';
    		}
    		else if (maybeEnemy.clss.equals("Lizard Warrior")) {
    			eMD[10] = 'W';
    		}
    	}
    	maybeEnemy = enemyMap.get(coordToHKey(1,2));
    	if (maybeEnemy!= null) {
    		if (maybeEnemy.clss.equals("Lizardman")) {
        		eMD[11] = 'L';	
    		}
    		else if (maybeEnemy.clss.equals("Lizard King")) {
    			eMD[11] = 'K';
    		}
    		else if (maybeEnemy.clss.equals("Lizard Warrior")) {
    			eMD[11] = 'W';
    		}
    	}
    	maybeEnemy = enemyMap.get(coordToHKey(2,2));
    	if (maybeEnemy!= null) {
    		if (maybeEnemy.clss.equals("Lizardman")) {
        		eMD[12] = 'L';	
    		}
    		else if (maybeEnemy.clss.equals("Lizard King")) {
    			eMD[12] = 'K';
    		}
    		else if (maybeEnemy.clss.equals("Lizard Warrior")) {
    			eMD[12] = 'W';
    		}
    	}
    	maybeEnemy = enemyMap.get(coordToHKey(3,2));
    	if (maybeEnemy!= null) {
    		if (maybeEnemy.clss.equals("Lizardman")) {
        		eMD[13] = 'L';	
    		}
    		else if (maybeEnemy.clss.equals("Lizard King")) {
    			eMD[13] = 'K';
    		}
    		else if (maybeEnemy.clss.equals("Lizard Warrior")) {
    			eMD[13] = 'W';
    		}
    	}
    	maybeEnemy = enemyMap.get(coordToHKey(4,2));
    	if (maybeEnemy!= null) {
    		if (maybeEnemy.clss.equals("Lizardman")) {
        		eMD[14] = 'L';	
    		}
    		else if (maybeEnemy.clss.equals("Lizard King")) {
    			eMD[14] = 'K';
    		}
    		else if (maybeEnemy.clss.equals("Lizard Warrior")) {
    			eMD[14] = 'W';
    		}
    	}
    	maybeEnemy = enemyMap.get(coordToHKey(0,3));
    	if (maybeEnemy!= null) {
    		if (maybeEnemy.clss.equals("Lizardman")) {
        		eMD[15] = 'L';	
    		}
    		else if (maybeEnemy.clss.equals("Lizard King")) {
    			eMD[15] = 'K';
    		}
    		else if (maybeEnemy.clss.equals("Lizard Warrior")) {
    			eMD[15] = 'W';
    		}
    	}
    	maybeEnemy = enemyMap.get(coordToHKey(1,3));
    	if (maybeEnemy!= null) {
    		if (maybeEnemy.clss.equals("Lizardman")) {
        		eMD[16] = 'L';	
    		}
    		else if (maybeEnemy.clss.equals("Lizard King")) {
    			eMD[16] = 'K';
    		}
    		else if (maybeEnemy.clss.equals("Lizard Warrior")) {
    			eMD[16] = 'W';
    		}
    	}
    	maybeEnemy = enemyMap.get(coordToHKey(2,3));
    	if (maybeEnemy!= null) {
    		if (maybeEnemy.clss.equals("Lizardman")) {
        		eMD[17] = 'L';	
    		}
    		else if (maybeEnemy.clss.equals("Lizard King")) {
    			eMD[17] = 'K';
    		}
    		else if (maybeEnemy.clss.equals("Lizard Warrior")) {
    			eMD[17] = 'W';
    		}
    	}
    	maybeEnemy = enemyMap.get(coordToHKey(3,3));
    	if (maybeEnemy!= null) {
    		if (maybeEnemy.clss.equals("Lizardman")) {
        		eMD[18] = 'L';	
    		}
    		else if (maybeEnemy.clss.equals("Lizard King")) {
    			eMD[18] = 'K';
    		}
    		else if (maybeEnemy.clss.equals("Lizard Warrior")) {
    			eMD[18] = 'W';
    		}
    	}
    	maybeEnemy = enemyMap.get(coordToHKey(4,3));
    	if (maybeEnemy!= null) {
    		if (maybeEnemy.clss.equals("Lizardman")) {
        		eMD[19] = 'L';	
    		}
    		else if (maybeEnemy.clss.equals("Lizard King")) {
    			eMD[19] = 'K';
    		}
    		else if (maybeEnemy.clss.equals("Lizard Warrior")) {
    			eMD[19] = 'W';
    		}
    	}
    	maybeEnemy = enemyMap.get(coordToHKey(0,4));
    	if (maybeEnemy!= null) {
    		if (maybeEnemy.clss.equals("Lizardman")) {
        		eMD[20] = 'L';	
    		}
    		else if (maybeEnemy.clss.equals("Lizard King")) {
    			eMD[20] = 'K';
    		}
    		else if (maybeEnemy.clss.equals("Lizard Warrior")) {
    			eMD[20] = 'W';
    		}
    	}
    	maybeEnemy = enemyMap.get(coordToHKey(1,4));
    	if (maybeEnemy!= null) {
    		if (maybeEnemy.clss.equals("Lizardman")) {
        		eMD[21] = 'L';	
    		}
    		else if (maybeEnemy.clss.equals("Lizard King")) {
    			eMD[21] = 'K';
    		}
    		else if (maybeEnemy.clss.equals("Lizard Warrior")) {
    			eMD[21] = 'W';
    		}
    	}
    	maybeEnemy = enemyMap.get(coordToHKey(2,4));
    	if (maybeEnemy!= null) {
    		if (maybeEnemy.clss.equals("Lizardman")) {
        		eMD[22] = 'L';	
    		}
    		else if (maybeEnemy.clss.equals("Lizard King")) {
    			eMD[22] = 'K';
    		}
    		else if (maybeEnemy.clss.equals("Lizard Warrior")) {
    			eMD[22] = 'W';
    		}
    	}
    	maybeEnemy = enemyMap.get(coordToHKey(3,4));
    	if (maybeEnemy!= null) {
    		if (maybeEnemy.clss.equals("Lizardman")) {
        		eMD[23] = 'L';	
    		}
    		else if (maybeEnemy.clss.equals("Lizard King")) {
    			eMD[23] = 'K';
    		}
    		else if (maybeEnemy.clss.equals("Lizard Warrior")) {
    			eMD[23] = 'W';
    		}
    	}
    	maybeEnemy = enemyMap.get(coordToHKey(4,4));
    	if (maybeEnemy!= null) {
    		if (maybeEnemy.clss.equals("Lizardman")) {
        		eMD[24] = 'L';	
    		}
    		else if (maybeEnemy.clss.equals("Lizard King")) {
    			eMD[24] = 'K';
    		}
    		else if (maybeEnemy.clss.equals("Lizard Warrior")) {
    			eMD[24] = 'W';
    		}
    	}

    	
    	
    	
    	
    }

    private void displayMap() {
        map.setText("+---+---+---+---+---+\n"
                +   "| "+pMD[20]+" | "+pMD[21]+" | "+pMD[22]+" | "+pMD[23]+" | "+pMD[24]+" |\n"
                +   "| "+eMD[20]+" | "+eMD[21]+" | "+eMD[22]+" | "+eMD[23]+" | "+eMD[24]+" |\n"
                + 	"+---+---+---+---+---+\n"
                +   "| "+pMD[15]+" | "+pMD[16]+" | "+pMD[17]+" | "+pMD[18]+" | "+pMD[19]+" |\n"
                +   "| "+eMD[15]+" | "+eMD[16]+" | "+eMD[17]+" | "+eMD[18]+" | "+eMD[19]+" |\n"
                + 	"+---+---+---+---+---+\n"
                +   "| "+pMD[10]+" | "+pMD[11]+" | "+pMD[12]+" | "+pMD[13]+" | "+pMD[14]+" |\n"
                +   "| "+eMD[10]+" | "+eMD[11]+" | "+eMD[12]+" | "+eMD[13]+" | "+eMD[14]+" |\n"
                + 	"+---+---+---+---+---+\n"
                +   "| "+pMD[5]+" | "+pMD[6]+" | "+pMD[7]+" | "+pMD[8]+" | "+pMD[9]+" |\n"
                +   "| "+eMD[5]+" | "+eMD[6]+" | "+eMD[7]+" | "+eMD[8]+" | "+eMD[9]+" |\n"
                + 	"+---+---+---+---+---+\n"
                +   "| "+pMD[0]+" | "+pMD[1]+" | "+pMD[2]+" | "+pMD[3]+" | "+pMD[4]+" |\n"
                +   "| "+eMD[0]+" | "+eMD[1]+" | "+eMD[2]+" | "+eMD[3]+" | "+eMD[4]+" |\n"
                + 	"+---+---+---+---+---+");
        mapPanel.repaint();
    }

    public void initPlayer() {

        if(ArenaFight.characterChoice == 1) {
            ArenaFight.player = new Warrior();
        }
        if (ArenaFight.characterChoice == 2) {
            ArenaFight.player = new Rogue();
        }
        if (ArenaFight.characterChoice== 3) {
            ArenaFight.player = new Mage();
        }

        ArenaFight.player.name = ArenaFight.setName;
        ArenaFight.player.xpos = 2;
        ArenaFight.player.ypos = 2;

        playerPanel.remove(loadPlayer);
        updatePSTPanel();
        updateESTPanel();
        mapUpdate();
        this.pack();
        JOptionPane.showMessageDialog(null, welcomeMessage, "Welcome", JOptionPane.PLAIN_MESSAGE);


    }

    private void updatePSTPanel() {



        lname.setText("  " + ArenaFight.player.name);
        levelcls.setText(" Level " + ArenaFight.player.level + " " + ArenaFight.player.clss);
        lhealth.setText("  Health: " + ArenaFight.player.health);
        lstrength.setText(" Strength: " + ArenaFight.player.strength);
        ldefense.setText("  Defense: " + ArenaFight.player.defense);
        lagility.setText(" Agility: " + ArenaFight.player.agility);
        lpotions.setText("  Potions: " + ArenaFight.player.potions);



        playerPanel.repaint();

    }
    
    private void updateESTPanel() {

        //Update enemy stat panel
    	
    	LizardMan enemyMaybe = enemyMap.get(coordToHKey(ArenaFight.player.xpos,ArenaFight.player.ypos));
    	
    	if (enemyMaybe!=null) { // Enemy Found
    		LEname.setText(enemyMaybe.name);
    		LElevelclss.setText("Level "+enemyMaybe.level+" "+enemyMaybe.clss+"  ");
    		LEhealth.setText("Health: " + enemyMaybe.health);
            LEstrength.setText("Strength: " + enemyMaybe.strength+ "  ");
            LEdefense.setText("Defense: " + enemyMaybe.defense);
            LEagility.setText("Agility: " + enemyMaybe.agility+"  ");
    	}
    	else { //No enemy
    		LEname.setText("Name");
    		LElevelclss.setText("Level -1 Class  ");
    		LEhealth.setText("Health: -1");
            LEstrength.setText("Strength: -1  ");
            LEdefense.setText("Defense: -1");
            LEagility.setText("Agility: -1  ");
    	}
    	
    	enemyPanel.repaint();
    }

    private void endOfTurn() {
    	
    	// Insert enemy attack and wander movements
    	
    	enemyWander(lm1);
    	enemyWander(lm2);
    	enemyWander(lm3);
    	enemyWander(lm4);
    	enemyWander(lking);
    	enemyWander(lw1);
    	enemyWander(lw2);
    	enemyWander(lw3);
    	
    	enemyAttack(lm1);
    	enemyAttack(lm2);
    	enemyAttack(lm3);
    	enemyAttack(lm4);
    	enemyAttack(lking);
    	enemyAttack(lw1);
    	enemyAttack(lw2);
    	enemyAttack(lw3);
    	
    	if (enemiesKilled == 8 && !hasShownWinMessage) {
    		JOptionPane.showMessageDialog(bAttack, "CONGRATULATIONS! You have defeated all enemies in the Arena!", "CONGRATULATIONS", JOptionPane.PLAIN_MESSAGE);
    		hasShownWinMessage = true;
    	}
    	
    	
    	
    	mapUpdate();
    	updatePSTPanel();
    	updateESTPanel();
    	this.pack();
        this.pack();
        //this.setLocationRelativeTo(null);
    }
    
    public String coordToHKey(int x, int y) {

        return "" + x + "_" + y;

    }

    private void initEnemies() {
    	
    	lm1 = new LizardMan();
    	lm1.name = ("George");
    	enemyMap.put(coordToHKey(lm1.xpos, lm1.ypos), lm1);
    	
    	lm2 = new LizardMan();
    	//lm2.name = ("Meep");
        lm2.name = ("CreativeName");
    	spawnEnemyInPlace = enemyMap.get(coordToHKey(lm2.xpos, lm2.ypos));
    	while(spawnEnemyInPlace!=null) {
    		lm2.randomSpawn();
    		spawnEnemyInPlace = enemyMap.get(coordToHKey(lm2.xpos, lm2.ypos));
    	}
    	enemyMap.put(coordToHKey(lm2.xpos, lm2.ypos), lm2);
    	
    	lm3 = new LizardMan();
    	lm3.name = ("Doot");
    	spawnEnemyInPlace = enemyMap.get(coordToHKey(lm3.xpos, lm3.ypos));
    	while(spawnEnemyInPlace!=null) {
    		lm3.randomSpawn();
    		spawnEnemyInPlace = enemyMap.get(coordToHKey(lm3.xpos, lm3.ypos));
    	}
    	enemyMap.put(coordToHKey(lm3.xpos, lm3.ypos), lm3);
    	
    	
    	lm4 = new LizardMan();
    	//lm4.name = ("MemeKiddo");
        lm4.name = ("Pineapple");
    	spawnEnemyInPlace = enemyMap.get(coordToHKey(lm4.xpos, lm4.ypos));
    	while(spawnEnemyInPlace!=null) {
    		lm4.randomSpawn();
    		spawnEnemyInPlace = enemyMap.get(coordToHKey(lm4.xpos, lm4.ypos));
    	}
    	enemyMap.put(coordToHKey(lm4.xpos, lm4.ypos), lm4);
    	
    	
    	lking = new LizardMan();
    	lking.name = ("Wilhelm");
    	lking.makeKing();
    	lking.randomSpawn();
    	spawnEnemyInPlace = enemyMap.get(coordToHKey(lking.xpos, lking.ypos));
    	while(spawnEnemyInPlace != null) {
    		lking.randomSpawn();
    		spawnEnemyInPlace = enemyMap.get(coordToHKey(lking.xpos, lking.ypos));
    	}
    	enemyMap.put(coordToHKey(lking.xpos, lking.ypos), lking);
    	
    	
    	
    	lw1 = new LizardMan();
    	lw1.name = "asdf";
    	lw1.makeWarrior();
    	spawnEnemyInPlace = enemyMap.get(coordToHKey(lw1.xpos, lw1.ypos));
    	while(spawnEnemyInPlace!=null) {
    		lw1.randomSpawn();
    		spawnEnemyInPlace = enemyMap.get(coordToHKey(lw1.xpos, lw1.ypos));
    	}
    	enemyMap.put(coordToHKey(lw1.xpos,lw1.ypos), lw1);
    	
    	lw2 = new LizardMan();
    	//lw2.name = "Wigglesworth";
        lw2.name = "Seven";
        lw2.makeWarrior();
    	spawnEnemyInPlace = enemyMap.get(coordToHKey(lw2.xpos, lw2.ypos));
    	while(spawnEnemyInPlace!=null) {
    		lw2.randomSpawn();
    		spawnEnemyInPlace = enemyMap.get(coordToHKey(lw2.xpos, lw2.ypos));
    	}
    	enemyMap.put(coordToHKey(lw2.xpos,lw2.ypos), lw2);
    	
    	lw3 = new LizardMan();
    	//lw3.name = "NotGTAV";
        lw3.name = "NotGeorge";
    	lw3.makeWarrior();
    	spawnEnemyInPlace = enemyMap.get(coordToHKey(lw3.xpos, lw3.ypos));
    	while(spawnEnemyInPlace!=null) {
    		lw3.randomSpawn();
    		spawnEnemyInPlace = enemyMap.get(coordToHKey(lw3.xpos, lw3.ypos));
    	}
    	enemyMap.put(coordToHKey(lw3.xpos,lw3.ypos), lw3);
    	
    	
    }
    
    private void enemyWander(LizardMan wanderer) {
    		
    	int randDirect = 0; // Random Direction as an int
    	
    	if (wanderer.health > 0 && wanderer.timeToWander == 0) {
            //Only wander if health is more than 0 and it is time to wander
    		
        	randDirect = ArenaFight.randGen.nextInt(4) + 1;
    		
    		
    		if (wanderer.xpos == ArenaFight.player.xpos && wanderer.ypos == ArenaFight.player.ypos) {
    			randDirect = -8; // Don't wander if in same space as player
    			wanderer.timeToWander = 3;
    		}


            //All the commented code was used before the creation of a moveEnemy method. I left it there to see my train of thought
    		
    		if (randDirect == 1) {
    			moveEnemy(wanderer, "north");
    			/*
    			enemyMaybe = enemyMap.get(coordToHKey(wanderer.xpos, wanderer.ypos));
    			if (!(enemyMaybe.name.equals(wanderer.name))) {
    				moveEnemy(wanderer, "south");
    				*/
    			wanderer.timeToWander = 3;
    			}
    			
    		}
    		if (randDirect == 2) {
    			moveEnemy(wanderer,"south");
    			/*
    			enemyMaybe = enemyMap.get(coordToHKey(wanderer.xpos, wanderer.ypos));
    			if (!(enemyMaybe.name.equals(wanderer.name))) {
    				moveEnemy(wanderer, "north");
    				
    			} */
    			wanderer.timeToWander = 3;
    			
    		}
    		if (randDirect == 3) {
    			moveEnemy(wanderer, "west");
    			
    			/*
    			enemyMaybe = enemyMap.get(coordToHKey(wanderer.xpos, wanderer.ypos));
    			if (!(enemyMaybe.name.equals(wanderer.name))) {
    				moveEnemy(wanderer, "east");
    				
    			}
    			*/
    			wanderer.timeToWander = 3;
    		}
    		if (randDirect == 4) {
    			moveEnemy(wanderer, "east");
    			/*
    			enemyMaybe = enemyMap.get(coordToHKey(wanderer.xpos, wanderer.ypos));
    			if (!(enemyMaybe.name.equals(wanderer.name))) {
    				moveEnemy(wanderer, "west");
    			}
    			*/
    			wanderer.timeToWander = 3;
    		}


    		
    		
    		
    		//If did not wander, reduce time to wander
    	
    	if (wanderer.health > 0) wanderer.timeToWander--;
    	
    }
    
    private void moveEnemy(LizardMan EMove, String direction) {

        //Move the enemy and reassign them into the enemymap Hashtable

    	LizardMan maybeEnemy;
    	
    	if (direction.equalsIgnoreCase("north")) {
    		maybeEnemy = enemyMap.get(coordToHKey(EMove.xpos, EMove.ypos + 1)); //Check the spot it will be moving to
    		if (maybeEnemy==null) {
    			
    		enemyMap.remove(coordToHKey(EMove.xpos, EMove.ypos));
    		EMove.ypos++;
    		EMove.normalizeLocation();
    		enemyMap.put(coordToHKey(EMove.xpos, EMove.ypos), EMove);
    		
    		}
    	}
    	if (direction.equalsIgnoreCase("south")) {
    		maybeEnemy = enemyMap.get(coordToHKey(EMove.xpos, EMove.ypos - 1));
    		if (maybeEnemy==null) {

    		enemyMap.remove(coordToHKey(EMove.xpos, EMove.ypos));
    		EMove.ypos--;
    		EMove.normalizeLocation();
    		enemyMap.put(coordToHKey(EMove.xpos, EMove.ypos), EMove);
    		}
    		
    	}
    	if (direction.equalsIgnoreCase("east")) {
    		maybeEnemy = enemyMap.get(coordToHKey(EMove.xpos + 1, EMove.ypos));
    		if (maybeEnemy==null) {
    		
    		enemyMap.remove(coordToHKey(EMove.xpos, EMove.ypos));
    		EMove.xpos++;
    		EMove.normalizeLocation();
    		enemyMap.put(coordToHKey(EMove.xpos, EMove.ypos), EMove);
    		}
    	}
    	if (direction.equalsIgnoreCase("west")) {
    		maybeEnemy = enemyMap.get(coordToHKey(EMove.xpos - 1, EMove.ypos));
    		if (maybeEnemy==null) {
    		
    		enemyMap.remove(coordToHKey(EMove.xpos, EMove.ypos));
    		EMove.xpos--;
    		EMove.normalizeLocation();
    		enemyMap.put(coordToHKey(EMove.xpos, EMove.ypos), EMove);
    		}
    	}
    }
    
    public void consoleOut(String content) {

        //Append string to the 'console' output

    	aConsole.append(content + "\n");
    	aConsole.setCaretPosition(aConsole.getDocument().getLength());
    }
    
    private void playerAttack() {
    	
    	LizardMan enemyMaybe;
    	
    	enemyMaybe = enemyMap.get(coordToHKey(ArenaFight.player.xpos, ArenaFight.player.ypos));
    	
    	if (enemyMaybe!=null) {
    		consoleOut("You attack the " + enemyMaybe.clss +".");
    		enemyMaybe.dealDamageToEnemy(ArenaFight.player.getAttackDamage());
    		
    	}
    	else {
    		consoleOut("There is no enemy for you to attack!");
    	}
    	
    }
    
    private void enemyAttack(LizardMan enemy) {
    	
    	if(enemy.xpos == ArenaFight.player.xpos && enemy.ypos == ArenaFight.player.ypos && enemy.health > 0) {
    		consoleOut("The " + enemy.clss + " " +enemy.name+ " attacks you.");
    		ArenaFight.player.dealDamageToPlayer(enemy.getAttackDamage());
    		
    	}
    	
    }


}// End of GWindow Class
