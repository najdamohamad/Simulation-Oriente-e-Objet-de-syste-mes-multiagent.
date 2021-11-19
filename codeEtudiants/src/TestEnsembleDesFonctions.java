import java.awt.Color;
import java.util.Scanner;

import boids.BoidsSimulator;
import boids.PredateursSimulator;
import gui.GUISimulator;
import jeuDeLImmigration.GameOfImmigrationSimulator;
import jeuDeLaVieConway.GameOfLifeSimulator;
import segregationSchelling.SegregationSimulator;
import simulateurBalles.BallsSimulator;

public class TestEnsembleDesFonctions {

	public static void main(String[] args) {
		GUISimulator gui;
		int choix;
		Scanner scan =new Scanner(System.in);
		
		
		
		System.out.println("Veuillez choisir parmi les options suivantes");
		
		System.out.println("[1] Simulation de balles rebondissantes ");
		System.out.println("[2] GameOfLife by Conway ");
		System.out.println("[3] GameOfImmigration ");
		System.out.println("[4] Segregation by Schelling ");
		System.out.println("[5] Boids classique");
		System.out.println("[6] Boids système proies/predateurs");

		choix=scan.nextInt();
		
		switch (choix) {
			case 1:
				System.out.println("Vous avez choisi la simulation de balles rebondissantes ");
				System.out.println(" Veuillez entre le nombre de balles ");
				int nbBalls=scan.nextInt();
				gui=new GUISimulator(500, 500, Color.BLACK);
				BallsSimulator ballsSimulator= new BallsSimulator(nbBalls,gui);
				gui.setSimulable(ballsSimulator);
				break;
			case 2:
				System.out.println("Vous avez choisi GameOfLife by Conway ");
				System.out.println("Vous avez choisi GameOfLife ");
				System.out.println(" Veuillez entrer un entier entre 1 et 4 pour la forme qui apparaitra ");
				System.out.println("[1] Ruche modifiée aboutissant sur 4 ruches ");
				System.out.println("[2] Vaisseau ");
				System.out.println("[3] QRCode :Mauvaise utilisation du code mais resultat beau à voir");
				System.out.println("[4] Melange du vaisseau et des ruches ");
				int choix_forme=scan.nextInt();
				gui=new GUISimulator(500, 500, Color.WHITE);
				GameOfLifeSimulator conwaySimulator = new GameOfLifeSimulator(gui,choix_forme);
				gui.setSimulable(conwaySimulator);	
				break;
			case 3:
				System.out.println("Vous avez choisi GameOfImmigration ");
				System.out.println(" Veuillez entre le nombre d'etat ");
				int nbEtats=scan.nextInt();
				gui=new GUISimulator(500, 500, Color.WHITE);
				GameOfImmigrationSimulator immigrationSimulator = new GameOfImmigrationSimulator(gui, nbEtats);
				gui.setSimulable(immigrationSimulator);
				break;
			case 4:
				System.out.println("Vous avez choisi Segregation by Schelling ");
				System.out.println(" Veuillez entre le nombre de couleurs ");
				int nbCouleurs=scan.nextInt();
				System.out.println(" Veuillez entre le seuil de tolerance. Pour une meilleure simulation il doit etre d'au moins 5 ");
				int seuil=scan.nextInt();
				gui=new GUISimulator(500, 500, Color.WHITE);
				SegregationSimulator schellingSimulator = new SegregationSimulator(gui, nbCouleurs, seuil);
				gui.setSimulable(schellingSimulator);
				break;
			case 5:
				System.out.println("Vous avez choisi la simulation de Boids classiques ");
				System.out.println(" Veuillez entre le nombre de boids ");
				int nbBoids=scan.nextInt();
				gui=new GUISimulator(500, 500, Color.BLACK);
				BoidsSimulator boidsSimulator = new BoidsSimulator(nbBoids,gui);
				gui.setSimulable(boidsSimulator);
				break;
			case 6:
				System.out.println("Vous avez choisi la simulation système proies/predateurs ");
				System.out.println(" Veuillez entre le nombre de boids ");
				int nbBoidsPredatorMode=scan.nextInt();
				gui=new GUISimulator(500, 500, Color.WHITE);
				PredateursSimulator boidsPredatorModeSimulation = new PredateursSimulator(nbBoidsPredatorMode,gui);
				gui.setSimulable(boidsPredatorModeSimulation);
			
			default:
				break;
			}
		scan.close();

		
	}

}
