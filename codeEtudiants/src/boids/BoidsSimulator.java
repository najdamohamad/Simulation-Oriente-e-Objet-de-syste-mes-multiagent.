package boids;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;

import gui.GUISimulator;
import gui.Oval;
import gui.Simulable;

/**
 * 
 * Classe BoidsSimulator chargé de la simulation des comportements definies dans la classe boids
 *
 */
public class BoidsSimulator implements Simulable {
	private Boids boids;
	private GUISimulator gui;

	/*
	 * Constructeur de la classe BoidsSimulator L'ensemble est composé de nombre boids tous de meme nature et d'une fenetre graphique
	 *@param nombre Nombre de boids présents dans la simulation 
	 *@param gui Fenetre graphique de la simulation
	 */
	public BoidsSimulator(int nombre, GUISimulator gui) {
		this.gui=gui;
		boids=new Boids(nombre,gui.getPanelHeight(),gui.getPanelWidth(),1);
		dessiner();
	}
	
	/** Draw on the screen the boids*/
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
	
	/*
	 * Incremente la simulation d'un pas
	 */
	@Override
	public void next() {
		boids.move();
		dessiner();
	}
	
	/*
	 * Reinitialise la simulation
	 */
	@Override
	public void restart() {
		boids.reInit();
		dessiner();	
	}

}
