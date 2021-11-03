package simulateurBalles;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;

import gui.GUISimulator;
import gui.Oval;
public class Balls {
	//listeInit sert à garder l'état initial des balles pour le reInit
	private ArrayList<Point> listeBalles;
	private ArrayList<Point> listeInit;
	
	public Balls() {
		this.listeBalles = new ArrayList<Point>();
		this.listeInit = new ArrayList<Point>();
	}

	public void add(Point p) {
		listeBalles.add(p);
		//on ne peut pas donner à listeInit.add le p comme argument car ça renverait à la meme reference et donc toute modif sur listeBalles se ferait sur listeInit
		listeInit.add(new Point(p.x,p.y));
	}
	
	public void translate(int dx, int dy) {
		Iterator<Point> it = listeBalles.iterator();
		while (it.hasNext()) {
			Point p = it.next();
			if (p.x>(799-dx) && p.y>(499-dy)) {
				p.translate(-200, -200);
			}
			else if (p.y>(499-dy)) {
				p.translate(5*dx, -200);
			}
			else if (p.x>(799-dx)) {
				p.translate(-200, 5*dy);
			}
			else {
				p.translate(dx, dy);
			}
		}
	}
	
	public void reInit() {
		Iterator<Point> it = listeBalles.iterator();
		Iterator<Point> it2 = listeInit.iterator();
		while(it.hasNext()) {
			Point p = it2.next();
			it.next().move(p.x, p.y);
		}
	}
	
	public void dessiner(GUISimulator gui) {
		gui.reset();
		Iterator<Point> it = listeBalles.iterator();
		while(it.hasNext()) {
			Point p = it.next();
			gui.addGraphicalElement(new Oval(p.x,p.y,Color.GREEN,Color.GREEN,20));
		}
	}

	@Override
	public String toString() {
		String s=new String();
		for (Iterator<Point> it = listeBalles.iterator(); it.hasNext();) {
			Point p = it.next();
			s+=" [x="+p.x+",y="+p.y+"]";
		}
		return s;
	}
	
}
