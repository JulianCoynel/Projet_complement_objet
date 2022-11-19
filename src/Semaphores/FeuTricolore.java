package Semaphores;

import java.util.ArrayList;

import Capteurs.Capteur;
import Segments_de_route.SegmentDeRoute;

/**
 * La classe définissant un sémaphore de type feu tricolore
 * @author Romain BAUDOUIN
 *
 */
public class FeuTricolore extends Semaphore {
	/**
	 * Classe énumérée définissant les trois couleurs possibles d'un feu tricolore
	 * @author Romain BAUDOUIN
	 *
	 */
	enum Tricolore {ROUGE, ORANGE, VERT}
	
	/**
	 * La couleur du feu
	 */
	private Tricolore couleur;

	/**
	 * Constructeur d'un feu tricolore, on initialise le feu à VERT
	 * @param segment Son segment de route
	 * @param capteurs Les capteurs qui lui sont reliés
	 * @param sens Le sens de circulation auquel il appartient
	 */
	public FeuTricolore(SegmentDeRoute segment, ArrayList<Capteur> capteurs, boolean sens) {
		super(segment, capteurs, sens);
		this.couleur = Tricolore.VERT;
	}
	
	/**
	 * Constructeur d'un feu tricolore avec un seul capteur, on initialise le feu à VERT
	 * @param segment Son segment de route
	 * @param capteur Un capteur que l'on souhaite relier au sémaphore à sa création
	 * @param sens Le sens de circulation auquel il appartient
	 */
	public FeuTricolore(SegmentDeRoute segment,Capteur capteur,boolean sens) {
		super(segment, capteur, sens);
		this.couleur = Tricolore.VERT;
	}
	
	/**
     * Cette fonction permet de calculer la vitesse à laquelle le véhicule doit se mettre lorsqu'il rencontre le sémaphore
     * Si le feu est vert on renvoie vitesseActuelle, sinon s'il est orange vitesseActuelle/2 et sinon 0
     * @param vitesseActuelle la vitesse actuelle du véhicule
     * @return la nouvelle vitesse qui doit être prise
     */
	public int contrainteVitesseSemaphore(int vitesseActuelle) {
		int res = 0;
		switch (this.couleur) {
			case VERT:
				res = vitesseActuelle;
			case ORANGE:
				res = vitesseActuelle/2;
			case ROUGE:
				res = 0;
		}
		return res;
	}
	
	/**
	 * Permet de changer la couleur d'un feu tricolore parmi ROUGE, ORANGE et VERT
	 * @param c la nouvelle couleur du feu
	 */
	public void setCouleur(Tricolore c) {
		couleur = c;
	}
	
	/**
	 * Permet de récupérer la couleur d'un feu
	 * @return la couleur actuelle du feu
	 */
	public Tricolore getCouleur() {
		return couleur;
	}

}
