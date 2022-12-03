package routes;

import java.util.HashSet;
import java.util.Set;

import exception.ErreurConstruction;
import jonctions.Jonction;
import semaphores.Semaphore;
import vehicules.Vehicule;

public class Route {
	private final int  longueur;
	private Jonction jonctionSensTrue;
	private Jonction jonctionSensFalse;
	private Set<Semaphore> sesSemaphores;
	private Set<Vehicule> sesVehicules;
	
	/**
	 * Constructeur d'une route 2 jonctions doivent être connecte a postiori pour que la route soit valide.
	 * @param l
	 */
	public Route (int l){
		longueur = l;
		jonctionSensTrue = null;
		jonctionSensFalse = null;
		sesSemaphores = new HashSet<Semaphore>();
		sesVehicules = new HashSet<Vehicule>();
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
	
	/**
	 * Permet d'ajouter une Jonction a une des extremites de la route pour laquelle il n'y a pas encore de jonction attribue.
	 * @param j la Jonction a ajouter.
	 * @return le sens vers lequel les Vehicule doivent aller pour rejoindre cette jonction.
	 * @throws ErreurConstruction
	 */
	public boolean addJonction(Jonction j) throws ErreurConstruction{
		if (jonctionSensTrue == null) {
			jonctionSensTrue = j;
			return true;
		} else if (jonctionSensFalse == null) {
			jonctionSensFalse = j;
			return false;
		}
		else {
			throw new ErreurConstruction("Une route ne peu avoir que deux jonctions");
		}
	}
	
	public int getVitesse(boolean sens, int vitesseMaxVehicule) {
		int v = vitesseMaxVehicule;
		for (Semaphore s : sesSemaphores) {
			if (s.getSonSens() == sens) {
				int limitation = s.getLimitationVitesse();
				if (limitation != -1) {
					v = Math.min(v, limitation);
				}
				v = v / s.getDivisionVitesse();
			}
		}
		return 0;
	}
	
	public boolean feuRouge(boolean sens) {
		for (Semaphore s : sesSemaphores) {
			if (s.getSonSens() == sens) {
				if (s.estRouge())
					return true;
			}
		}
		return false;
	}

	public void addVehicule(Vehicule vehicule) {
		sesVehicules.add(vehicule);
	}
	
	public void addSemaphore(Semaphore semaphore) {
		sesSemaphores.add(semaphore);
	}
	
	/**
	 * Permet de s'assure que le reseau routier construit en cette instant respecte bien les regles pour cette route
	 * @return true si la route est correctement connectee, false sinon
	 */
	public boolean estValide () {
		if (jonctionSensTrue != null) {
			if (jonctionSensFalse != null) {
				return true;
			}
		}
		return false;
	}
}
