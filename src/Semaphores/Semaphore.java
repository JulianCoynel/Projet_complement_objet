package Semaphores;

import java.util.ArrayList;

import Capteurs.Capteur;
import Segments_de_route.SegmentDeRoute;

/**
 * La classe abstraite permettant de définir les sémaphores
 * @author Romain BAUDOUIN
 *
 */
public abstract class Semaphore {
	/**
     * Le segment auquel appartient le sémaphore
     */
	private SegmentDeRoute sonSegment;
	/**
     * La liste de capteurs reliés au sémaphore
     */
	private ArrayList<Capteur> sesCapteurs;
	/**
     * Le sens de circulation auquel appartient le sémaphore
     */
	private boolean sens;
	
	/**
	 * Constructeur d'un sémaphore quelconque
	 * @param segment Son segment de route
	 * @param capteurs Les capteurs qui lui sont reliés
	 * @param sens Le sens de circulation auquel il appartient
	 */
	public Semaphore(SegmentDeRoute segment,ArrayList<Capteur> capteurs,boolean sens) {
		sonSegment = segment;
		sesCapteurs = capteurs;
		this.sens = sens;
	}
	
	/**
	 * Constructeur d'un sémaphore quelconque avec un seul capteur
	 * @param segment Son segment de route
	 * @param capteur Un capteur que l'on souhaite relier au sémaphore à sa création
	 * @param sens Le sens de circulation auquel il appartient
	 */
	public Semaphore(SegmentDeRoute segment,Capteur capteur,boolean sens) {
		this(segment,new ArrayList<Capteur>(),sens);
		this.add_Capteur(capteur);
	}
	
	/**
     * Cette fonction permet de calculer la vitesse à laquelle le véhicule doit se mettre lorsqu'il rencontre le sémaphore
     * @param vitesseActuelle la vitesse actuelle du véhicule
     * @return la nouvelle vitesse qui doit être prise
     */
	public abstract int contrainteVitesseSemaphore(int vitesseActuelle);
	
	/**
	 * Permet de changer le segment de route d'un sémaphore
	 * @param seg Son segment de route
	 */
	public void set_sonSegment(SegmentDeRoute seg) {
		sonSegment = seg;
	}
	
	/**
	 * Permet de changer les capteurs d'un sémaphore
	 * @param cl Une liste de capteurs reliés au sémaphore
	 */
	public void set_sesCapteurs(ArrayList<? extends Capteur> cl) {
		sesCapteurs=new ArrayList<Capteur>(cl);
	}
	
	/**
	 * Permet d'ajouter un capteur à la liste de capteurs reliés au sémaphore
	 * @param c Le capteur à ajouter
	 */
	public void add_Capteur(Capteur c) {
		sesCapteurs.add(c);
	}
	
	/**
	 * Permet de récupérer son segment de route
	 * @return Son segment de route
	 */
	public SegmentDeRoute get_sonSegment() {
		return sonSegment;
	}
	
	/**
	 * Permet de récupérer son ArrayList de capteurs
	 * @return sa liste de capteurs
	 */
	public ArrayList<Capteur> get_sesCapteurs() {
		return sesCapteurs;
	}
	
	/**
	 * Permet de récupérer le sens de circulation du sémaphore
	 * @return son sens de circulation
	 */
	public boolean get_sonSens() {
		return sens;
	}
}
