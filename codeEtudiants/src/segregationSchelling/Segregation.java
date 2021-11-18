package segregationSchelling;

import java.awt.Color;
import java.awt.Point;
import java.util.LinkedList;
import java.util.Random;

import jeuDeLImmigration.GameOfImmigration;

public class Segregation extends GameOfImmigration {
	private int seuil;
	private int nbCouleurs;
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

	@Override
	public Color[] initialiseCouleurs() {
		Color[] couleurs = new Color[nbCouleurs+1];
		couleurs[0]=Color.WHITE;//les maisons vacantes sont en blanc
		couleurs[1]=Color.RED;
		couleurs[2]=Color.BLUE;
		couleurs[3]=Color.BLACK;
		for (int i = 4; i < couleurs.length; i++) {
			couleurs[i]=couleurs[i-1].brighter();
		}
		return couleurs;
	}

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
