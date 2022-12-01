package Semaphores;

import java.util.ArrayList;

import Capteurs.Capteur;
import Segments_de_route.SegmentDeRoute;

/**
 * La classe abstraite permettant de definir les semaphores
 * @author Romain BAUDOUIN
 *
 */
public abstract class Semaphore {
	/** Le segment auquel appartient le semaphore */
	private SegmentDeRoute sonSegment;
	/** La liste de capteurs relies au semaphore */
	private ArrayList<Capteur> sesCapteurs;
	/** Le sens de circulation auquel appartient le semaphore */
	private boolean sens;
	
	/**
	 * Constructeur d'un semaphore quelconque
	 * @param segment Son segment de route
	 * @param capteurs Les capteurs qui lui sont relies
	 * @param sens Le sens de circulation auquel il appartient
	 */
	public Semaphore(SegmentDeRoute segment,ArrayList<Capteur> capteurs,boolean sens) {
		sonSegment = segment;
		sesCapteurs = capteurs;
		this.sens = sens;
	}
	
	/**
	 * Constructeur d'un semaphore quelconque avec un seul capteur
	 * @param segment Son segment de route
	 * @param capteur Un capteur que l'on souhaite relier au semaphore a sa creation
	 * @param sens Le sens de circulation auquel il appartient
	 */
	public Semaphore(SegmentDeRoute segment,Capteur capteur,boolean sens) {
		this(segment,new ArrayList<Capteur>(),sens);
		this.add_Capteur(capteur);
	}
	
	/**
     * Cette fonction permet de calculer la vitesse a laquelle le vehicule doit se mettre lorsqu'il rencontre le semaphore
     * @param vitesseActuelle la vitesse actuelle du vehicule
     * @return la nouvelle vitesse qui doit etre prise
     */
	public abstract int contrainteVitesseSemaphore(int vitesseActuelle);
	
	/**
	 * Permet de changer le segment de route d'un semaphore
	 * @param seg Son segment de route
	 */
	public void set_sonSegment(SegmentDeRoute seg) {
		sonSegment = seg;
	}
	
	/**
	 * Permet de changer les capteurs d'un s�maphore
	 * @param cl Une liste de capteurs relies au semaphore
	 */
	public void set_sesCapteurs(ArrayList<? extends Capteur> cl) {
		sesCapteurs=new ArrayList<Capteur>(cl);
	}
	
	/**
	 * Permet d'ajouter un capteur a la liste de capteurs relies au semaphore
	 * @param c Le capteur a ajouter
	 */
	public void add_Capteur(Capteur c) {
		sesCapteurs.add(c);
	}
	
	/**
	 * Permet de recuperer son segment de route
	 * @return Son segment de route
	 */
	public SegmentDeRoute get_sonSegment() {
		return sonSegment;
	}
	
	/**
	 * Permet de recuperer son ArrayList de capteurs
	 * @return sa liste de capteurs
	 */
	public ArrayList<Capteur> get_sesCapteurs() {
		return sesCapteurs;
	}
	
	/**
	 * Permet de recuperer le sens de circulation du semaphore
	 * @return son sens de circulation
	 */
	public boolean get_sonSens() {
		return sens;
	}
}
