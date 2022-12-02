package vehicules;

import jonctions.Jonction;
import routes.Route;

public abstract class Vehicule {
	private static int nextId = 0;
	
	private final int id;
	private final int longueur;
	private final int vitesseMax;
	
	private Route sonSegmentDeRoute;
	private boolean sens;
	private int borne;
	
	
	public Vehicule (int l, int v, Route r, boolean s) {
		nextId++;
		id = nextId;
		longueur = l;
		vitesseMax = v;
		
		sonSegmentDeRoute = r;
		sens = s;
	}
	
	private int getVitesse() {
		return sonSegmentDeRoute.getVitesse(sens, vitesseMax);
	}
	
	public void avance () {
		int v = getVitesse();
		borne += v;
		
		//cas ou on arrive a une intersection => on tourne dans une direction aleatoire
		if (borne >= sonSegmentDeRoute.getLongueur()) {
			Jonction jonctionAtteinte = sonSegmentDeRoute.getJonction(sens);
			Route routeChoisi = jonctionAtteinte.getRouteAleatoire();
			
			setVoie(routeChoisi, jonctionAtteinte.getSensSortant(routeChoisi));
		}
	}

	private void setVoie(Route nouvelleRoute, boolean sensSurNouvelleRoute) {
		sonSegmentDeRoute = nouvelleRoute;
		sens = sensSurNouvelleRoute;
		borne = 0;
	}
}
