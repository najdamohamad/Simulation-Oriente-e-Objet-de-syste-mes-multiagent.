package jeuDeLaVieConway;

import java.util.ArrayList;
import java.util.Iterator;


public class GameOfLife {
	private int size;
	private Etat [][] matrice;
	private Etat [][] matricePast;

	public GameOfLife(int size) {
		this.size=size;
		matrice = new Etat[size][size];
		matricePast = new Etat[size][size];
	}

	public GameOfLife() {
		super();
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getSize() {
		return size;
	}

	public Etat[][] getMatrice(){
		return matrice;
	}

	public void move() {
		for (int i = 0; i < size; i+=10) {
			for (int j = 0; j < size; j+=10) {
				ArrayList<Etat> neighbors=voisins(i,j);
				int nbVoisinsVivants=0;
				for (Iterator<Etat> it = neighbors.iterator(); it.hasNext();) {
					Etat etat = (Etat) it.next();
					if(etat==Etat.Vivant) {
						nbVoisinsVivants++;
					}
				}
				if(matricePast[i][j]==Etat.Vivant) {
					if(nbVoisinsVivants==2 || nbVoisinsVivants==3) {
						matrice[i][j]=Etat.Vivant;
					}
					else {
						matrice[i][j] = Etat.Mort;
					}
				}
				if(matricePast[i][j]==Etat.Mort) {
					if(nbVoisinsVivants==3) {
						matrice[i][j]=Etat.Vivant;
					}
					else {
						matrice[i][j] = Etat.Mort;
					}
				}
			}
		}
		//on met à jour matricePast
		for (int i = 0; i < size; i+=10) {
			for (int j = 0; j < size; j+=10) {
				matricePast[i][j]=matrice[i][j];
			}
		}
	}

	public void init() {
		for (int i = 0; i < size; i+=10) {
			for (int j = 0; j < size; j+=10) {
				matricePast[i][j]=Etat.Mort;
				matrice[i][j]=Etat.Mort;
			}
		}
		//Pour une meilleure visibilité n'utiliser qu'une forme et commenter les autres lignes

		//1st shape ruche modifié aboutissant à 4 ruches
		firstShape();
		//2nd shape vaisseau
		//shipShape();
		//QrCode: bad example it's due to a reference error but it's beautiful enjoy;)
		//qrCodeShape();
		//melange du vaisseau et des ruches
		//fourthShape();

	}

	void firstShape() {
		matrice[(size/2)-10][size/2]=Etat.Vivant;
		matrice[(size/2)-10][(size/2)+10]=Etat.Vivant;
		matrice[size/2][(size/2)-10]=Etat.Vivant;
		matrice[size/2][(size/2)+20]=Etat.Vivant;
		matrice[(size/2)+10][size/2]=Etat.Vivant;
		matrice[(size/2)+10][(size/2)+10]=Etat.Vivant;
		matrice[(size/2)-10][(size/2)+20]=Etat.Vivant;

		matricePast[(size/2)-10][size/2]=Etat.Vivant;
		matricePast[(size/2)-10][(size/2)+10]=Etat.Vivant;
		matricePast[size/2][(size/2)-10]=Etat.Vivant;
		matricePast[size/2][(size/2)+20]=Etat.Vivant;
		matricePast[(size/2)+10][size/2]=Etat.Vivant;
		matricePast[(size/2)+10][(size/2)+10]=Etat.Vivant;
		matricePast[(size/2)-10][(size/2)+20]=Etat.Vivant;
	}

	void shipShape() {
		matrice[0][0]=Etat.Vivant;
		matrice[0][10]=Etat.Vivant;
		matrice[0][20]=Etat.Vivant;
		matrice[10][0]=Etat.Vivant;
		matrice[20][10]=Etat.Vivant;

		matricePast[0][0]=Etat.Vivant;
		matricePast[0][10]=Etat.Vivant;
		matricePast[0][20]=Etat.Vivant;
		matricePast[10][0]=Etat.Vivant;
		matricePast[20][10]=Etat.Vivant;
	}

	void qrCodeShape() {
		matrice[(size/2)-10][size/2]=Etat.Vivant;
		matrice[(size/2)-10][(size/2)+10]=Etat.Vivant;
		matrice[size/2][(size/2)-10]=Etat.Vivant;
		matrice[size/2][(size/2)+20]=Etat.Vivant;
		matrice[(size/2)+10][size/2]=Etat.Vivant;
		matrice[(size/2)+10][(size/2)+10]=Etat.Vivant;
		matrice[(size/2)-10][(size/2)+20]=Etat.Vivant;
		matricePast=matrice;
	}

	void fourthShape() {
		firstShape();
		matrice[0][150]=Etat.Vivant;
        matrice[0][140]=Etat.Vivant;
        matrice[10][130]=Etat.Vivant;
        matrice[20][120]=Etat.Vivant;
        matrice[30][130]=Etat.Vivant;
        matrice[40][130]=Etat.Vivant;
        matrice[50][140]=Etat.Vivant;
        matrice[50][150]=Etat.Vivant;
        matrice[40][160]=Etat.Vivant;
        matrice[30][170]=Etat.Vivant;
        matrice[20][160]=Etat.Vivant;


        matricePast[0][150]=Etat.Vivant;
        matricePast[0][140]=Etat.Vivant;
        matricePast[10][130]=Etat.Vivant;
        matricePast[20][120]=Etat.Vivant;
        matricePast[30][130]=Etat.Vivant;
        matricePast[40][130]=Etat.Vivant;
        matricePast[50][140]=Etat.Vivant;
        matricePast[50][150]=Etat.Vivant;
        matricePast[40][160]=Etat.Vivant;
        matricePast[30][170]=Etat.Vivant;
        matricePast[20][160]=Etat.Vivant;
	}

	public ArrayList<Etat> voisins(int k, int m) {

		ArrayList<Etat> listeVoisins = new ArrayList<Etat>();

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
		listeVoisins.add(matricePast[kMinus1][m]);
        listeVoisins.add(matricePast[kMinus1][mMinus1]);
        listeVoisins.add(matricePast[kMinus1][mPlus1]);
        listeVoisins.add(matricePast[k][mMinus1]);
        listeVoisins.add(matricePast[k][mPlus1]);
        listeVoisins.add(matricePast[kPlus1][m]);
        listeVoisins.add(matricePast[kPlus1][mMinus1]);
        listeVoisins.add(matricePast[kPlus1][mPlus1]);

		return listeVoisins;
	}


}
