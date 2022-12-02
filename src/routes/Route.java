package routes;

import java.util.ArrayList;

import jonctions.Jonction;
import semaphores.Semaphore;
import vehicules.Vehicule;

public class Route {
	private int longueur;
	private Jonction jonctionSensTrue;
	private Jonction jonctionSensFalse;
	private ArrayList<Semaphore> sesSemaphores;
	private ArrayList<Vehicule> sesVehicules;
	
	public Route (Jonction A, Jonction B){
		
	}

	public int getLongueur() {
		return longueur;
	}

	public Jonction getJonction(boolean sens) {
		if (sens == true) {
			return jonctionSensTrue;
		} else {
			return jonctionSensFalse;
		}
	}

	public int getVitesse(boolean sens, int vitesseActuel) {
		int v = vitesseActuel;
		for (Semaphore s : sesSemaphores) {
			if (s.getSonSens() == sens) {
				v = Math.min(v, s.contrainteVitesseSemaphore(vitesseActuel));
			}
		}
		return 0;
	}
}
