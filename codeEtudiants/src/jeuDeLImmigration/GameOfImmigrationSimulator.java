package jeuDeLImmigration;

import java.awt.Color;

import event.EventManager;
import event.GameOfLifeEvent;
import gui.GUISimulator;
import gui.Rectangle;
import gui.Simulable;

public class GameOfImmigrationSimulator implements Simulable {
	private GameOfImmigration immigration;
	GUISimulator gui;

	/**
	*@param gui Fenêtre graphique de la simulation.
	*@param nbEtats Nombre d'etats pris charge par la simulation
	*/
	public GameOfImmigrationSimulator(GUISimulator gui, int nbEtats) {
		this.gui=gui;
		immigration = new GameOfImmigration(gui.getPanelHeight(), nbEtats);
		dessiner(gui);
		EventManager.get().addEvent(new GameOfLifeEvent(0, immigration));
	}

	/**
	*Représente les elements de la matrice de simulation dans la fenêtre graphique.
	*/
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

	/**
	*Incrémente la simulation d'un pas.
	*/
	@Override
	public void next() {
		EventManager.get().next();
		dessiner(gui);
	}

	/**
	*Réinitialise la simulation.
	*/
	@Override
	public void restart() {
		immigration.reinitialiser();
		dessiner(gui);
	}

}
