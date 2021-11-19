package segregationSchelling;

import java.awt.Color;
import java.awt.Point;
import java.util.LinkedList;
import java.util.Random;

import jeuDeLImmigration.GameOfImmigration;

public class Segregation extends GameOfImmigration {
	/**
	*Paramètre de segregation d'une cellule. Si la cellule a plus de seuil voisin de couleur différente alors elle devient vacante.
	*/
	private int seuil;
	/**
	*Nombre de couleur prise en compte dans la simulation
	*/
	private int nbCouleurs;
	/**
	*Liste des cellules dans un état d'attente.
	*/
	private LinkedList<Point> listesVacants;


	public Segregation(int size, int nbCouleurs, int seuil) {
		super();
		this.seuil = seuil;
		this.nbCouleurs=nbCouleurs;
		listesVacants= new LinkedList<Point>();
		this.setNbEtats(nbCouleurs+1);
		this.setSize(size);
		this.setMatriceIm(new int[size][size]);
		this.setMatriceImPast(new int[size][size]);
		this.setMatriceInit(new int[size][size]);
		this.setListeCouleurs(initialiseCouleurs());
		this.init();
	}

	/**
	*Initialisation de notre tableau de couleur.
	*/
	@Override
	public Color[] initialiseCouleurs() {
		Color[] couleurs = new Color[nbCouleurs+1];
	  couleurs[0]=Color.BLACK;//les maisons vacantes sont en noir
		Random r = new Random();
		for(int i=1; i<couleurs.length; i++){
			couleurs[i]=new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255));
		}

		return couleurs;
	}

	/**
	*Passage de l'état n à l'état n+1 de la simulation. L'état de chaque cellule est recalculé suivant les règle segregation de Schelling.
	*/
	@Override
	public void move() {
		int[][] matrice1= this.getMatriceIm();
		int[][] matrice2= this.getMatriceImPast();
		int size=this.getSize();
		for (int i = 0; i < size; i+=10) {
			for (int j = 0; j < size; j+=10) {
				int[]neighbors=voisinsIm(i, j);
				int nbVoisinsCouleursDifferentes=0;
				int couleurActuelle = matrice2[i][j];
				for (int k = 0; k < neighbors.length; k++) {
					if (couleurActuelle!=0 && neighbors[k]!=couleurActuelle && neighbors[k]!=0) {
						nbVoisinsCouleursDifferentes++;
					}
				}
				if (nbVoisinsCouleursDifferentes>=seuil) {
					matrice1[i][j]=0;
					listesVacants.add(new Point(i,j));
					Point p = listesVacants.pollFirst();
					matrice1[p.x][p.y]=couleurActuelle;
				}
			}
		}
		//on met à jour matriceImPast
		for (int i = 0; i < size; i+=10) {
			for (int j = 0; j < size; j+=10) {
				matrice2[i][j]=matrice1[i][j];
			}
		}
		this.setMatriceIm(matrice1);
		this.setMatriceImPast(matrice2);
	}

	/**
	*Initialisation de la matrice de simulation.
	*/
	@Override
	public void init() {
		while (listesVacants.size()<50 ) {
			int [][] matrice1=this.getMatriceIm();
			int [][] matrice2=this.getMatriceImPast();
			int [][] matrice3=this.getMatriceInit();
			int taille=this.getSize();
			for (int i = 0; i < taille; i+=10) {
				for (int j = 0; j < taille; j+=10) {
					Random random = new Random();
					int etat = random.nextInt(this.getNbEtats());
					matrice1[i][j]=etat;
					matrice2[i][j]=etat;
					matrice3[i][j]=etat;
					if(etat==0) {
						listesVacants.add(new Point(i,j));
					}
				}
			}
			this.setMatriceIm(matrice1);
			this.setMatriceImPast(matrice2);
			this.setMatriceInit(matrice3);
		}
	}

}
