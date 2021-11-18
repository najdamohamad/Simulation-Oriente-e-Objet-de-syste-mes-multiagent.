package boids;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;
import event.BallsEvent;
import event.EventManager;
import gui.GUISimulator;
import gui.Simulable;
import gui.Oval;
import java.awt.Color;

public class BoidsSimulator implements Simulable{
	private Boids boids;
	private GUISimulator gui;

	/**
	*Constructeur du simulateur de boids. L'ensemble sera composé de "nombre" boids et affiché dans la fenêtre graphique "gui".
	*/
	public BoidsSimulator(int nombre,GUISimulator gui) {
		this.gui=gui;
		boids = new Boids(nombre,gui.getPanelHeight(),gui.getPanelWidth());
		boids.init();
		//EventManager.get().addEvent(new BoidsEvent(0, boids));
		}

	/**
	*Dessine l'ensemble des boids dans le fenêtre graphique.
	*/
	public void dessiner() {
		gui.reset();
		ArrayList<Point> boidsList = boids.getListeBalles();
		Iterator<Point> it = boidsList.iterator();
//		int indice=0;
		while(it.hasNext()) {
			Point p = it.next();
//			Point v = vitesses.get(indice);
//			gui.addGraphicalElement(new Triangle(p.x,p.y,v,Color.PINK,Color.PINK,10));
			gui.addGraphicalElement(new Oval(p.x,p.y,Color.GREEN,Color.GREEN,10));
//			indice++;
		}
	}

	/**
	*Incrémente la simulation d'un pas.
	*/
	@Override
	public void next() {
		boids.move();
		//EventManager.get().next();
		System.out.println(boids.toString());
		dessiner();
	}

	/**
	*Réinitialise la simulation.
	*/
	@Override
	public void restart() {
		boids.reInit();
		System.out.println(boids.toString());
		dessiner();
	}

}
