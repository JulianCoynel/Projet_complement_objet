package Semaphores;

import java.util.ArrayList;

import Capteurs.Capteur;
import Segments_de_route.SegmentDeRoute;

public class FeuTricolore extends Semaphore {
	
	enum Tricolore {ROUGE, ORANGE, VERT}
	private Tricolore couleur;

	public FeuTricolore(SegmentDeRoute segment, ArrayList<Capteur> capteurs, boolean sens) {
		super(segment, capteurs, sens);
		this.couleur = Tricolore.VERT;
	}
	
	public FeuTricolore(SegmentDeRoute segment,Capteur capteur,boolean sens) {
		super(segment, capteur, sens);
		this.couleur = Tricolore.VERT;
	}
	
	public int contrainteVitesseSemaphore(int vitesseActuelle) {
		int res = 0;
		switch (this.couleur) {
			case VERT:
				res = vitesseActuelle;
			case ORANGE:
				res = vitesseActuelle/2;
			case ROUGE:
				res = 0;
		}
		return res;
	}
	
	public void setCouleur(Tricolore c) {
		couleur = c;
	}
	
	public Tricolore getCouleur() {
		return couleur;
	}

}
