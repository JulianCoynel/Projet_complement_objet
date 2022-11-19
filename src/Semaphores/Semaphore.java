package Semaphores;

import java.util.ArrayList;

import Capteurs.Capteur;
import Segments_de_route.SegmentDeRoute;

public abstract class Semaphore {
	private SegmentDeRoute sonSegment;
	private ArrayList<Capteur> sesCapteurs;
	private boolean sens;
	
	public Semaphore(SegmentDeRoute segment,ArrayList<Capteur> capteurs,boolean sens) {
		sonSegment = segment;
		sesCapteurs = capteurs;
		this.sens = sens;
	}
	
	public Semaphore(SegmentDeRoute segment,Capteur capteur,boolean sens) {
		this(segment,new ArrayList<Capteur>(),sens);
		this.add_Capteur(capteur);
	}
	
	public abstract int contrainteVitesseSemaphore(int vitesseActuelle);
	
	public void set_sonSegment(SegmentDeRoute seg) {
		sonSegment = seg;
	}
	
	public void set_sesCapteurs(ArrayList<? extends Capteur> cl) {
		sesCapteurs=new ArrayList<Capteur>(cl);
	}
	
	public void add_Capteur(Capteur c) {
		sesCapteurs.add(c);
	}
	
	public SegmentDeRoute get_sonSegment() {
		return sonSegment;
	}
	
	public ArrayList<Capteur> get_sesCapteurs() {
		return sesCapteurs;
	}
	
	public boolean get_sonSens() {
		return sens;
	}
}
