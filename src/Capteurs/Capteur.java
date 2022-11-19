package Capteurs;

import java.util.ArrayList;

import Elements_de_regulations.ElementDeRegulation;
import Segments_de_route.*;
import Semaphores.*;

public abstract class Capteur {
	private SegmentDeRoute sonSegment;
	private ArrayList<Semaphore> sesSemaphores;
	private ElementDeRegulation sonElement;
	
	public void set_sonSegment(SegmentDeRoute s) {
		sonSegment=s;
	}
	
	public void set_sesSemaphores(ArrayList<? extends Semaphore> sl) {
		sesSemaphores=new ArrayList<Semaphore>(sl);
	}
	
	public void add_Semaphore(Semaphore s) {
		sesSemaphores.add(s);
	}
	
	public void set_sonElement(ElementDeRegulation e) {
		sonElement=e;
	}
	
	public SegmentDeRoute get_sonSegment() {
		return sonSegment;
	}
	
	public ArrayList<Semaphore> get_sesSemaphores() {
		return sesSemaphores;
	}
	
	public ElementDeRegulation get_sonElement() {
		return sonElement;
	}
}
