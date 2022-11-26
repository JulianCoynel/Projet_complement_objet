package Capteurs;

import java.util.ArrayList;

import Elements_de_regulations.ElementDeRegulation;
import Segments_de_route.SegmentDeRoute;
import Semaphores.Semaphore;

public class Vitesse extends Capteur {

	public Vitesse(SegmentDeRoute segment, ArrayList<Semaphore> semaphores, ElementDeRegulation element,boolean sens) {
		super(segment, semaphores, element,sens);
	}

	@Override
	public boolean est_actif() {
		// TODO Auto-generated method stub
		return false;
	}
	

}
