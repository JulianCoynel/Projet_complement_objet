package regulations;

import semaphores.Semaphore;

/**
 * Interface fonctionnelle permetant de determiner la methode a appliquer sur un semaphore une fois l'element de regulation declanche
 * @author Theo DELORME
 */
@FunctionalInterface
interface ActionRegulation {
	/** Methode a apliquer sur un semaphore une fois l'element de regulation declanche */
	void appliquerA (Semaphore semaphore);
}
