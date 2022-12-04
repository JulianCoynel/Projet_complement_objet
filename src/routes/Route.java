package routes;

import java.util.HashSet;
import java.util.Set;

import capteurs.Capteur;
import exception.ErreurConstruction;
import jonctions.Jonction;
import semaphores.Semaphore;
import vehicules.Vehicule;

/**
 * La classe definissant un segment de route
 * @author Theo DELORME
 */
public class Route {
	/** Le nombre de troncon qui constituent la route **/
	private final int  longueur;
	/** La jonction atteinte apres avoir parcouru la route dans le sens true **/
	private Jonction jonctionSensTrue;
	/** La jonction atteinte apres avoir parcouru la route dans le sens false **/
	private Jonction jonctionSensFalse;
	/** Ensembles des Semaphore de la route **/
	private Set<Semaphore> sesSemaphores;
	/** Ensembles des Vehicule present actuellement sur cette route **/
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
	
	/**
	 * Ajoute un vehicule sur cette route
	 * @param vehicule le vehicule a ajouter
	 */
	public void addVehicule(Vehicule vehicule) {
		sesVehicules.add(vehicule);
	}
	
	/**
	 * Retire un vehicule de cette route
	 * @param vehicule le vehicule a retirer
	 */
	public void removeVehicule(Vehicule vehicule) {
		sesVehicules.remove(vehicule);
	}
	
	/**
	 * Inscrit un semaphore a la liste de tout les semaphore de cette route
	 * @param semaphore le semaphore a ajouter
	 */
	public void addSemaphore(Semaphore semaphore) {
		sesSemaphores.add(semaphore);
	}
	
	/**
	 * Inscrit un capteur a la liste de tout les capteur de cette route
	 * @param c le capteur a ajouter
	 */
	public void addCapteur(Capteur c) {
		sesCapteurs.add(c);
	}
	
	/**
	 * Indique si un feu situer a l'extremite de la route dans le sens d'ecrit est ROUGE
	 * @param sens le sens observe
	 * @return true si l'un des feux decris est ROUGE, false si il n'y a pas de feu ou qu'aucun n'est ROUGE
	 */
	public boolean feuRouge(boolean sens) {
		for (Semaphore s : sesSemaphores) {
			if (s.getSonSens() == sens) {
				if (s.estRouge())
					return true;
			}
		}
		return false;
	}
	
	/**
	 * Indique si un feu est situer a l'extremite de la route dans le sens d'ecrit
	 * @param sens le sens observe
	 * @return true si un feu est present dans le bon sens, false sinon
	 */
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
	
	/**
	 * Retourne le nombre de troncon qui constituent cette route
	 * @return la longueur de la route
	 */
	public int getLongueur() {
		return longueur;
	}
	
	/**
	 * Retourne la jonction atteinte apres avoir parcouru la voie dans le sens decrit
	 * @param sens le sens dont on souhaite connaitre la destination
	 * @return La jonction correspondante
	 * @throws ErreurConstruction si la route n'est pas valide
	 */
	public Jonction getJonction(boolean sens) throws ErreurConstruction {
		if (!estValide()) {
			throw new ErreurConstruction("La route n'est pas correctement construite verifier qu'elle a bien 2 jonctions");
		}
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
				} else {
					v = v / s.getDivisionVitesse();
				}
			}
		}
		return v;
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
				if (!premier)	{ sb.append(" ; ");}
				else			{ premier = false; }
				sb.append(s.toString());
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
				sb.append(c.toString());
			}
		}
		sb.append(']');
		sb.append('\n');
		
		//Sens False :
		sb.append("SensFalse : ");
		vehiculePresent = false;
		for (int i = getLongueur()-1 ; i >= 0 ; i-- ) {
			for (Vehicule v : sesVehicules) {
				if (v.estIci(this, false, i)) {
					vehiculePresent = true;
					break;
				}
			}
			if (vehiculePresent) {
				sb.append('>');
			} else {
				sb.append('-');
			}
			
			vehiculePresent = false;
		}

		premier = true;
		sb.append('[');
		for (Semaphore s : sesSemaphores) {
			if (s.getSonSens() == false) {
				if (!premier)	{ sb.append(" ; ");}
				else			{ premier = false; }
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
