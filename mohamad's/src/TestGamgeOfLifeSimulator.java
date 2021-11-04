
import java.awt.Color;

import gui.GUISimulator;

public class TestGameOfLifeSimulator {

	public static void main(String[] args) {
		GUISimulator gui = new GUISimulator(500, 500, Color.BLACK);
		GameOfLifeSimulator conwaySimulator = new GameOfLifeSimulator(gui);
		gui.setSimulable(conwaySimulator);
	}

}
