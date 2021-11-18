package boids;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Vector;

import gui.GUISimulator;
import gui.Oval;
import gui.Simulable;
import simulateurBalles.Balls;

public class BoidsSimulator extends Balls implements Simulable {
	private int nombresBoids;
	private Vector<Point> vitesses;
	private Vector<Point> vectorRule1;
	private Vector<Point> vectorRule2;
	private Vector<Point> vectorRule3;
	
	GUISimulator gui;
	
	
	
	public BoidsSimulator(int nombre, GUISimulator gui) {
		super();
		nombresBoids=nombre;
		vitesses=new Vector<Point>();
		vectorRule1=new Vector<Point>();
		vectorRule2=new Vector<Point>();
		vectorRule3=new Vector<Point>();
		this.gui=gui;
		init();
	}
	
	@Override
	public void init() {
		ArrayList<Point> listeBoids = getListeBalles();
		ArrayList<Point> initList=new ArrayList<Point>();
		Random r = new Random();
		for (int i = 0; i < nombresBoids; i++) {
			Point p = new Point(r.nextInt(gui.getPanelHeight()),r.nextInt(gui.getPanelWidth()));
			listeBoids.add(p);
			initList.add(new Point(p.x,p.y));
			vitesses.add(i, new Point());
			vectorRule1.add(i, new Point());
			vectorRule2.add(i, new Point());
			vectorRule3.add(i, new Point());
		}
		setListeBalles(listeBoids);
		setListeInit(initList);
		dessiner();
	}

	@Override
	public void dessiner() {
		gui.reset();
		ArrayList<Point> boidsList = getListeBalles();
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

	void rule1(int indice) {
		Point positionCentre=new Point();
		int index=0;
		ArrayList<Point> ballsListe=getListeBalles();
		Iterator<Point> it = ballsListe.iterator();
		int positionx=0,positiony=0;
		while(it.hasNext()) {
			Point boid = it.next();
			if(index!=indice) {
				positionCentre.x+=boid.x;
				positionCentre.y+=boid.y;
			}
			else {
				positionx=boid.x;
				positiony=boid.y;
			}
			index++;
		}
		positionCentre.x/=(nombresBoids-1);
		positionCentre.y/=(nombresBoids-1);
		Point p = new Point();
		p.x = (positionCentre.x - positionx)/100;
		p.y = (positionCentre.y - positiony)/100;
		vectorRule1.setElementAt(p, indice);
	}
	
	void rule2(Point bj, int indice) {
		Point v=new Point();
		int index=0;
		ArrayList<Point> ballsListe=getListeBalles();
		Iterator<Point> it = ballsListe.iterator();
		while(it.hasNext()) {
			Point boid = it.next();
			if(index!=indice) {
				double distance = Math.sqrt(Math.pow((boid.x-bj.x),2)+Math.pow((boid.y-bj.y),2));
				if (distance < 20) {
					v.x -= (boid.x - bj.x);
					v.y-= (boid.y - bj.y);
				}
			}
			index++;
		}
		vectorRule2.setElementAt(v, indice);
	}
	
	void rule3(int indice) {
		Point v= new Point();
		int index=0;
		ArrayList<Point> ballsListe=getListeBalles();
		Iterator<Point> it = ballsListe.iterator();
		while(it.hasNext()) {
			it.next();
			if(index!=indice) {
				v.x+=vitesses.get(index).x;
				v.y+=vitesses.get(index).y;
			}
			index++;
		}
		v.x/=(nombresBoids-1);
		v.y/=(nombresBoids-1);
		v.x = (v.x - vitesses.get(indice).x)/8;
		v.y = (v.y - vitesses.get(indice).y)/8;
		vectorRule3.setElementAt(v, indice);
	}
	
	void rule4Bounds(Point bj, int indice) {
		Point v = vitesses.get(indice);
		if(bj.x < 0) {
			v.x = 30;
		}
		else if(bj.x > gui.getHeight()) {
			v.x = -30;
		}
		if(bj.y < 0) {
			v.y = 30;
		}
		else if(bj.y > gui.getWidth()) {
			v.y = -50;
		}
		vitesses.setElementAt(v, indice);
	}
	
	void rule5SpeedLImit(int vlim) {
		for (int i = 0; i < nombresBoids; i++) {
			Point v = vitesses.get(i);
			int module =(int) Math.sqrt(Math.pow(v.x,2)+Math.pow(v.y,2));
			if(module> vlim) {
				v.x = (v.x /module)*vlim;
				v.y = (v.y /module)*vlim;
				vitesses.set(i, v);
			}
		}
	}
	
	
	@Override
	public void reInit() {
		ArrayList<Point> ballsList=getListeBalles();
		ArrayList<Point> initList=getListeInit();
		Iterator<Point> it = ballsList.iterator();
		Iterator<Point> it2 = initList.iterator();
		int indice =0;
		while(it.hasNext()) {
			Point p = it2.next();
			it.next().move(p.x, p.y);
			vitesses.setElementAt(new Point(), indice);
			vectorRule1.setElementAt(new Point(), indice);
			vectorRule2.setElementAt(new Point(), indice);
			vectorRule3.setElementAt(new Point(), indice);
			indice++;
		}
		setListeBalles(ballsList);
	}
	
	
	@Override
	public void next() {
		move();
		dessiner();
	}

	@Override
	public void restart() {
		reInit();
		dessiner();	
	}
	
	
	public void move() {
		ArrayList<Point> ballsListe = getListeBalles();
		int indice =0;
		for (Iterator<Point> it = ballsListe.iterator(); it.hasNext();) {
			Point boid = (Point) it.next();
			rule1(indice);
			rule2(boid,indice);
			rule3(indice);
			Point p=new Point();
			p.x=vitesses.get(indice).x+vectorRule1.get(indice).x+vectorRule2.get(indice).x+vectorRule3.get(indice).x;
			p.y=vitesses.get(indice).y+vectorRule1.get(indice).y+vectorRule2.get(indice).y+vectorRule3.get(indice).y;
			vitesses.setElementAt(p, indice);
			rule4Bounds(boid,indice);
			rule5SpeedLImit(25);
			boid.x+=vitesses.get(indice).x;
//			if(boid.x > gui.getPanelHeight()) {
//				boid.x=gui.getPanelHeight()-3*(boid.x-gui.getPanelHeight());
//			}
			boid.y+=vitesses.get(indice).y;
//			if(boid.y > gui.getPanelWidth()) {
//				boid.y=gui.getPanelWidth()-2*(boid.y-gui.getPanelWidth());
//			}
			indice++;
		}
		setListeBalles(ballsListe);
	}

}
