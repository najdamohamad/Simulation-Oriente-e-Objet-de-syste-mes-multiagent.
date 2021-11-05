package segregationSchelling;

import gui.GUISimulator;
import gui.Simulable;

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
		schelling.dessiner(gui);
	}

	@Override
	public void next() {
		schelling.move();
		schelling.dessiner(gui);
		
	}

	@Override
	public void restart() {
		schelling.reinitialiser();
		schelling.dessiner(gui);
		
	}

}
