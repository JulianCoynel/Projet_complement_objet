package semaphores;

import java.util.ArrayList;

import capteurs.Capteur;
import routes.Route;

/**
 * La classe definissant un semaphore de type feu bicolore
 * @author Romain BAUDOUIN
 *
 */
public class FeuBicolore extends Semaphore {
	
	/**
	 * Classe enumeree definissant les deux couleurs possibles d'un feu bicolore
	 * @author Romain BAUDOUIN
	 */
	enum Bicolore {ROUGE, VERT}
	
	/** La couleur du feu */
	private Bicolore couleur;

	/**
	 * Constructeur d'un feu bicolore, on initialise le feu a VERT
	 * @param segment Son segment de route
	 * @param capteurs Les capteurs qui lui sont relies
	 * @param sens Le sens de circulation auquel il appartient
	 */
	public FeuBicolore(Route segment, ArrayList<Capteur> capteurs, boolean sens) {
		super(segment, capteurs, sens);
		this.couleur = Bicolore.VERT;
	}
	
	/**
	 * Constructeur d'un feu bicolore avec un seul capteur, on initialise le feu a VERT
	 * @param segment Son segment de route
	 * @param capteur Un capteur que l'on souhaite relier au semaphore a sa creation
	 * @param sens Le sens de circulation auquel il appartient
	 */
	public FeuBicolore(Route segment,Capteur capteur,boolean sens) {
		super(segment, capteur, sens);
		this.couleur = Bicolore.VERT;
	}
	
	@Override
	/**
     * Cette fonction permet de calculer la vitesse a laquelle le vehicule doit se mettre lorsqu'il rencontre le semaphore
     * Si le feu est vert on renvoie vitesseActuelle et sinon 0
     * @param vitesseActuelle la vitesse actuelle du vehicule
     * @return la nouvelle vitesse qui doit etre prise
     */
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
	
	/**
	 * Permet de changer la couleur d'un feu bicolore parmi ROUGE et VERT
	 * @param b la nouvelle couleur du feu
	 */
	public void setCouleur(Bicolore b) {
		couleur = b;
	}
	
	/**
	 * Permet de recuperer la couleur d'un feu
	 * @return la couleur actuelle du feu
	 */
	public Bicolore getCouleur() {
		return couleur;
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
	public int getDivisionVitesse() {
		return 1;
	}

	@Override
	public int getLimitationVitesse() {
		return -1;
	}

	@Override
	public boolean estFeu() {
		return true;
	}

}
