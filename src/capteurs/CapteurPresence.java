package capteurs;

import java.util.ArrayList;

import regulations.ElementDeRegulation;
import routes.Route;
import semaphores.Semaphore;

public class CapteurPresence extends Capteur{

	public CapteurPresence(Route segment, ArrayList<Semaphore> semaphores, ElementDeRegulation element, boolean sens) {
		super(segment, semaphores, element, sens);
	}

	@Override
	public boolean estActif() {
		// TODO Auto-generated method stub
		return false;
	}

}
