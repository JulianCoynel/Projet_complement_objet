package semaphores;

import java.util.ArrayList;

import capteurs.Capteur;
import routes.Route;

/**
 * La classe definissant un semaphore de type panneau de limitation de vitesse
 * @author Romain BAUDOUIN
 *
 */
public class PanneauLimitationVitesse extends Semaphore {
	
	/** La vitesse maximum indiquee par le panneau */
	private int vitesseMax;

	/**
	 * Constructeur d'un panneau de limitation de vitesse
	 * @param segment Son segment de route
	 * @param capteurs Les capteurs qui lui sont relies
	 * @param sens Le sens de circulation auquel il appartient
	 * @param v La vitesse max indiquee par le panneau
	 */
	public PanneauLimitationVitesse(Route segment, ArrayList<Capteur> capteurs, boolean sens,int v) {
		super(segment, capteurs, sens);
		this.vitesseMax = v;
	}
	
	/**
	 * Constructeur d'un panneau de limitation de vitesse, avec un seul capteur
	 * @param segment Son segment de route
	 * @param capteurs Les capteurs qui lui sont relies
	 * @param sens Le sens de circulation auquel il appartient
	 * @param v La vitesse max indiquee par le panneau
	 */
	public PanneauLimitationVitesse(Route segment,Capteur capteur,boolean sens,int v) {
		super(segment, capteur, sens);
		this.vitesseMax = v;
	}
	
	/**
	 * @param vitesseActuelle la vitesse actuelle du vehicule
	 * @return La vitesse max indiquee par le panneau
	 */
	public int contrainteVitesseSemaphore(int vitesseActuelle) {
		return vitesseMax;
	}
	
	/**
	 * Permet de changer la vitesse max indiquee par le panneau
	 * @param v la nouvelle vitesse max indiquee par le panneau
	 */
	public void setVitesseMax(int v) {
		vitesseMax = v;
	}
	
	/**
	 * Permet de recuperer La vitesse max indiquee par le panneau
	 * @return La vitesse max indiquee par le panneau
	 */
	public int getVitesseMax() {
		return vitesseMax;
	}

}
