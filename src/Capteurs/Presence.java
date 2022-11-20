package Capteurs;

import java.util.ArrayList;

import Elements_de_regulations.ElementDeRegulation;
import Segments_de_route.SegmentDeRoute;
import Semaphores.Semaphore;

public class Presence extends Capteur{

	public Presence(SegmentDeRoute segment, ArrayList<Semaphore> semaphores, ElementDeRegulation element) {
		super(segment, semaphores, element);
	}

}