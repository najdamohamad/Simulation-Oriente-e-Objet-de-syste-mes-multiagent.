package boids;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;

import event.EventManager;
import event.PredateursEvent;
import gui.GUISimulator;
import gui.Oval;
import gui.Simulable;

public class PredateursSimulator implements Simulable {
	private Predateurs predators;
	private GUISimulator gui;

	/*
	 * Constructeur de la classe PredateursSimulator L'ensemble est composé de nombre boids de deux types(proie/predateurs) et d'une fenetre graphique
	 *@param nombre Nombre de boids présents dans la simulation 
	 *@param gui Fenetre graphique de la simulation
	 */
	public PredateursSimulator(int nombre, GUISimulator gui) {
		this.gui=gui;
		predators=new Predateurs(nombre,gui);
		dessiner();
		EventManager.get().addEvent(new PredateursEvent(0, predators));
	}
	
	protected static final int PREDATEUR =1;
	protected static final int PROIE =0;
	protected static final int DEAD=-1;
	
	/** Draw on the screen the boids*/
	public void dessiner() {
		gui.reset();
		ArrayList<Point> boidsList = predators.getListeBalles();
		Iterator<Point> it = boidsList.iterator();
		int[] typeBoids = predators.getType();
		int indice=0;
		while(it.hasNext()) {
			Point boid = it.next();
			if(typeBoids[indice]==PROIE) {
				gui.addGraphicalElement(new Oval(boid.x,boid.y,Color.GREEN,Color.GREEN,10));
			}
			else if(typeBoids[indice]==PREDATEUR) {
				gui.addGraphicalElement(new Oval(boid.x,boid.y,Color.RED,Color.RED,20));
			}
			indice++;
		}
	}
	
	/*
	 * Incremente la simulation d'un pas
	 */
	@Override
	public void next() {
		EventManager.get().next();
		dessiner();
	}
	/*
	 * Reinitialise la simulation
	 */
	@Override
	public void restart() {
		predators.reInit();
		dessiner();	
	}

}
