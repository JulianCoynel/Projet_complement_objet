package semaphores;

import java.util.ArrayList;

import capteurs.Capteur;
import routes.Route;

/**
 * La classe definissant un semaphore de type panneau de limitation de vitesse
 * @author Romain BAUDOUIN
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
	
	public PanneauLimitationVitesse(Route segment,boolean sens,int v) {
		super(segment, sens);
		this.vitesseMax = v;
	}
	
	/**
	 * Permet de changer la vitesse max indiquee par le panneau
	 * @param v la nouvelle vitesse max indiquee par le panneau
	 */
	public void setVitesseMax(int v) {
		vitesseMax = v;
	}
	
	@Override
	public boolean estRouge() {
		return false;
	}
	
	@Override
	public boolean estFeu() {
		return false;
	}

	@Override
	public void passerAuRouge() {
		return;
	}

	@Override
	public void passerAuOrange() {
		return;
	}

	@Override
	public void passerAuVert() {
		return;
	}

	@Override
	public int getDivisionVitesse() {
		return 1;
	}

	@Override
	public int getLimitationVitesse() {
		return vitesseMax;
	}
}
