package Vehicules;

import Segments_de_route.SegmentDeRoute;

public class Vehicule {
	private static int nextId = 0;
	
	private final int id;
	private final int longueur;
	private final int vitesseMax;
	
	private SegmentDeRoute sonSegmentDeRoute;
	private boolean sens;
	private int borne;
	
	
	public Vehicule (int l, int v) {
		nextId++;
		id = nextId;
		longueur = l;
		vitesseMax = v;
	}
	
	private int getVitesse() {
		return Math.max(vitesseMax, sonSegmentDeRoute.vitesseMax(sens));
	}
	
	public void avance () {
		int v = getVitesse();
		borne += v;
		
		if (borne >= sonSegmentDeRoute.getLongueur()) {
			
		}
	}
}
