package boids;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Vector;

import gui.GUISimulator;

/**
 * 
 * Cette classe Predateurs sert mettre en place une simulation de la cohabitation
 * entre deux groupes de boids differents: des predateurs et des proies
 *
 */
public class Predateurs extends Boids {
	private Vector<Point> vectorChasePrey=new Vector<Point>();
	private Vector<Point> vectorEscapePred;
	private int[] typeBoidsInit;
	
	/*
	 * Constructeur de la classe Predateurs
	 */
	public Predateurs(int nombre, GUISimulator gui) {
		super(nombre, gui.getPanelHeight(), gui.getPanelWidth());
		this.vectorChasePrey = new Vector<Point>();
		this.vectorEscapePred = new Vector<Point>();
		this.typeBoidsInit=new int[nombre];
		init();
	}
	
	protected static final int PREDATEUR =1;
	protected static final int PROIE =0;
	protected static final int DEAD=-1;
	protected static final int PRED_SPEED_LIMIT=15;
	protected static final int PREY_SPEED_LIMIT=20;
	protected static final double PREY_RATIO = 0.7;
	protected static final int DEATH_RAY=10;
	
	/*
	 * Regle de chasse pour les predateurs qui poursuivent les proies
	 */
	protected void ruleChasePrey(Point bj, int index) {
		ArrayList<Point> listePositions = getListeBalles();
		Iterator<Point> it = listePositions.iterator();
		int[] typeBoids = getType();
		int indice=0,nombreProies=0;
		Point positionMoyenneProies = new Point();
		while (it.hasNext()) {
			Point boid = (Point) it.next();
			if (typeBoids[indice]==PROIE) {
				nombreProies++;
				positionMoyenneProies.x+=boid.x;
				positionMoyenneProies.y+=boid.y;
			}
			indice++;
		}
		
		if(nombreProies!=0) {
		positionMoyenneProies.x/=nombreProies;
		positionMoyenneProies.y/=nombreProies;
		Point p= new Point();
		p.x = (positionMoyenneProies.x - bj.y)/100;
		p.y = (positionMoyenneProies.y - bj.x)/100;
		vectorChasePrey.setElementAt(p, index);
		}
	}
	
	/*
	 * Règles de fuite pour les proies qui cherchent à eviter les predateurs
	 */
	protected void ruleEscapePred(Point bj, int index) {
		ArrayList<Point> listePositions = getListeBalles();
		Iterator<Point> it = listePositions.iterator();
		int[] typeBoids = getType();
		int indice=0,nombrePredateurs=0;
		Point positionMoyennePredateurs = new Point();
		while (it.hasNext()) {
			Point boid = (Point) it.next();
			if (typeBoids[indice]==PREDATEUR) {
				nombrePredateurs++;
				positionMoyennePredateurs.x+=boid.x;
				positionMoyennePredateurs.y+=boid.y;
			}
			indice++;
		}
		if(nombrePredateurs!=0) {
		positionMoyennePredateurs.x/=nombrePredateurs;
		positionMoyennePredateurs.y/=nombrePredateurs;
		Point p= new Point();
		p.x = (bj.x - positionMoyennePredateurs.x)/100;
		p.y = (bj.y - positionMoyennePredateurs.y)/100;
		vectorEscapePred.setElementAt(p, index);
		}
	}
	
	/*
	 * Methode qui definit le deplacement des proies
	 * Applique les differentes régles et remplie les vecteurs correspondant 
	 */
	public void moveProie(Point boid, int indice) {
		rule1(indice);
		rule2(boid,indice);
		rule3(indice);
		ruleEscapePred(boid, indice);
		Point p=new Point();
		Vector<Point> vitesses = getVitesses();
		Vector<Point> vectorRule1 = getVectorRule1();
		Vector<Point> vectorRule2 = getVectorRule2();
		Vector<Point> vectorRule3 = getVectorRule3();
		p.x=vitesses.get(indice).x+vectorRule1.get(indice).x+vectorRule2.get(indice).x+vectorRule3.get(indice).x+2*vectorEscapePred.get(indice).x;
		p.y=vitesses.get(indice).y+vectorRule1.get(indice).y+vectorRule2.get(indice).y+vectorRule3.get(indice).y+2*vectorEscapePred.get(indice).y;
		vitesses.setElementAt(p, indice);
		setVitesses(vitesses);
		rule4Bounds(boid,indice);
		rule5SpeedLImit(PREY_SPEED_LIMIT);
		vitesses=getVitesses();
		boid.x+=vitesses.get(indice).x;
		boid.y+=vitesses.get(indice).y;
	
}
	
