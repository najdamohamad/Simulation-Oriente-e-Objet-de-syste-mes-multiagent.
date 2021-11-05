package jeuDeLImmigration;

import java.awt.Color;

import gui.GUISimulator;

public class TestGameOfImmigrationSimulator {

	public static void main(String[] args) {
		int nbEtats=5;
		GUISimulator gui = new GUISimulator(500, 500, Color.WHITE);
		GameOfImmigrationSimulator immigrationSimulator = new GameOfImmigrationSimulator(gui, nbEtats);
		gui.setSimulable(immigrationSimulator);

	}

}
