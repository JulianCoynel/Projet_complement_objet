package Semaphores;

import java.util.ArrayList;

import Capteurs.Capteur;
import Segments_de_route.SegmentDeRoute;

public class PanneauLimitationVitesse extends Semaphore {
	
	private int vitesseMax;

	public PanneauLimitationVitesse(SegmentDeRoute segment, ArrayList<Capteur> capteurs, boolean sens,int v) {
		super(segment, capteurs, sens);
		this.vitesseMax = v;
	}
	
	public PanneauLimitationVitesse(SegmentDeRoute segment,Capteur capteur,boolean sens,int v) {
		super(segment, capteur, sens);
		this.vitesseMax = v;
	}
	
	public int contrainteVitesseSemaphore(int vitesseActuelle) {
		return vitesseMax;
	}
	
	public void setVitesseMax(int v) {
		vitesseMax = v;
	}
	
	public int getVitesseMax() {
		return vitesseMax;
	}

}
