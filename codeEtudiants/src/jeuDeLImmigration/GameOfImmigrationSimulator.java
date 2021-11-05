package jeuDeLImmigration;

import gui.GUISimulator;
import gui.Simulable;

public class GameOfImmigrationSimulator implements Simulable {
	private GameOfImmigration immigration;
	GUISimulator gui;
	private int nbEtats;
	

	public GameOfImmigrationSimulator(GUISimulator gui, int nbEtats) {
		this.gui=gui;
		this.nbEtats=nbEtats;
		immigration = new GameOfImmigration(gui.getPanelHeight(), this.nbEtats);
		immigration.dessiner(gui);
	}

	@Override
	public void next() {
		immigration.move();
		immigration.dessiner(gui);
	}

	@Override
	public void restart() {
		immigration.reinitialiser();
		immigration.dessiner(gui);
	}

}
