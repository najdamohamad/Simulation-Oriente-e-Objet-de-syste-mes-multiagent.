package jeuDeLaVieConway;

import java.awt.Color;

import gui.GUISimulator;

public class TestGameOfLifeSimulator {

	public static void main(String[] args) {
		GUISimulator gui = new GUISimulator(500, 500, Color.WHITE);
		//choisir un entier entre 1 et 4
		int choix_forme =1;
		GameOfLifeSimulator conwaySimulator = new GameOfLifeSimulator(gui,choix_forme);
		gui.setSimulable(conwaySimulator);
	}

}
