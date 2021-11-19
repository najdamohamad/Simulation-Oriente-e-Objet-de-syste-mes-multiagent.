package jeuDeLImmigration;

import event.EventManager;
import event.GameOfLifeEvent;
import gui.GUISimulator;
import gui.Simulable;
import gui.Rectangle;
import java.awt.Color;

public class GameOfImmigrationSimulator implements Simulable {
	private GameOfImmigration immigration;
	GUISimulator gui;
	private int nbEtats;


	public GameOfImmigrationSimulator(GUISimulator gui, int nbEtats) {
		this.gui=gui;
		this.nbEtats=nbEtats;
		immigration = new GameOfImmigration(gui.getPanelHeight(), this.nbEtats);
		dessiner(gui);
		EventManager.get().addEvent(new GameOfLifeEvent(0, immigration));
	}

	public void dessiner(GUISimulator gui) {
		int size = this.immigration.getSize();
		gui.reset();
		for (int i = 0; i < size; i+=10) {
			for (int j = 0; j < size; j+=10) {
				int k=this.immigration.getMatrice()[i][j];
				Color couleur = immigration.getListeCouleurs()[k];
				gui.addGraphicalElement(new Rectangle(i,j,couleur,couleur,10));
			}
		}
	}

	@Override
	public void next() {
		EventManager.get().next();
		dessiner(gui);
	}

	@Override
	public void restart() {
		immigration.reinitialiser();
		dessiner(gui);
	}

}
