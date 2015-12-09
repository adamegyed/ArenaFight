package com.adamegyed.ArenaFight;

import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.adamegyed.ArenaFight.Characters.*;
import com.adamegyed.ArenaFight.Windows.*;

public class ArenaFight {

	public static int characterChoice = 0;
	public static String setName = "";

	private static CharacterCreate ccWindow = new CharacterCreate();
	public static GWindow GameWindow = new GWindow();

	//Create the player as a blank template
	public static Characters player = new Characters();

	public static Random randGen = new Random();

	public static void main(String[] args) {

		ccWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		GameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);




		//Change the player to match





	} // End of method main



} // End of Class ArenaFight
