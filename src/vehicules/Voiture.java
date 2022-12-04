package vehicules;

import routes.Route;

/**
 * La classe definissant un type de Vehicule de longueur 1 : la voiture
 * @author Theo DELORME
 */
public class Voiture extends Vehicule {
	
	/**
	 * Constructeur d'une voiture
	 * @param v La vitesse maximale que peu attendre ce vehicule en dehors de toute restriction : en nombre de troncon routier par unite de temps
	 * @param r La Route ou est localise ce vehicule actuellement
	 * @param s Le sens de circulation qu'emprunte actuelement le vehicule sur la route r
	 */
	public Voiture (int v, Route r, boolean s) {
		super(1,v,r,s);
	}
}