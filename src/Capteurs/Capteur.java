package Capteurs;

import java.util.ArrayList;

import Elements_de_regulations.ElementDeRegulation;
import Segments_de_route.*;
import Semaphores.*;

public abstract class Capteur {
	private SegmentDeRoute sonSegment;
	private ArrayList<Semaphore> sesSemaphores;
	private ElementDeRegulation sonElement;
	private boolean sonSens;
	
	
	public Capteur(SegmentDeRoute segment,ArrayList<Semaphore> semaphores,ElementDeRegulation element,boolean sens) {
		sonSegment=segment;
		set_sesSemaphores(semaphores);
		sonElement=element;
		sonSens=sens;
	}
	
	public Capteur(SegmentDeRoute segment,Semaphore semaphore,ElementDeRegulation element,boolean sens) {
		this(segment,new ArrayList<Semaphore>(),element,sens);
		add_Semaphore(semaphore);
	}
	
	public abstract boolean est_actif();
	
	
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
	
	public void set_sonSens(boolean sens) {
		sonSens =sens;
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
	
	public boolean get_sonSens() {
		return sonSens;
	}

}
