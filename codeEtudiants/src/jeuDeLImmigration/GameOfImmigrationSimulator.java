package jeuDeLImmigration;

import event.EventManager;
import event.GameOfLifeEvent;
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
		EventManager.get().addEvent(new GameOfLifeEvent(0, immigration));
	}

	@Override
	public void next() {
		EventManager.get().next();
		immigration.dessiner(gui);
	}

	@Override
	public void restart() {
		immigration.reinitialiser();
		immigration.dessiner(gui);
	}

}
