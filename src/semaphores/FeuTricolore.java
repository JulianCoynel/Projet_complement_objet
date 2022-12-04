package semaphores;

import java.util.ArrayList;

import capteurs.Capteur;
import routes.Route;

/**
 * La classe definissant un semaphore de type feu tricolore
 * @author Romain BAUDOUIN
 */
public class FeuTricolore extends Semaphore {
	/**
	 * Classe enumeree definissant les trois couleurs possibles d'un feu tricolore
	 * @author Romain BAUDOUIN
	 *
	 */
	public enum Tricolore {ROUGE, ORANGE, VERT}
	
	/** La couleur du feu */
	private Tricolore couleur;

	/**
	 * Constructeur d'un feu tricolore, on initialise le feu a VERT
	 * @param segment Son segment de route
	 * @param capteurs Les capteurs qui lui sont relies
	 * @param sens Le sens de circulation auquel il appartient
	 */
	public FeuTricolore(Route segment, ArrayList<Capteur> capteurs, boolean sens) {
		super(segment, capteurs, sens);
		this.couleur = Tricolore.VERT;
	}
	
	/**
	 * Constructeur d'un feu tricolore avec un seul capteur, on initialise le feu a VERT
	 * @param segment Son segment de route
	 * @param capteur Un capteur que l'on souhaite relier au semaphore a sa creation
	 * @param sens Le sens de circulation auquel il appartient
	 */
	public FeuTricolore(Route segment,Capteur capteur,boolean sens) {
		super(segment, capteur, sens);
		this.couleur = Tricolore.VERT;
	}
	
	@Override
	public boolean estRouge() {
		switch (this.couleur) {
		case ROUGE:
			return true;
		default:
			return false;
		}
	}
	
	@Override
	public boolean estFeu() {
		return true;
	}

	@Override
	public void passerAuRouge() {
		this.couleur = Tricolore.ROUGE;
	}

	@Override
	public void passerAuOrange() {
		this.couleur = Tricolore.ORANGE;
	}

	@Override
	public void passerAuVert() {
		this.couleur = Tricolore.VERT;
	}
	
	/**
	 * Permet de recuperer la couleur d'un feu
	 * @return la couleur actuelle du feu
	 */
	public Tricolore getCouleur() {
		return couleur;
	}

	@Override
	public int getDivisionVitesse() {
		switch (this.couleur) {
		case ORANGE:
			return 2;
		default:
			return 1;
		}
	}

	@Override
	public int getLimitationVitesse() {
		return -1;
	}

}
