package jeuDeLImmigration;

import java.util.Random;
import java.awt.Color;


import jeuDeLaVieConway.GameOfLife;

public class GameOfImmigration extends GameOfLife {
	private int nbEtats;
	private int[][] matriceInit;
	private Color[] listeCouleurs;


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

	public int getNbEtats() {
		return nbEtats;
	}

	public void setNbEtats(int nbEtats) {
		this.nbEtats = nbEtats;
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
		int[][] matrice=getMatrice();
		int[][] matricePast=getMatricePast();
		int size=this.getSize();
		for (int i = 0; i < size; i+=10) {
			for (int j = 0; j < size; j+=10) {
				int[]neighbors=voisinsIm(i, j);
				int nbVoisinsEtatSuivant=0;
				int etatCourant = matricePast[i][j];
				int etatSuivant = (etatCourant + 1)% nbEtats;
				for (int k = 0; k < neighbors.length; k++) {
					if (neighbors[k] == etatSuivant) {
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


	public int[] voisinsIm(int k, int m) {
		int[][] matricePast=getMatricePast();
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
		listeVoisins[0]=matricePast[kMinus1][m];
        listeVoisins[1]=matricePast[kMinus1][mMinus1];
        listeVoisins[2]=matricePast[kMinus1][mPlus1];
        listeVoisins[3]=matricePast[k][mMinus1];
        listeVoisins[4]=matricePast[k][mPlus1];
        listeVoisins[5]=matricePast[kPlus1][m];
        listeVoisins[6]=matricePast[kPlus1][mMinus1];
        listeVoisins[7]=matricePast[kPlus1][mPlus1];
		this.setMatricePast(matricePast);

		return listeVoisins;
	}



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
