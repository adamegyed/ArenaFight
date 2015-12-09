package com.adamegyed.ArenaFight.Windows;

import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JRadioButton;

import com.adamegyed.ArenaFight.*;
import com.adamegyed.ArenaFight.Characters.*;

public class CharacterCreate extends JFrame implements ActionListener{
	
	//Global Components
	JTextField name;
	JRadioButton warrior, rogue, mage;
	JButton begin;
	
	//Constructor
	public CharacterCreate() {
		super("Character Creation");
		init();
        //this.setLocationRelativeTo(null);
		this.setVisible(true);
	} //End of Constructor
	
	
	//Add components
	void init() {
		
		// Labels
		JLabel lname = new JLabel("Name: "); //Name Field
		JLabel lclass = new JLabel("Please pick a class:");
		
		// Text Fields
		name = new JTextField(15);
		
		// Radio Buttons
		warrior = new JRadioButton("Warrior - Strong, can heal with potions.");
		rogue = new JRadioButton("Rogue - Quick and Agile, can land critical hits.");
		mage = new JRadioButton("Mage - Powerful, can counter attacks and heal himself");
		
		// Buttons
		begin = new JButton("Begin!");
		
		//Radio Button Group
		ButtonGroup classes = new ButtonGroup();
		classes.add(warrior);
		classes.add(rogue);
		classes.add(mage);
		
		// Panels
		JPanel namePanel = new JPanel();
		JPanel classSelect = new JPanel(new GridLayout(4,1));
		JPanel buttonPanel = new JPanel();

        // Adding components to panels
		namePanel.add(lname);
		namePanel.add(name);
		
		classSelect.add(lclass);
		classSelect.add(warrior);
		classSelect.add(rogue);
		classSelect.add(mage);
		
		buttonPanel.add(begin);
		
		begin.addActionListener(this);
				
				
		this.setLayout(new BorderLayout());
		
		this.add(namePanel, BorderLayout.NORTH);
		this.add(classSelect, BorderLayout.CENTER);
		this.add(buttonPanel, BorderLayout.SOUTH);
		
		this.pack();
		this.setResizable(false);
	}


	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == begin) {
			
			//1 is Warrior, 2 is Rogue, 3 is Mage
			if (name.getText().equals("")) {
				JOptionPane.showMessageDialog(begin, "Please Enter a Name.","Error",JOptionPane.ERROR_MESSAGE);
			}
			else if (warrior.isSelected()) {
				ArenaFight.characterChoice = 1;
				ArenaFight.setName = name.getText();
				ArenaFight.GameWindow.setVisible(true);
                this.setVisible(false);
				ArenaFight.GameWindow.initPlayer();
				this.dispose();
			}
			else if (rogue.isSelected()){
				ArenaFight.characterChoice = 2;
				ArenaFight.setName = name.getText();
				ArenaFight.GameWindow.setVisible(true);
                this.setVisible(false);
                ArenaFight.GameWindow.initPlayer();
				this.dispose();
			}
			else if (mage.isSelected()) {
				ArenaFight.characterChoice = 3;
				ArenaFight.setName = name.getText();
				ArenaFight.GameWindow.setVisible(true);
                this.setVisible(false);
                ArenaFight.GameWindow.initPlayer();
				this.dispose();
				
			}
			else {
				//If No class is chosen, show an error message with the parent of the begin button
				JOptionPane.showMessageDialog(begin, "Please Choose a Class","Error", JOptionPane.ERROR_MESSAGE);
			}
			
			
		}//End of BEGIN Button
		
	} //End of Button Actions
	
	

}
