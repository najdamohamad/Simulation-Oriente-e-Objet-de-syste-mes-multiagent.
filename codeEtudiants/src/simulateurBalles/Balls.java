package simulateurBalles;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;


/**
*Cette classe permet de gerer un ensemble de balles définies par leurs coordonnées.
*/
public class Balls {
	//listeInit sert à garder l'état initial des balles pour le reInit

	/**
	*Liste contenant les coordonnées des balles.
	*/
	private ArrayList<Point> listeBalles;
	/**
	*Liste des cooredonnées initiales des balles (dans le cas d'une réinitialisation de leurs coordonnées).
	*/
	private ArrayList<Point> listeInit;
	//sensDeTranslation (nom plutot explicite) sert à faire rebondir les balles
	private ArrayList<Integer> sensDeTranslationX;
	private ArrayList<Integer> sensDeTranslationY;
	/**
	*Nombre de balles de l'ensemble.
	*/
	protected int nombre;
	protected int tailleDeLaFenetreX;
	protected int tailleDeLaFenetreY;


	/**
	*Constructeur d'un ensemble de "nb" balles.
	*@param tailleX Hauteur de la zone d'évolution des mobiles.
	*@param tailleY Largeur de la zone d'évolution des mobiles.
	*/
	public Balls(int nb, int tailleX, int tailleY) {
		this.listeBalles = new ArrayList<Point>();
		this.listeInit = new ArrayList<Point>();
			this.sensDeTranslationX = new ArrayList<Integer>();
		this.sensDeTranslationY = new ArrayList<Integer>();
		this.nombre=nb;
		this.tailleDeLaFenetreX=tailleX;
		this.tailleDeLaFenetreY=tailleY;
	}

	/**
	*Accesseur a "nombre".
	*/
	public int getNombre() {
		return nombre;
	}

	/**
	*Accesseur a "listeBalles".
	*/
	public ArrayList<Point> getListeBalles() {
		return listeBalles;
	}

	/**
	*Parametrage de "listeBalles".
	*/
	public void setListeBalles(ArrayList<Point> listeBalles) {
		this.listeBalles = listeBalles;
	}

	/**
	*Accesseur a "listeInit".
	*/
	public ArrayList<Point> getListeInit() {
		return listeInit;
	}

	/**
	*Parametrage de "listeInit".
	*/
	public void setListeInit(ArrayList<Point> listeInit) {
		this.listeInit = listeInit;
	}

	/**
	*Créer un point de coordonnées aléatoires, l'ajoute dans les listes de balles et l'affiche dans la fenêtre.
	*/
	public void init() {
		Random r = new Random();
		for (int i = 0; i < nombre; i++) {
			add(new Point(r.nextInt(this.tailleDeLaFenetreX),r.nextInt(this.tailleDeLaFenetreY)));
		}
	}

	/**
	*Ajout d'un point "p" a notre ensemble de balles.
	*/
	public void add(Point p) {
		listeBalles.add(p);
		//on ne peut pas donner à listeInit.add le p comme argument car ça renverait à la meme reference et donc toute modif sur listeBalles se ferait sur listeInit
		listeInit.add(new Point(p.x,p.y));
		sensDeTranslationX.add(1);
		sensDeTranslationY.add(1);
	}

	/**
	*Fait translater les balles dans le sens de leur deplacement.
	*@param dx Déplacement absolu vertical.
	*@param dy Déplacement absolu horizontal.
	*/
	public void translate(int dx, int dy) {
		int i=0;
		Iterator<Point> it = listeBalles.iterator();
		while (it.hasNext()) {
			Point p = it.next();
			if(p.x > this.tailleDeLaFenetreX-dx) {
				sensDeTranslationX.set(i, -1);
			}
			if(p.x < dx) {
				sensDeTranslationX.set(i, 1);
			}
			if(p.y > this.tailleDeLaFenetreY-dy) {
				sensDeTranslationY.set(i, -1);
			}
			if(p.y < dy) {
				sensDeTranslationY.set(i, 1);
			}
			p.translate(dx*sensDeTranslationX.get(i), dy*sensDeTranslationY.get(i));
			i++;
		}
	}

	/**
	*Réinitialise les coordonnées des balles de l'ensemble.
	*/
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


	/**
	*Affichage des coordonnées de chaque balle de l'ensemble.
	*/
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
