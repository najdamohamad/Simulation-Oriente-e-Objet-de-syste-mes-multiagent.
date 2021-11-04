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
	//sensDeTranslation (nom plutot explicite) sert à faire rebondir les balles
	private ArrayList<Integer> sensDeTranslationX;
	private ArrayList<Integer> sensDeTranslationY;
	
	public Balls() {
		this.listeBalles = new ArrayList<Point>();
		this.listeInit = new ArrayList<Point>();
		this.sensDeTranslationX = new ArrayList<Integer>();
		this.sensDeTranslationY = new ArrayList<Integer>();
	}

	public void add(Point p) {
		listeBalles.add(p);
		//on ne peut pas donner à listeInit.add le p comme argument car ça renverait à la meme reference et donc toute modif sur listeBalles se ferait sur listeInit
		listeInit.add(new Point(p.x,p.y));
		sensDeTranslationX.add(1);
		sensDeTranslationY.add(1);
	}
	
	public void translate(int dx, int dy) {
		int i=0;
		Iterator<Point> it = listeBalles.iterator();
		while (it.hasNext()) {
			Point p = it.next();
			if(p.x > 499-dx) {
				sensDeTranslationX.set(i, -1);
			}
			if(p.x < dx) {
				sensDeTranslationX.set(i, 1);
			}
			if(p.y > 499-dy) {
				sensDeTranslationY.set(i, -1);
			}
			if(p.y < dy) {
				sensDeTranslationY.set(i, 1);
			}
			p.translate(dx*sensDeTranslationX.get(i), dy*sensDeTranslationY.get(i));
			i++;
		}
	}
	
	public void reInit() {
		Iterator<Point> it = listeBalles.iterator();
		Iterator<Point> it2 = listeInit.iterator();
		int i=0;
		while(it.hasNext()) {
			Point p = it2.next();
			it.next().move(p.x, p.y);
			sensDeTranslationX.set(i, 1);
			sensDeTranslationY.set(i, 1);
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
