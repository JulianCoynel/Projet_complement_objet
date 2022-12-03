package regulations;

import semaphores.Semaphore;

@FunctionalInterface
public interface ActionRegulation {
	void appliquerA (Semaphore semaphore);
}
