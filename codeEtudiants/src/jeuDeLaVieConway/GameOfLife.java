package jeuDeLaVieConway;

import java.util.ArrayList;
import java.util.Iterator;


public class GameOfLife {
	/**
	*Taille de la matrice de simulation. La matrice est carré de taille size*size.
	*/
	private int size;
	/**
	*Matrice représentant la simulation à l'instant n.
	*/
	private int [][] matrice;
	/**
	*Matrice représentant la simulation à l'état n-1. On se base sur l'état n-1 pour décrire l'état n.
	*/
	private int [][] matricePast;

	/**
	*@param size Taille de la matrice de simulation
	*/
	public GameOfLife(int size) {
		this.size=size;
		matrice = new int[size][size];
		matricePast = new int[size][size];
	}
	
	protected static final int VIVANT=1;
	protected static final int MORT=0;
	
	public GameOfLife() {
		super();
	}

	/**
	*@param size Taille de la matrice de simulation
	*/
	public void setSize(int size) {
		this.size = size;
	}

	public int getSize() {
		return size;
	}

	public int[][] getMatrice(){
		return matrice;
	}

	public int[][] getMatricePast() {
		return matricePast;
	}

	public void setMatricePast(int[][] matricePast) {
		this.matricePast = matricePast;
	}

	public void setMatrice(int[][] matrice) {
		this.matrice = matrice;
	}

	/**
	*Passage de l'état n à l'état n+1 de la simulation. L'état de chaque cellule est recalculé suivant les règle du jeu de la vie de Conway.
	*/
	public void move() {
		for (int i = 0; i < size; i+=10) {
			for (int j = 0; j < size; j+=10) {
				ArrayList<Integer> neighbors=voisins(i,j);
				int nbVoisinsVIVANTs=0;
				for (Iterator<Integer> it = neighbors.iterator(); it.hasNext();) {
					int etat = it.next();
					if(etat==VIVANT) {
						nbVoisinsVIVANTs++;
					}
				}
				if(matricePast[i][j]==VIVANT) {
					if(nbVoisinsVIVANTs==2 || nbVoisinsVIVANTs==3) {
						matrice[i][j]=VIVANT;
					}
					else {
						matrice[i][j] = MORT;
					}
				}
				if(matricePast[i][j]==MORT) {
					if(nbVoisinsVIVANTs==3) {
						matrice[i][j]=VIVANT;
					}
					else {
						matrice[i][j] = MORT;
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

	/**
	*Initialisation de la matrice de simulation. Les cellules initialement VIVANTes sont choisies selon une forme particulière.
	*/
	public void init() {
		for (int i = 0; i < size; i+=10) {
			for (int j = 0; j < size; j+=10) {
				matricePast[i][j]=MORT;
				matrice[i][j]=MORT;
			}
		}
		//Pour une meilleure visibilité n'utiliser qu'une forme et commenter les autres lignes

		//1st shape ruche modifié aboutissant à 4 ruches
		//firstShape();
		//2nd shape vaisseau
		shipShape();
		//QrCode: bad example it's due to a reference error but it's beautiful enjoy;)
		//qrCodeShape();
		//melange du vaisseau et des ruches
		//fourthShape();

	}

	/**
	*Initialisation possible de l'ensemble des cellules VIVANTes.
	*/
	void firstShape() {
		matrice[(size/2)-10][size/2]=VIVANT;
		matrice[(size/2)-10][(size/2)+10]=VIVANT;
		matrice[size/2][(size/2)-10]=VIVANT;
		matrice[size/2][(size/2)+20]=VIVANT;
		matrice[(size/2)+10][size/2]=VIVANT;
		matrice[(size/2)+10][(size/2)+10]=VIVANT;
		matrice[(size/2)-10][(size/2)+20]=VIVANT;

		matricePast[(size/2)-10][size/2]=VIVANT;
		matricePast[(size/2)-10][(size/2)+10]=VIVANT;
		matricePast[size/2][(size/2)-10]=VIVANT;
		matricePast[size/2][(size/2)+20]=VIVANT;
		matricePast[(size/2)+10][size/2]=VIVANT;
		matricePast[(size/2)+10][(size/2)+10]=VIVANT;
		matricePast[(size/2)-10][(size/2)+20]=VIVANT;
	}

	/**
	*Initialisation possible de l'ensemble des cellules VIVANTes.
	*/
	void shipShape() {
		matrice[0][0]=VIVANT;
		matrice[0][10]=VIVANT;
		matrice[0][20]=VIVANT;
		matrice[10][0]=VIVANT;
		matrice[20][10]=VIVANT;

		matricePast[0][0]=VIVANT;
		matricePast[0][10]=VIVANT;
		matricePast[0][20]=VIVANT;
		matricePast[10][0]=VIVANT;
		matricePast[20][10]=VIVANT;
	}

	/**
	*Initialisation possible de l'ensemble des cellules VIVANTes.
	*/
	void qrCodeShape() {
		matrice[(size/2)-10][size/2]=VIVANT;
		matrice[(size/2)-10][(size/2)+10]=VIVANT;
		matrice[size/2][(size/2)-10]=VIVANT;
		matrice[size/2][(size/2)+20]=VIVANT;
		matrice[(size/2)+10][size/2]=VIVANT;
		matrice[(size/2)+10][(size/2)+10]=VIVANT;
		matrice[(size/2)-10][(size/2)+20]=VIVANT;
		matricePast=matrice;
	}

	/**
	*Initialisation possible de l'ensemble des cellules VIVANTes.
	*/
	void fourthShape() {
		firstShape();
		matrice[0][150]=VIVANT;
        matrice[0][140]=VIVANT;
        matrice[10][130]=VIVANT;
        matrice[20][120]=VIVANT;
        matrice[30][130]=VIVANT;
        matrice[40][130]=VIVANT;
        matrice[50][140]=VIVANT;
        matrice[50][150]=VIVANT;
        matrice[40][160]=VIVANT;
        matrice[30][170]=VIVANT;
        matrice[20][160]=VIVANT;


        matricePast[0][150]=VIVANT;
        matricePast[0][140]=VIVANT;
        matricePast[10][130]=VIVANT;
        matricePast[20][120]=VIVANT;
        matricePast[30][130]=VIVANT;
        matricePast[40][130]=VIVANT;
        matricePast[50][140]=VIVANT;
        matricePast[50][150]=VIVANT;
        matricePast[40][160]=VIVANT;
        matricePast[30][170]=VIVANT;
        matricePast[20][160]=VIVANT;
	}

	/**
	*Liste de l'ensemble des cellules voisine d'une cellule.
	*@param k Ligne de la cellule dont on cherche les voisins.
	*@param m Colone de la cellule dont on cherche les voisins.
	*/
	public ArrayList<Integer> voisins(int k, int m) {

		ArrayList<Integer> listeVoisins = new ArrayList<Integer>();

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
