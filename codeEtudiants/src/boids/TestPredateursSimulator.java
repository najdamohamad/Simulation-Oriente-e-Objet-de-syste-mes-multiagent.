package boids;

import java.awt.Color;

import gui.GUISimulator;

public class TestPredateursSimulator {

	public static void main(String[] args) {
		GUISimulator gui = new GUISimulator(500, 500, Color.BLACK);
		PredateursSimulator simulator = new PredateursSimulator(20,gui);
		gui.setSimulable(simulator);
	}


}
