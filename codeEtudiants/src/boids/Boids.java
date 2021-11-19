package boids;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Vector;

import simulateurBalles.Balls;

/**
 * 
 * Cette classe sert à la simulation d'un groupe de boids ayant tous la meme nature
 *
 */
public class Boids extends Balls {
	//vecteurs correspondants aux differentes regles
	private Vector<Point> vitesses;
	private Vector<Point> vectorRule1;
	private Vector<Point> vectorRule2;
	private Vector<Point> vectorRule3;
	// type is useful for the Predator/Prey mode
	//c'est dans type que l'on stocke la nature des boids
	private int[] type;
	protected static final int DISTANCE_MIN =20;
	protected static final int SPEED_LIMIT =20;


	/*
	 * Constructeur de la classe Predateurs
	 * @param nombre Nombre de boids pris en charge dans la simulation
	 * @param tailleX taille de la fenetre graphique suivant l'axe X
	 * @param tailleY taille de la fenetre graphique suivant l'axe Y
	 */
	public Boids(int nombre, int tailleX, int tailleY) {
		super(nombre,tailleX,tailleY);
		vitesses=new Vector<Point>();
		vectorRule1=new Vector<Point>();
		vectorRule2=new Vector<Point>();
		vectorRule3=new Vector<Point>();
		type = new int[getNombre()];
	}
	
	/**Les classes filles de Boids vont override la fonction init() 
	 * Dans les constructeurs des classes filles le superConstructor est le premier
	 * à etre appelé si on n'en avait mis qu'un seul avec le init inclus
	 * n'aura pas tous les elements necessaires lors de son appel c'estpour ça que l'on met
	 * en place une surcharge du constructeur
	 */
	public Boids(int nombre, int tailleX, int tailleY, int bool) {
		this(nombre,tailleX,tailleY);
		init();
	}
	/*
	 * Getter du vecteur Vitesses
	 */
	public Vector<Point> getVitesses() {
		return vitesses;
	}
	/*
	 * Setter du vecteur Vitesses
	 */
	public void setVitesses(Vector<Point> vitesses) {
		this.vitesses = vitesses;
	}
	/*
	 * Getter du vecteur de la Regle1; celle pour la cohesion du groupe
	 */
	public Vector<Point> getVectorRule1() {
		return vectorRule1;
	}
	/*
	 * Setter du vecteur de la Regle1; celle pour la cohesion du groupe 
	 */
	public void setVectorRule1(Vector<Point> vectorRule1) {
		this.vectorRule1 = vectorRule1;
	}
	
	/*
	 * Getter du vecteur de la Regle2; celle pour l'alignement du groupe
	 */
	public Vector<Point> getVectorRule2() {
		return vectorRule2;
	}
	/*
	 * Setter du vecteur de la Regle2; celle pour l'alignement du groupe
	 */
	public void setVectorRule2(Vector<Point> vectorRule2) {
		this.vectorRule2 = vectorRule2;
	}
	
	/*
	 * Getter du vecteur de la Regle3; celle qui evite les collisions entre les boids 
	 */
	public Vector<Point> getVectorRule3() {
		return vectorRule3;
	}
	
	/*
	 * Setter du vecteur de la Regle3; celle qui evite les collisions entre les boids
	 */
	public void setVectorRule3(Vector<Point> vectorRule3) {
		this.vectorRule3 = vectorRule3;
	}
	
	/*
	 * Getter du tableau où sont indiqués la nature(proie ou predateur) des boids
	 */
	public int[] getType() {
		return type;
	}
	/*
	 * Setter du tableau où sont indiqués la nature(proie ou predateur) des boids
	 */
	public void setType(int[] type) {
		this.type = type;
	}

	/**Initialise the boids positions*/
	@Override
	public void init() {
		ArrayList<Point> listeBoids = getListeBalles();
		ArrayList<Point> initList=new ArrayList<Point>();
		Random r = new Random();
		for (int i = 0; i < getNombre(); i++) {
			Point p = new Point(r.nextInt(this.tailleDeLaFenetreX),r.nextInt(this.tailleDeLaFenetreY));
			listeBoids.add(p);
			initList.add(new Point(p.x,p.y));
			vitesses.add(i, new Point());
			vectorRule1.add(i, new Point());
			vectorRule2.add(i, new Point());
			vectorRule3.add(i, new Point());
			type[i]=0;
		}
		setListeBalles(listeBoids);
		setListeInit(initList);
	}

