package routes;

import java.util.ArrayList;

import jonctions.Jonction;
import semaphores.Semaphore;
import vehicules.Vehicule;

public class Route {
	private int longueur;
	private ArrayList<Jonction> sesJonctions;
	private ArrayList<Semaphore> sesSemaphores;
	private ArrayList<Vehicule> sesVehicules;
	
	public Route (Jonction A, Jonction B){
		
	}

	public int getLongueur() {
		// TODO Auto-generated method stub
		return 0;
	}

	public Jonction getJonction(boolean sens) {
		// TODO Auto-generated method stub
		return null;
	}

	public int getLimitationVitesse(boolean sens) {
		// TODO Auto-generated method stub
		return 0;
	}
}
