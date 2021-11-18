package jeuDeLaVieConway;

import event.EventManager;
import event.GameOfLifeEvent;
import gui.GUISimulator;
import gui.Simulable;

public class GameOfLifeSimulator implements Simulable {
	private GameOfLife conway;
	GUISimulator gui;
	
	
	public GameOfLifeSimulator(GUISimulator gui) {
		this.gui=gui;
		conway=new GameOfLife(gui.getPanelHeight());
		conway.init();
		conway.dessiner(gui);
		EventManager.get().addEvent(new GameOfLifeEvent(0, conway));
	}

	@Override
	public void next() {
		EventManager.get().next();
		conway.dessiner(gui);
	}

	@Override
	public void restart() {
		conway.init();
		conway.dessiner(gui);
	}
	
	
	
}
