package simulateurBalles;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;
import event.BallsEvent;
import event.EventManager;
import gui.GUISimulator;
import gui.Simulable;
import gui.Oval;
import java.awt.Color;

public class BallsSimulator implements Simulable{
	private Balls balles;
	private GUISimulator gui;

	/**
	*Constructeur du simulateur de balles. L'ensemble sera composé de "nombre" balles et affiché dans la fenêtre graphique "gui".
	*@param nombre Nombre de mobile de la simulation.
	*@param gui Fenêtre graphique de la simulation.
	*/
	public BallsSimulator(int nombre,GUISimulator gui) {
		this.gui=gui;
		balles = new Balls(nombre,gui.getPanelHeight(),gui.getPanelWidth());
		balles.init();
		EventManager.get().addEvent(new BallsEvent(0, balles));
		}

	/**
	*Dessine l'ensemble des balles dans le fenêtre graphique.
	*/
	public void dessiner() {
		gui.reset();
		Iterator<Point> it = this.balles.getListeBalles().iterator();
		while(it.hasNext()) {
			Point p = it.next();
			gui.addGraphicalElement(new Oval(p.x,p.y,Color.GREEN,Color.GREEN,20));
		}
	}

	/**
	*Incrémente la simulation d'un pas.
	*/
	@Override
	public void next() {
		EventManager.get().next();
		System.out.println(balles.toString());
		this.dessiner();
	}

	/**
	*Réinitialise la simulation.
	*/
	@Override
	public void restart() {
		balles.reInit();
		System.out.println(balles.toString());
		this.dessiner();
	}

}
