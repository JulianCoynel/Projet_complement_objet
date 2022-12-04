package semaphores;

import java.util.ArrayList;

import capteurs.Capteur;
import routes.Route;

/**
 * La classe abstraite permettant de definir les semaphores
 * @author Romain BAUDOUIN
 */
public abstract class Semaphore {
	/** Le segment auquel appartient le semaphore */
	private Route sonSegment;
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
	public Semaphore(Route segment,ArrayList<Capteur> capteurs,boolean sens) {
		sonSegment = segment;
		sesCapteurs = capteurs;
		this.sens = sens;
		segment.addSemaphore(this);
	}
	
	/**
	 * Constructeur d'un semaphore sans capteurs
	 * @param segment Son segment de route
	 * @param sens Le sens de circulation auquel il appartient
	 */
	public Semaphore(Route segment,boolean sens) {
		this(segment,new ArrayList<Capteur>(),sens);
		segment.addSemaphore(this);
	}
	
	/**
	 * Permet d'ajouter un capteur a la liste de capteurs relies au semaphore
	 * @param c Le capteur a ajouter
	 */
	public void addCapteur(Capteur c) {
		sesCapteurs.add(c);
	}
	
	/**
	 * Permet de retirer un capteur a la liste de capteurs relies au semaphore
	 * @param c Le capteur a retirer
	 */
	public void removeCapteur(Capteur c) {
		sesCapteurs.remove(c);
	}
	
	/**
	 * Permet de recuperer son ArrayList de capteurs
	 * @return sa liste de capteurs
	 */
	public ArrayList<Capteur> getSesCapteurs() {
		return sesCapteurs;
	}

	/**
	 * Permet de savoir si un feu est rouge
	 * @return true si le feu est rouge, false sinon
	 */
	public abstract boolean estRouge();
	
	public abstract boolean estFeu();
	
	public abstract void passerAuRouge();
	
	public abstract void passerAuOrange();
	
	public abstract void passerAuVert();
	
	/**
	 * Permet de changer le segment de route d'un semaphore
	 * @param seg Son segment de route
	 */
	public void setSonSegment(Route seg) {
		sonSegment = seg;
	}
	
	/**
	 * Permet de recuperer son segment de route
	 * @return Son segment de route
	 */
	public Route getSonSegment() {
		return sonSegment;
	}
	
	/**
	 * Permet de changer les capteurs d'un semaphore
	 * @param cl Une liste de capteurs relies au semaphore
	 */
	public void setSesCapteurs(ArrayList<? extends Capteur> cl) {
		sesCapteurs=new ArrayList<Capteur>(cl);
	}
	
	/**
	 * Permet de recuperer le sens de circulation du semaphore
	 * @return son sens de circulation
	 */
	public boolean getSonSens() {
		return sens;
	}
	
	public abstract int getDivisionVitesse();
	
	public abstract int getLimitationVitesse();
}
