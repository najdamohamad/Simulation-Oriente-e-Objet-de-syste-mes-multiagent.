package segregationSchelling;

import java.awt.Color;

import gui.GUISimulator;

public class TestSegregationSimulator {

	public static void main(String[] args) {
		int nbCouleurs=10;
		int seuil=5;
		GUISimulator gui = new GUISimulator(500, 500, Color.WHITE);
		SegregationSimulator schellingSimulator = new SegregationSimulator(gui, nbCouleurs, seuil);
		gui.setSimulable(schellingSimulator);

	}

}
