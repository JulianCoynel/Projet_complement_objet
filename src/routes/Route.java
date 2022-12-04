package routes;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import capteurs.Capteur;
import exception.ErreurConstruction;
import jonctions.Jonction;
import semaphores.Semaphore;
import vehicules.Vehicule;

public class Route {
	private final int  longueur;
	private Jonction jonctionSensTrue;
	private Jonction jonctionSensFalse;
	/** Ensembles des Semaphore de la route **/
	private Set<Semaphore> sesSemaphores;
	/** Ensembles des Vehicule de la route **/
	private Set<Vehicule> sesVehicules;
	/** Ensembles des Capteur de la route **/
	private Set<Capteur> sesCapteurs;
	
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
		sesCapteurs = new HashSet<Capteur>();
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

	public void addVehicule(Vehicule vehicule) {
		sesVehicules.add(vehicule);
	}
	
	public void addSemaphore(Semaphore semaphore) {
		sesSemaphores.add(semaphore);
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
	
	public boolean feu(boolean sens) {
		for (Semaphore s : sesSemaphores) {
			if (s.getSonSens() == sens) {
				return true;
			}
		}
		return false;
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
	
	/**
	 * Permet d'obtenir les vehicules visible depuis une position precise de cette route.
	 * @param sens le sens de circulation observe.
	 * @param borne le troncon observe dependant du sens.
	 * @return L'ensemble des vehicules visible a cette position.
	 */
	public Set<Vehicule> getVehicules (boolean sens, int borne) {
		Set<Vehicule> res = new HashSet<Vehicule>();
		for (Vehicule v : sesVehicules) {
			if (v.estIci(this, sens, borne)) {
				res.add(v);
			}
		}
		return res;
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
	 * Calcule la vitesse effective d'un vehicule circulant sur cette route
	 * @param sens le sens de circulation du vehicule
	 * @param vitesseMaxVehicule la vitesse maximale que peu atteindre le vehicule
	 * @return la vitesse effective du vehicule selon l'etat 
	 */
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
	
	@Override
	public String toString () {
		StringBuilder sb = new StringBuilder();
		boolean vehiculePresent, premier;
		
		//Sens True :
		sb.append("SensTrue  : ");
		vehiculePresent = false;
		for (int i = 0 ; i < getLongueur() ; i++ ) {
			for (Vehicule v : sesVehicules) {
				if (v.estIci(this, true, i)) {
					vehiculePresent = true;
					break;
				}
			}
			if (vehiculePresent) {
				sb.append('<');
			} else {
				sb.append('-');
			}
			
			vehiculePresent = false;
		}

		premier = true;
		sb.append('[');
		for (Semaphore s : sesSemaphores) {
			if (s.getSonSens() == true) {
				if (!premier) {
					sb.append(" ; ");
					premier = false;
				}
				sb.append(s);
			}
		}
		premier = true;
		sb.append(" | ");
		for (Capteur c : sesCapteurs) {
			if (c.getSonSens() == true) {
				if (!premier) {
					sb.append(" ; ");
					premier = false;
				}
				sb.append(c);
			}
		}
		sb.append(']');
		
		//Sens False :
		sb.append("SensFalse : ");
		vehiculePresent = false;
		for (int i = 0 ; i < getLongueur() ; i++ ) {
			for (Vehicule v : sesVehicules) {
				if (v.estIci(this, false, i)) {
					vehiculePresent = true;
					break;
				}
			}
			if (vehiculePresent) {
				sb.append('<');
			} else {
				sb.append('-');
			}
			
			vehiculePresent = false;
		}

		premier = true;
		sb.append('[');
		for (Semaphore s : sesSemaphores) {
			if (s.getSonSens() == false) {
				if (!premier) {
					sb.append(" ; ");
					premier = false;
				}
				sb.append(s);
			}
		}
		premier = true;
		sb.append(" | ");
		for (Capteur c : sesCapteurs) {
			if (c.getSonSens() == false) {
				if (!premier) {
					sb.append(" ; ");
					premier = false;
				}
				sb.append(c);
			}
		}
		sb.append(']');
		
		return sb.toString();
	}
}
