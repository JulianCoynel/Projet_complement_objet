package Semaphores;

import java.util.ArrayList;

import Capteurs.Capteur;
import Segments_de_route.SegmentDeRoute;

public class FeuBicolore extends Semaphore {
	
	enum Bicolore {ROUGE, VERT}
	private Bicolore couleur;

	public FeuBicolore(SegmentDeRoute segment, ArrayList<Capteur> capteurs, boolean sens) {
		super(segment, capteurs, sens);
		this.couleur = Bicolore.VERT;
	}
	
	public FeuBicolore(SegmentDeRoute segment,Capteur capteur,boolean sens) {
		super(segment, capteur, sens);
		this.couleur = Bicolore.VERT;
	}
	
	public int contrainteVitesseSemaphore(int vitesseActuelle) {
		int res = 0;
		switch (this.couleur) {
			case VERT:
				res = vitesseActuelle;
			case ROUGE:
				res = 0;
		}
		return res;
	}
	
	public void setCouleur(Bicolore b) {
		couleur = b;
	}
	
	public Bicolore getCouleur() {
		return couleur;
	}

}