	/*
	 * Initiailise aléatoirement les boids en leur donnant une nature(proie ou predateur) et les reparties sur la grille
	 */
	@Override
	public void init() {
		ArrayList<Point> listeBoids = getListeBalles();
		ArrayList<Point> initList=new ArrayList<Point>();
		Random r = new Random();
		int[] typeBoids = getType();
		Vector<Point> vitesses=getVitesses();
		Vector<Point> vectorRule1=getVectorRule1();
		Vector<Point> vectorRule2=getVectorRule2();
		Vector<Point> vectorRule3=getVectorRule3();
		for (int i = 0; i < getNombresBoids(); i++) {
			double nombreAlea = Math.random();
			if(nombreAlea<PREY_RATIO) {
				typeBoids[i]=PROIE;
				typeBoidsInit[i]=PROIE;
			}
			else {
				typeBoids[i]=PREDATEUR;
				typeBoidsInit[i]=PREDATEUR;
			}
			Point p = new Point(r.nextInt(this.tailleDeLaFenetreX),r.nextInt(this.tailleDeLaFenetreY));
			listeBoids.add(p);
			initList.add(new Point(p.x,p.y));
			vitesses.add(i, new Point());
			vectorRule1.add(i, new Point());
			vectorRule2.add(i, new Point());
			vectorRule3.add(i, new Point());
			vectorChasePrey.add(i,new Point());
			vectorEscapePred.add(i, new Point());
		}
		setListeBalles(listeBoids);
		setListeInit(initList);
		setType(typeBoids);
	}

	/*
	 * Methode appelée pour remettre la simulation dans son etat initial(restart)
	 */
	@Override
	public void reInit() {
		ArrayList<Point> ballsList=getListeBalles();
		ArrayList<Point> initList=getListeInit();
		Iterator<Point> it = ballsList.iterator();
		Iterator<Point> it2 = initList.iterator();
		int indice =0;
		Vector<Point> vitesses = getVitesses();
		Vector<Point> vectorRule1=getVectorRule1();
		Vector<Point> vectorRule2=getVectorRule2();
		Vector<Point> vectorRule3=getVectorRule3();
		int [] typeBoids = getType();
		while(it.hasNext()) {
			Point p = it2.next();
			it.next().move(p.x, p.y);
			vitesses.setElementAt(new Point(), indice);
			vectorRule1.setElementAt(new Point(), indice);
			vectorRule2.setElementAt(new Point(), indice);
			vectorRule3.setElementAt(new Point(), indice);
			typeBoids[indice]=typeBoidsInit[indice];
			indice++;
		}
		setVectorRule1(vectorRule1);
		setVectorRule2(vectorRule2);
		setVectorRule3(vectorRule3);
		setVitesses(vitesses);
		setType(typeBoids);
		setListeBalles(ballsList);
	}
	
	/*
	 * Methode definissant le deplacement des predateurs en appliquanr les differentes regles et en mettant à jour les vecteurs
	 */
	public void movePredateur(Point boid, int indice) {
		rule1(indice);
		rule2(boid,indice);
		rule3(indice);
		ruleChasePrey(boid, indice);
		Point p=new Point();
		Vector<Point> vitesses = getVitesses();
		Vector<Point> vectorRule1 = getVectorRule1();
		Vector<Point> vectorRule2 = getVectorRule2();
		Vector<Point> vectorRule3 = getVectorRule3();
		p.x=vitesses.get(indice).x+vectorRule1.get(indice).x+vectorRule2.get(indice).x+vectorRule3.get(indice).x+2*vectorChasePrey.get(indice).x;
		p.y=vitesses.get(indice).y+vectorRule1.get(indice).y+vectorRule2.get(indice).y+vectorRule3.get(indice).y+2*vectorChasePrey.get(indice).y;
		vitesses.setElementAt(p, indice);
		setVitesses(vitesses);
		rule4Bounds(boid,indice);
		rule5SpeedLImit(PRED_SPEED_LIMIT);
		vitesses=getVitesses();
		boid.x+=vitesses.get(indice).x;
		boid.y+=vitesses.get(indice).y;
	}

	
	/*
	 * Methode qui detecte les proies éliminées par les predateurs
	 * et modifie leur nature dans le vecteur type pour qu'ils soient considérés comme mort
	 */
	public void updateKills() {
		ArrayList<Point> listePositions = getListeBalles();
		ArrayList<Point> listePositions2 = getListeBalles();
		Iterator<Point> it = listePositions.iterator();
		int[] typeBoids = getType();
		int indice=0;
		while (it.hasNext()) {
			Point boid = it.next();
			if(typeBoids[indice]==PROIE) {
				int i=0;
				for (Iterator<Point> iterator = listePositions2.iterator(); iterator.hasNext();) {
					Point point = (Point) iterator.next();
					if(typeBoids[i]==PREDATEUR) {
						double distance = Math.sqrt(Math.pow((boid.x-point.x),2)+Math.pow((boid.y-point.y),2));
						if(distance<=DEATH_RAY) {
							typeBoids[indice]=DEAD;
							break;
						}
					}
					i++;
				}
			}
			indice++;
		}
		setType(typeBoids);
	}
	
	
	/*
	 * Methode utilisée par le simulateur pour déplacer l'ensemble des boids
	 * Fait appel aux methodes correspondant au comportement spécifique de chaque boid
	 */
	@Override
	public void move() {
		updateKills();
		ArrayList<Point> listePositions = getListeBalles();
		Iterator<Point> it = listePositions.iterator();
		int[] typeBoids = getType();
		int indice=0;
		while (it.hasNext()) {
			Point boid = (Point) it.next();
			if (typeBoids[indice]==PREDATEUR) {
				movePredateur(boid, indice);
			}
			else if(typeBoids[indice]==PROIE) {
				moveProie(boid, indice);
			}
			indice++;
		}
		setListeBalles(listePositions);
	}
	
	
	
}
