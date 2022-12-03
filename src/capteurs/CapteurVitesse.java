package capteurs;

import java.util.ArrayList;

import regulations.ElementDeRegulation;
import routes.Route;
import semaphores.Semaphore;

public class CapteurVitesse extends Capteur {

	public CapteurVitesse(Route segment, ArrayList<Semaphore> semaphores, ElementDeRegulation element,boolean sens) {
		super(segment, semaphores, element, sens);
	}

	@Override
	public ResultatCapteur getResultatCapteur(Route r) {
		if (resultatEstPossible(r)) {
			return new ResultatCapteur();
		}
		return null;
	}

	
	

}
