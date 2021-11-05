package segregationSchelling;

import java.awt.Color;

import gui.GUISimulator;

public class TestSegregationSimulator {

	public static void main(String[] args) {
		int nbCouleurs=3;
		int seuil=3;
		GUISimulator gui = new GUISimulator(500, 500, Color.BLACK);
		SegregationSimulator schellingSimulator = new SegregationSimulator(gui, nbCouleurs, seuil);
		gui.setSimulable(schellingSimulator);

	}

}
