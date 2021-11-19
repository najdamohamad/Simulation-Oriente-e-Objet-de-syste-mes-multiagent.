package segregationSchelling;

import event.EventManager;
import event.GameOfLifeEvent;
import gui.GUISimulator;
import gui.Simulable;
import gui.Rectangle;
import java.awt.Color;

public class SegregationSimulator implements Simulable {
	private Segregation schelling;
	GUISimulator gui;

	/**
	*@param gui Fenêtre graphique de la simulation.
	*@param nbCouleur Nombre de couleur pris en charge par la simulation
	*@param seuil Paramètre de segregation d'une cellule. Si la cellule a plus de seuil voisin de couleur différente alors elle devient vacante.
	*/
	public SegregationSimulator(GUISimulator gui,int nbCouleur,int seuil) {
		this.gui = gui;
		schelling = new Segregation(gui.getPanelHeight(), nbCouleur, seuil);
		dessiner(gui);
		EventManager.get().addEvent(new GameOfLifeEvent(0, schelling));
	}

	/**
	*Représente les elements de la matrice de simulation dans la fenêtre graphique.
	*/
	public void dessiner(GUISimulator gui) {
		int size = this.schelling.getSize();
		gui.reset();
		for (int i = 0; i < size; i+=10) {
			for (int j = 0; j < size; j+=10) {
				int k=this.schelling.getMatriceIm()[i][j];
				Color couleur = schelling.getListeCouleurs()[k];
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
		schelling.reinitialiser();
		dessiner(gui);

	}

}
