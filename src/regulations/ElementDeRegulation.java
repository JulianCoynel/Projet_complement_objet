package regulations;

import java.util.Set;
import semaphores.Semaphore;

public class ElementDeRegulation {
	private ActionRegulation sonAction;
	private Set<Semaphore> sesSemaphores;
	
	public ElementDeRegulation(ActionRegulation action, Set<Semaphore> sesSemaphores){
		sonAction = action;
	}
	
	public void actionner () {
		for (Semaphore s : sesSemaphores) {
			sonAction.appliquerA(s);
		}
	}
}
