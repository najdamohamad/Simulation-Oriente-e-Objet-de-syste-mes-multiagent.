package jeuDeLaVieConway;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;

import gui.GUISimulator;
import gui.Rectangle;

public class GameOfLife {
	private int size;
	private Etat [][] matrice;
	private Etat [][] matricePast;

	public GameOfLife(int size) {
		this.size=size;
		matrice = new Etat[size][size];
		matricePast = new Etat[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				matrice[i][j]=Etat.Mort;
				matricePast[i][j]=Etat.Mort;
			}
		}
	}
	
	
	public void move() {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
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
		
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				matricePast[i][j]=matrice[i][j];
			}
		}
	}
	
	public void init() {
		matrice[size/2][(size/2)-1]=Etat.Vivant;
		matrice[(size/2)+1][(size/2)-1]=Etat.Vivant;
		matrice[(size/2)-1][size/2]=Etat.Vivant;
		matrice[(size/2)-2][size/2]=Etat.Vivant;
		matrice[size/2][(size/2)+1]=Etat.Vivant;
		matrice[(size/2)+1][(size/2)+1]=Etat.Vivant;
		
		
		matricePast[size/2][(size/2)-1]=Etat.Vivant;
		matricePast[(size/2)+1][(size/2)-1]=Etat.Vivant;
		matricePast[(size/2)-1][size/2]=Etat.Vivant;
		matricePast[(size/2)-2][size/2]=Etat.Vivant;
		matricePast[size/2][(size/2)+1]=Etat.Vivant;
		matricePast[(size/2)+1][(size/2)+1]=Etat.Vivant;
	}
	
	public ArrayList<Etat> voisins(int k, int m) {
		
		ArrayList<Etat> listeVoisins = new ArrayList<Etat>();
		
		int kMinus1=k-1;
		int kPlus1=k+1;
		int mMinus1=m-1;
		int mPlus1=m+1;
		
		if (k==0) {
			kMinus1=size-1;
		}
		else if (k==size-1) {
			kPlus1=0;
		}
		if (m==0) {
			mMinus1=size-1;
		}
		else if(m==size-1) {
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
	
	public void dessiner(GUISimulator gui) {
		gui.reset();
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if(matrice[i][j]==Etat.Vivant) {
					gui.addGraphicalElement(new Rectangle(i,j,Color.BLUE,Color.BLUE,10));
				}
				else {
					gui.addGraphicalElement(new Rectangle(i,j,Color.WHITE,Color.WHITE,10));
				}
			}
		}		
	}
	
	
	
	
	
	
	
	
	
}


