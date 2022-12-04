package capteurs;

import java.util.HashSet;
import java.util.Set;

import exception.ErreurResultatCapteurImpossible;
import regulations.ElementDeRegulation;
import routes.Route;
import semaphores.Semaphore;
import vehicules.Vehicule;

public class CapteurPresence extends Capteur{

	public CapteurPresence(Route segment, Set<Semaphore> semaphores, ElementDeRegulation element, boolean sens) {
		super(segment, semaphores, element, sens);
	}
	
	public CapteurPresence(Route segment, Set<Semaphore> semaphores, ElementDeRegulation element, boolean sens,int position) {
		super(segment, semaphores, element, sens,position);
	}

	@Override
	public Set<ResultatCapteur> getResultatCapteur(Route r) {
		try {
			Set<Vehicule> V=resultatEstPossible(r);
			super.actionnerElement();
			Set<ResultatCapteur> RC=new HashSet<ResultatCapteur>();
			for(Vehicule v: V) {
				RC.add(new ResultatCapteur(v.getID()));
			}
			return RC;
		} catch(ErreurResultatCapteurImpossible e) {
			Set<ResultatCapteur> RC=new HashSet<ResultatCapteur>();
			RC.add(new ResultatCapteur());
			return RC;
		}
	}
}