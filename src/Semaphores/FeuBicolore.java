package Semaphores;

import java.util.ArrayList;

import Capteurs.Capteur;
import Segments_de_route.SegmentDeRoute;

/**
 * La classe d�finissant un s�maphore de type feu bicolore
 * @author Romain BAUDOUIN
 *
 */
public class FeuBicolore extends Semaphore {
	
	/**
	 * Classe �num�r�e d�finissant les deux couleurs possibles d'un feu bicolore
	 * @author Romain BAUDOUIN
	 *
	 */
	enum Bicolore {ROUGE, VERT}
	
	/**
	 * La couleur du feu
	 */
	private Bicolore couleur;

	/**
	 * Constructeur d'un feu bicolore, on initialise le feu � VERT
	 * @param segment Son segment de route
	 * @param capteurs Les capteurs qui lui sont reli�s
	 * @param sens Le sens de circulation auquel il appartient
	 */
	public FeuBicolore(SegmentDeRoute segment, ArrayList<Capteur> capteurs, boolean sens) {
		super(segment, capteurs, sens);
		this.couleur = Bicolore.VERT;
	}
	
	/**
	 * Constructeur d'un feu bicolore avec un seul capteur, on initialise le feu � VERT
	 * @param segment Son segment de route
	 * @param capteur Un capteur que l'on souhaite relier au s�maphore � sa cr�ation
	 * @param sens Le sens de circulation auquel il appartient
	 */
	public FeuBicolore(SegmentDeRoute segment,Capteur capteur,boolean sens) {
		super(segment, capteur, sens);
		this.couleur = Bicolore.VERT;
	}
	
	/**
     * Cette fonction permet de calculer la vitesse � laquelle le v�hicule doit se mettre lorsqu'il rencontre le s�maphore
     * Si le feu est vert on renvoie vitesseActuelle et sinon 0
     * @param vitesseActuelle la vitesse actuelle du v�hicule
     * @return la nouvelle vitesse qui doit �tre prise
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
	 * Permet de r�cup�rer la couleur d'un feu
	 * @return la couleur actuelle du feu
	 */
	public Bicolore getCouleur() {
		return couleur;
	}

}