	/**Boids try to fly towards the centre of mass of neighbouring boids of the same type
	 * @param indice position occupé par le boid dans les differents vecteurs
	 * */
	protected void rule1(int indice) {
		Point positionCentre=new Point();
		int index=0;
		ArrayList<Point> ballsListe=getListeBalles();
		Iterator<Point> it = ballsListe.iterator();
		int positionx=0,positiony=0;
		int nombreBoidsSimilaires=0;
		int typeBoid=type[indice];
		while(it.hasNext()) {
			Point boid = it.next();
			if(index!=indice && (type[index]==typeBoid)) {
				positionCentre.x+=boid.x;
				positionCentre.y+=boid.y;
				nombreBoidsSimilaires++;
			}
			else if(index==indice){
				positionx=boid.x;
				positiony=boid.y;
			}
			index++;
		}
		Point p = new Point();
		if(nombreBoidsSimilaires!=0) {
			positionCentre.x/=nombreBoidsSimilaires;
			positionCentre.y/=nombreBoidsSimilaires;
			p.x = (positionCentre.x - positionx)/100;
			p.y = (positionCentre.y - positiony)/100;
		}
		vectorRule1.setElementAt(p, indice);
	}
	
	/**Boids try to keep a small distance away from other boids
	 * @param bj localisation du boid
	 * @param indice position occupé par le boid dans les differents vecteurs
	 * */
	protected void rule2(Point bj, int indice) {
		Point v=new Point();
		int index=0;
		ArrayList<Point> ballsListe=getListeBalles();
		Iterator<Point> it = ballsListe.iterator();
		while(it.hasNext()) {
			Point boid = it.next();
			if(index!=indice) {
				double distance = Math.sqrt(Math.pow((boid.x-bj.x),2)+Math.pow((boid.y-bj.y),2));
				if (distance < DISTANCE_MIN) {
					v.x -= (boid.x - bj.x);
					v.y-= (boid.y - bj.y);
				}
			}
			index++;
		}
		vectorRule2.setElementAt(v, indice);
	}
	/**Boids try to match velocity with near boids
	 * 
	 * @param indice position occupé par le boid dans les differents vecteurs
	 * */
	protected void rule3(int indice) {
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
		v.x/=(getNombre()-1);
		v.y/=(getNombre()-1);
		v.x = (v.x - vitesses.get(indice).x)/8;
		v.y = (v.y - vitesses.get(indice).y)/8;
		vectorRule3.setElementAt(v, indice);
	}
	
	/**Rule to enable boids bounding on the limits of the screen
	 * 
	 * @param bj localisation du boid
	 * @param indice position occupé par le boid dans les differents vecteurs
	 * */
	protected void rule4Bounds(Point bj, int indice) {
		Point v = vitesses.get(indice);
		if(bj.x < 0) {
			v.x = 30;
		}
		else if(bj.x > this.tailleDeLaFenetreX) {
			v.x = -30;
		}
		if(bj.y < 0) {
			v.y = 30;
		}
		else if(bj.y > this.tailleDeLaFenetreY) {
			v.y = -30;
		}
		vitesses.setElementAt(v, indice);
	}
	
	/**Rule to limit the speed of the boids
	 * @param vlim la vitesse limite
	 * */
	protected void rule5SpeedLImit(int vlim) {
		for (int i = 0; i < getNombre(); i++) {
			Point v = vitesses.get(i);
			int module =(int) Math.sqrt(Math.pow(v.x,2)+Math.pow(v.y,2));
			if(module> vlim) {
				v.x = (v.x /module)*vlim;
				v.y = (v.y /module)*vlim;
				vitesses.set(i, v);
			}
		}
	}
	
	/**Reinitialise the simulation*/
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
			type[indice]=0;
			indice++;
		}
		setListeBalles(ballsList);
	}
	
	/** Move all the boids*/
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
			rule5SpeedLImit(SPEED_LIMIT);
			boid.x+=vitesses.get(indice).x;
			boid.y+=vitesses.get(indice).y;
			indice++;
		}
		setListeBalles(ballsListe);
	}

}
