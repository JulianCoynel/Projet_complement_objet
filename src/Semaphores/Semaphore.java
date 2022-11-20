package Semaphores;

import java.util.ArrayList;

import Capteurs.Capteur;
import Segments_de_route.SegmentDeRoute;

/**
 * La classe abstraite permettant de d�finir les s�maphores
 * @author Romain BAUDOUIN
 *
 */
public abstract class Semaphore {
	/**
     * Le segment auquel appartient le s�maphore
     */
	private SegmentDeRoute sonSegment;
	/**
     * La liste de capteurs reli�s au s�maphore
     */
	private ArrayList<Capteur> sesCapteurs;
	/**
     * Le sens de circulation auquel appartient le s�maphore
     */
	private boolean sens;
	
	/**
	 * Constructeur d'un s�maphore quelconque
	 * @param segment Son segment de route
	 * @param capteurs Les capteurs qui lui sont reli�s
	 * @param sens Le sens de circulation auquel il appartient
	 */
	public Semaphore(SegmentDeRoute segment,ArrayList<Capteur> capteurs,boolean sens) {
		sonSegment = segment;
		sesCapteurs = capteurs;
		this.sens = sens;
	}
	
	/**
	 * Constructeur d'un s�maphore quelconque avec un seul capteur
	 * @param segment Son segment de route
	 * @param capteur Un capteur que l'on souhaite relier au s�maphore � sa cr�ation
	 * @param sens Le sens de circulation auquel il appartient
	 */
	public Semaphore(SegmentDeRoute segment,Capteur capteur,boolean sens) {
		this(segment,new ArrayList<Capteur>(),sens);
		this.add_Capteur(capteur);
	}
	
	/**
     * Cette fonction permet de calculer la vitesse � laquelle le v�hicule doit se mettre lorsqu'il rencontre le s�maphore
     * @param vitesseActuelle la vitesse actuelle du v�hicule
     * @return la nouvelle vitesse qui doit �tre prise
     */
	public abstract int contrainteVitesseSemaphore(int vitesseActuelle);
	
	/**
	 * Permet de changer le segment de route d'un s�maphore
	 * @param seg Son segment de route
	 */
	public void set_sonSegment(SegmentDeRoute seg) {
		sonSegment = seg;
	}
	
	/**
	 * Permet de changer les capteurs d'un s�maphore
	 * @param cl Une liste de capteurs reli�s au s�maphore
	 */
	public void set_sesCapteurs(ArrayList<? extends Capteur> cl) {
		sesCapteurs=new ArrayList<Capteur>(cl);
	}
	
	/**
	 * Permet d'ajouter un capteur � la liste de capteurs reli�s au s�maphore
	 * @param c Le capteur � ajouter
	 */
	public void add_Capteur(Capteur c) {
		sesCapteurs.add(c);
	}
	
	/**
	 * Permet de r�cup�rer son segment de route
	 * @return Son segment de route
	 */
	public SegmentDeRoute get_sonSegment() {
		return sonSegment;
	}
	
	/**
	 * Permet de r�cup�rer son ArrayList de capteurs
	 * @return sa liste de capteurs
	 */
	public ArrayList<Capteur> get_sesCapteurs() {
		return sesCapteurs;
	}
	
	/**
	 * Permet de r�cup�rer le sens de circulation du s�maphore
	 * @return son sens de circulation
	 */
	public boolean get_sonSens() {
		return sens;
	}
}
