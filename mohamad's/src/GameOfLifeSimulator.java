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
	}

	@Override
	public void next() {
		conway.move();
		conway.dessiner(gui);
	}

	@Override
	public void restart() {
		conway.init();
		conway.dessiner(gui);
	}
	
	
	
}
