package boids;

import java.awt.Color;

import gui.GUISimulator;

public class TestBoidsSimulator {

	public static void main(String[] args) {
		GUISimulator gui = new GUISimulator(500, 500, Color.BLACK);
		BoidsSimulator simulator = new BoidsSimulator(10,gui);
		gui.setSimulable(simulator);
	}


}
