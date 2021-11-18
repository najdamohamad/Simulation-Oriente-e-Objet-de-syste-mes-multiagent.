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
	private int nbCouleurs;
	private int seuil;

	public SegregationSimulator(GUISimulator gui, int nbCouleurs, int seuil) {
		this.gui = gui;
		this.nbCouleurs = nbCouleurs;
		this.seuil = seuil;
		schelling = new Segregation(gui.getPanelHeight(), this.nbCouleurs, this.seuil);
		dessiner(gui);
		EventManager.get().addEvent(new GameOfLifeEvent(0, schelling));
	}

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

	@Override
	public void next() {
		EventManager.get().next();
		dessiner(gui);

	}

	@Override
	public void restart() {
		schelling.reinitialiser();
		dessiner(gui);

	}

}
