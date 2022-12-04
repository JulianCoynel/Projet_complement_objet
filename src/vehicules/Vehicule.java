package vehicules;

import jonctions.Jonction;
import routes.Route;


/**
 * La classe definissant un Vehicule et sa position sur le reseau routier
 * @author Theo DELORME
 */
public abstract class Vehicule {
	/** Le prochain identifiant a attribuable a un vehicule */
	private static int nextId = 1;

	/** L'identifiant de ce vehicule */
	private final int id;
	/** La longueur de ce vehicule : en nombre de troncon routier */
	private final int longueur;
	/** La vitesse maximale que peu attendre ce vehicule en dehors de toute restriction : en nombre de troncon routier par unite de temps */
	private final int vitesseMax;

	/** La Route ou est localise ce vehicule */
	private Route saRoute;
	/** Le sens de circulation qu'emprunte le vehicule sur saRoute */
	private boolean sens;
	/** L'avencement du vehicule sur saRoute : en nombre de troncon routier parcouru integralement */
	private int borne;
	
	
	/**
	 * Constructeur d'un vehicule
	 * @param l La longueur de ce vehicule : en nombre de troncon routier
	 * @param v La vitesse maximale que peu attendre ce vehicule en dehors de toute restriction : en nombre de troncon routier par unite de temps
	 * @param r La Route ou est localise ce vehicule actuellement
	 * @param s Le sens de circulation qu'emprunte actuelement le vehicule sur la route r
	 */
	public Vehicule (int l, int v, Route r, boolean s) {
		if (l <= 0) {
			throw new IllegalArgumentException("Un vehicule doit avoir une longeur strictement positive");
		}
		id = nextId;
		nextId++;
		longueur = l;
		vitesseMax = v;
		
		setVoie(r,s);
	}
	
	/**
	 * Permet de deplace le vehicule a son nouvelle emplacement apres l'ecoulement d'une unite de temps
	 */
	public void avance() {
		int v = getVitesse();
		borne += v;
		
		Route r = getRoute();
		//Cas ou on arrive a une intersection
		if (borne >= r.getLongueur()) {
			//On attend au feu rouge
			if (r.feuRouge(sens)) {
				borne = r.getLongueur()-1;
			}
			//On tourne dans une direction aleatoire
			else {
				Jonction jonctionAtteinte = r.getJonction(sens);
				Route routeChoisi = jonctionAtteinte.getRouteAleatoire();
				
				setVoie(routeChoisi, jonctionAtteinte.getSensSortant(routeChoisi));
			}
		}
	}
	
	@Override
	public boolean equals(Object o) {
		if (o == null) { return false; }
		if (o == this) { return true; }
		if (o instanceof Vehicule) {
			Vehicule v =  (Vehicule) o;
			return v.id == id;
		}
		else { return false; }
	 }
	 
	/**
	 * Verifie si le vehicule est detectable depuis le troncon routier decrit 
	 * @param route La route sur lequel ce trouve le troncon observe
	 * @param sens Le sens correspondant a la voie observee
	 * @param emplacement La distance en nombre de troncon du troncon observe par rapport au debut de la voie observee
	 * @return true si le vehicule est detectable, false sinon
	 */
	 public boolean estIci(Route route, boolean sens, int emplacement) {
		if (route == null) { return false; }
		if (saRoute.equals(route)) {
			if (borne >= emplacement) { //le vehicule a atteint la borne
				if (emplacement > borne - longueur) { //le vehicule n'a pas depace la borne
					return true;
				}
			}
		}
		return false;
	 }
	 
	 private void setVoie(Route nouvelleRoute, boolean sensSurNouvelleRoute) {
		borne = 0;
		setRoute(nouvelleRoute);
		sens = sensSurNouvelleRoute;
	}
	
	private void setRoute(Route r) {
		if (r == null) {
			throw new IllegalArgumentException("Le vehicule ne peut pas etre sur une Route 'null'");
		}
		saRoute = r;
		r.addVehicule(this);
	}
	
	/**
	 * Permet d'obtenir la route ou est localise ce vehicule 
	 * @return la route concernee
	 */
	public Route getRoute() {
		return saRoute;
	}
	
	/**
	 * Permet d'obtenir l'identifiant de ce vehicule
	 * @return l'identifiant
	 */
	public int getID() {
		return id;
	}

	/**
	 * Permet d'obtenir la vitesse effective du vehicule
	 * @return la vitesse calcule selon les restrictions du reseau routier et du vehicule
	 */
	public int getVitesse() {
		return getRoute().getVitesse(sens, vitesseMax);
	}
}
