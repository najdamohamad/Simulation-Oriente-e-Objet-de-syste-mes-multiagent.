package jeuDeLaVieConway;

import event.EventManager;
import event.GameOfLifeEvent;
import gui.GUISimulator;
import gui.Simulable;
import gui.Rectangle;
import java.awt.Color;


public class GameOfLifeSimulator implements Simulable {
	private GameOfLife conway;
	GUISimulator gui;

	/**
	*@param gui Fenetre graphique du simulateur.
	*/
	public GameOfLifeSimulator(GUISimulator gui) {
		this.gui=gui;
		conway=new GameOfLife(gui.getPanelHeight());
		conway.init();
		dessiner(gui);
		EventManager.get().addEvent(new GameOfLifeEvent(0, conway));
	}

	/**
	*Représente les elements de la matrice de simulation dans la fenêtre graphique.
	*/
	public void dessiner(GUISimulator gui) {
		gui.reset();
		for (int i = 0; i < conway.getSize(); i+=10) {
			for (int j = 0; j < conway.getSize(); j+=10) {
				if(conway.getMatrice()[i][j]==Etat.Vivant) {
					gui.addGraphicalElement(new Rectangle(i,j,Color.BLUE,Color.BLUE,10));
				}
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
		conway.init();
		dessiner(gui);
	}



}

