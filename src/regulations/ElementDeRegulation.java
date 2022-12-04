package regulations;

import java.util.Set;
import semaphores.Semaphore;

/**
 * La classe definissant un Element de Regulation
 * @author Theo DELORME
 */
public class ElementDeRegulation {
	/** Implementation d'une interface fonctionnelle permetant de determiner la methode a appliquer sur un semaphore une fois l'element de regulation declanche **/
	private ActionRegulation sonAction;
	/** L'ensemble des Semaphore qui doivent effectuer l'action en cas d'actionnement */
	private Set<Semaphore> sesSemaphores;
	
	/**
	 * Constructeur d'un element de regulation
	 * @param action Implementation d'une interface fonctionnelle permetant de determiner la methode a appliquer sur un semaphore une fois l'element de regulation declanche
	 * @param sesSemaphores L'ensemble des Semaphore qui doivent effectuer l'action en cas d'actionnement
	 */
	public ElementDeRegulation(ActionRegulation action, Set<Semaphore> sesSemaphores){
		sonAction = action;
	}
	
	/**
	 * Applique l'action a chacun des Semaphore de cette element de regulation
	 */
	public void actionner () {
		for (Semaphore s : sesSemaphores) {
			sonAction.appliquerA(s);
		}
	}
}
