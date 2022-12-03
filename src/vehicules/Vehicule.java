package vehicules;

import jonctions.Jonction;
import routes.Route;

public abstract class Vehicule {
	private static int nextId = 0;
	
	private final int id;
	private final int longueur;
	private final int vitesseMax;
	
	private Route saRoute;
	private boolean sens;
	private int borne;
	
	
	public Vehicule (int l, int v, Route r, boolean s) {
		if (l <= 0) {
			throw new IllegalArgumentException("Un vehicule doit avoir une longeur strictement positive");
		}
		nextId++;
		id = nextId;
		longueur = l;
		vitesseMax = v;
		
		setVoie(r,s);
	}
	
	
	public Route getRoute() {
		return saRoute;
	}

	public int getVitesse() {
		return getRoute().getVitesse(sens, vitesseMax);
	}
	
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
	
	 public boolean equals(Object o) {
		 if (o == null) { return false; }
		 if (o == this) { return true; }
		 if (o instanceof Vehicule) {
			 Vehicule v =  (Vehicule) o;
			 return v.id == id;
		 }
		 else { return false; }
	 }
	 
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
}
