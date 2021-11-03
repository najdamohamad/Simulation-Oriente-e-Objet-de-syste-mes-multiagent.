package simulateurBalles;

import java.awt.Point;

import gui.GUISimulator;
import gui.Simulable;

public class BallsSimulator implements Simulable{
	private Balls balles;
	GUISimulator gui;
	
	public BallsSimulator() {
		balles = new Balls();
		balles.add(new Point(0,100));
		balles.add(new Point(100,350));
		balles.add(new Point(360,40));
	}
	
	@Override
	public void next() {
		balles.translate(10, 10);
		System.out.println(balles.toString());
		balles.dessiner(gui);
		System.out.println(gui.getPanelWidth());
	}

	@Override
	public void restart() {
		balles.reInit();
		System.out.println(balles.toString());
		balles.dessiner(gui);
	}
	
}
