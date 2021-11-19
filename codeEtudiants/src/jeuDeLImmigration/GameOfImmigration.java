package jeuDeLImmigration;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import jeuDeLaVieConway.GameOfLife;

public class GameOfImmigration extends GameOfLife {
	private int nbEtats;
	private int[][] matriceInit;
	private Color[] listeCouleurs;


	/*
	 * Constructeur de la classe Segregation
	 * @param size taille de la fenetre graphique
	 * @param nbEtats Nombre d'etats pris en compte dans la simulation
	 */
	public GameOfImmigration(int size, int nbEtats) {
		super(size);
		this.nbEtats = nbEtats;
		matriceInit = new int[size][size];
		listeCouleurs= initialiseCouleurs();
		this.init();
	}
	
	public GameOfImmigration() {
		super();
	}
	/*
	 * Getter du nombre d'etats
	 */
	public int getNbEtats() {
		return nbEtats;
	}

	/*
	 * Setter du nombre d'etats
	 */
	public void setNbEtats(int nbEtats) {
		this.nbEtats = nbEtats;
	}
	
	/*
	 * Getter de la liste des couleurs utilisées
	 */
	public Color[] getListeCouleurs() {
		return listeCouleurs;
	}
	
	/*
	 * Setter de la liste des couleurs
	 * @param listeCouleurs
	 */
	public void setListeCouleurs(Color[] listeCouleurs) {
		this.listeCouleurs = listeCouleurs;
	}
	
	/*
	 * Getter de la matrice d'initialisation
	 */
	public int[][] getMatriceInit() {
		return matriceInit;
	} 

	/**
	*Initialisation de notre tableau de couleur.
	*/
	public Color[] initialiseCouleurs() {
		Color [] couleurs = new Color [this.nbEtats];
		couleurs[0]= Color.RED;
		couleurs[1]=Color.BLACK;
		couleurs[2]=Color.WHITE;
		for (int i = 3; i < couleurs.length; i++) {
			couleurs[i] = couleurs[i-1].darker();
		}
		return couleurs;
	}

	/*
	 * Setter de la matrice d'initialisation
	 * @param matrice
	 */
	public void setMatriceInit(int[][] matriceInit) {
		this.matriceInit = matriceInit;
	}
	
	/**
	*Passage de l'état n à l'état n+1 de la simulation. L'état de chaque cellule est recalculé suivant les règle definies dans le cadre de l'immigration.
	*/
	@Override
	public void move() {
		int[][] matrice=getMatrice();
		int[][] matricePast=getMatricePast();
		int size=this.getSize();
		for (int i = 0; i < size; i+=10) {
			for (int j = 0; j < size; j+=10) {
				ArrayList<Integer> neighbors=voisins(i,j);
				int nbVoisinsEtatSuivant=0;
				int etatCourant = matricePast[i][j];
				int etatSuivant = (etatCourant + 1)% nbEtats;
				for (Iterator<Integer> it = neighbors.iterator(); it.hasNext();) {
					int etat = it.next();
					if(etat==etatSuivant) {
						nbVoisinsEtatSuivant++;
					}
				}
				if (nbVoisinsEtatSuivant>=3) {
					matrice[i][j]=etatSuivant;
				}
			}
		}
		//on met à jour matriceImPast
		for (int i = 0; i < size; i+=10) {
			for (int j = 0; j < size; j+=10) {
				matricePast[i][j]=matrice[i][j];
			}
		}
		this.setMatrice(matrice);
		this.setMatricePast(matricePast);
	}

	/**
	*Initialisation de la matrice de simulation.
	*/
	@Override
	public void init() {
		int[][] matrice=getMatrice();
		int[][] matricePast=getMatricePast();
		int taille=this.getSize();
		for (int i = 0; i < taille; i+=10) {
			for (int j = 0; j < taille; j+=10) {
				Random random = new Random();
				int etat = random.nextInt(nbEtats);
				matrice[i][j]=etat;
				matricePast[i][j]=etat;
				matriceInit[i][j]=etat;
			}
		}
		this.setMatrice(matrice);
		this.setMatricePast(matricePast);
	}
	
	/*
	 * Methode appelée pour remettre la simulation dans son etat initial(restart)
	 */
	public void reinitialiser() {
		int[][] matrice=getMatrice();
		int[][] matricePast=getMatricePast();
		int size=this.getSize();
		for (int i = 0; i < size; i+=10) {
			for (int j = 0; j < size; j+=10) {
				matrice[i][j]=matriceInit[i][j];
				matricePast[i][j]=matriceInit[i][j];
			}
		}
		this.setMatrice(matrice);
		this.setMatricePast(matricePast);
	}

}
