package Semaphores;

import java.util.ArrayList;

import Capteurs.Capteur;
import Segments_de_route.SegmentDeRoute;

/**
 * La classe d�finissant un s�maphore de type panneau de limitation de vitesse
 * @author Romain BAUDOUIN
 *
 */
public class PanneauLimitationVitesse extends Semaphore {
	
	/**
	 * La vitesse maximum indiqu�e par le panneau
	 */
	private int vitesseMax;

	/**
	 * Constructeur d'un panneau de limitation de vitesse
	 * @param segment Son segment de route
	 * @param capteurs Les capteurs qui lui sont reli�s
	 * @param sens Le sens de circulation auquel il appartient
	 * @param v La vitesse max indiqu�e par le panneau
	 */
	public PanneauLimitationVitesse(SegmentDeRoute segment, ArrayList<Capteur> capteurs, boolean sens,int v) {
		super(segment, capteurs, sens);
		this.vitesseMax = v;
	}
	
	/**
	 * Constructeur d'un panneau de limitation de vitesse, avec un seul capteur
	 * @param segment Son segment de route
	 * @param capteurs Les capteurs qui lui sont reli�s
	 * @param sens Le sens de circulation auquel il appartient
	 * @param v La vitesse max indiqu�e par le panneau
	 */
	public PanneauLimitationVitesse(SegmentDeRoute segment,Capteur capteur,boolean sens,int v) {
		super(segment, capteur, sens);
		this.vitesseMax = v;
	}
	
	/**
	 * @param vitesseActuelle la vitesse actuelle du v�hicule
	 * @return La vitesse max indiqu�e par le panneau
	 */
	public int contrainteVitesseSemaphore(int vitesseActuelle) {
		return vitesseMax;
	}
	
	/**
	 * Permet de changer la vitesse max indiqu�e par le panneau
	 * @param v la nouvelle vitesse max indiqu�e par le panneau
	 */
	public void setVitesseMax(int v) {
		vitesseMax = v;
	}
	
	/**
	 * Permet de r�cup�rer La vitesse max indiqu�e par le panneau
	 * @return La vitesse max indiqu�e par le panneau
	 */
	public int getVitesseMax() {
		return vitesseMax;
	}

}
