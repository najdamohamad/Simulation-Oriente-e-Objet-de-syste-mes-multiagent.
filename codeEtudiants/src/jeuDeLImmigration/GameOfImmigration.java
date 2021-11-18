package jeuDeLImmigration;

import java.util.Random;
import java.awt.Color;


import jeuDeLaVieConway.GameOfLife;

public class GameOfImmigration extends GameOfLife {
	private int nbEtats;
	private int[][] matriceIm;
	private int[][] matriceImPast;
	private int[][] matriceInit;
	private Color[] listeCouleurs;


	public GameOfImmigration(int size, int nbEtats) {
		super(size);
		this.nbEtats = nbEtats;
		matriceIm = new int[size][size];
		matriceImPast = new int[size][size];
		matriceInit = new int[size][size];
		listeCouleurs= initialiseCouleurs();
		this.init();
	}
	public GameOfImmigration() {
		super();
	}

	public int getNbEtats() {
		return nbEtats;
	}

	public void setNbEtats(int nbEtats) {
		this.nbEtats = nbEtats;
	}

	public int[][] getMatriceIm() {
		return matriceIm;
	}

	public void setMatriceIm(int[][] matriceIm) {
		this.matriceIm = matriceIm;
	}

	public int[][] getMatriceImPast() {
		return matriceImPast;
	}

	public void setMatriceImPast(int[][] matriceImPast) {
		this.matriceImPast = matriceImPast;
	}

	public Color[] getListeCouleurs() {
		return listeCouleurs;
	}

	public void setListeCouleurs(Color[] listeCouleurs) {
		this.listeCouleurs = listeCouleurs;
	}

	public int[][] getMatriceInit() {
		return matriceInit;
	}

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

	public void setMatriceInit(int[][] matriceInit) {
		this.matriceInit = matriceInit;
	}

	@Override
	public void move() {
		int size=this.getSize();
		for (int i = 0; i < size; i+=10) {
			for (int j = 0; j < size; j+=10) {
				int[]neighbors=voisinsIm(i, j);
				int nbVoisinsEtatSuivant=0;
				int etatCourant = matriceImPast[i][j];
				int etatSuivant = (etatCourant + 1)% nbEtats;
				for (int k = 0; k < neighbors.length; k++) {
					if (neighbors[k] == etatSuivant) {
						nbVoisinsEtatSuivant++;
					}
				}
				if (nbVoisinsEtatSuivant>=3) {
					matriceIm[i][j]=etatSuivant;
				}
			}
		}
		//on met à jour matriceImPast
		for (int i = 0; i < size; i+=10) {
			for (int j = 0; j < size; j+=10) {
				matriceImPast[i][j]=matriceIm[i][j];
			}
		}
	}


	@Override
	public void init() {
		int taille=this.getSize();
		for (int i = 0; i < taille; i+=10) {
			for (int j = 0; j < taille; j+=10) {
				Random random = new Random();
				int etat = random.nextInt(nbEtats);
				matriceIm[i][j]=etat;
				matriceImPast[i][j]=etat;
				matriceInit[i][j]=etat;
			}
		}
	}


	public int[] voisinsIm(int k, int m) {
		int[] listeVoisins = new int[8];
		int size=this.getSize();
		int kMinus1=k-10;
		int kPlus1=k+10;
		int mMinus1=m-10;
		int mPlus1=m+10;

		if (k==0) {
			kMinus1=size-10;
		}
		else if (k==size-10) {
			kPlus1=0;
		}
		if (m==0) {
			mMinus1=size-10;
		}
		else if(m==size-10) {
			mPlus1=0;
		}
		listeVoisins[0]=matriceImPast[kMinus1][m];
        listeVoisins[1]=matriceImPast[kMinus1][mMinus1];
        listeVoisins[2]=matriceImPast[kMinus1][mPlus1];
        listeVoisins[3]=matriceImPast[k][mMinus1];
        listeVoisins[4]=matriceImPast[k][mPlus1];
        listeVoisins[5]=matriceImPast[kPlus1][m];
        listeVoisins[6]=matriceImPast[kPlus1][mMinus1];
        listeVoisins[7]=matriceImPast[kPlus1][mPlus1];

		return listeVoisins;
	}



	public void reinitialiser() {
		int size=this.getSize();
		for (int i = 0; i < size; i+=10) {
			for (int j = 0; j < size; j+=10) {
				matriceIm[i][j]=matriceInit[i][j];
				matriceImPast[i][j]=matriceInit[i][j];
			}
		}
	}

}
