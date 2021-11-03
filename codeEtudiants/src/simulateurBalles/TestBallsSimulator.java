package simulateurBalles;

import java.awt.Color;

import gui.GUISimulator;

public class TestBallsSimulator {

	public static void main(String[] args) {
		BallsSimulator simulator= new BallsSimulator();
		GUISimulator gui = new GUISimulator(500, 500, Color.BLACK);
		simulator.gui=gui;
		gui.setSimulable(simulator);
	}
}
