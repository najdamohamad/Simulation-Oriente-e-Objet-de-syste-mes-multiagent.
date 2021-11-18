package simulateurBalles;

import event.BallsEvent;
import event.EventManager;
import gui.GUISimulator;
import gui.Simulable;

public class BallsSimulator implements Simulable{
	private Balls balles;
	
	public BallsSimulator(int nombre,GUISimulator gui) {
		balles = new Balls(nombre,gui);
		balles.init();
		EventManager.get().addEvent(new BallsEvent(0, balles));
		}
	
	@Override
	public void next() {
		EventManager.get().next();
		System.out.println(balles.toString());
		balles.dessiner();
	}

	@Override
	public void restart() {
		balles.reInit();
		System.out.println(balles.toString());
		balles.dessiner();
	}
	
}
