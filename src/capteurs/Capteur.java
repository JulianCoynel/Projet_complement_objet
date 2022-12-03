package capteurs;

import java.util.ArrayList;

import regulations.ElementDeRegulation;
import routes.*;
import semaphores.*;

public abstract class Capteur {
	private Route sonSegment;
	private ArrayList<Semaphore> sesSemaphores;
	private ElementDeRegulation sonElement;
	private boolean sonSens;
	
	
	public Capteur(Route segment,ArrayList<Semaphore> semaphores,ElementDeRegulation element,boolean sens) {
		sonSegment=segment;
		setSesSemaphores(semaphores);
		sonElement=element;
		sonSens=sens;
	}
	
	public Capteur(Route segment,Semaphore semaphore,ElementDeRegulation element,boolean sens) {
		this(segment,new ArrayList<Semaphore>(),element,sens);
		addSemaphore(semaphore);
	}
	
	public abstract boolean estActif();
	
	
	public void setSonSegment(Route s) {
		sonSegment=s;
	}
	
	public void setSesSemaphores(ArrayList<? extends Semaphore> sl) {
		sesSemaphores=new ArrayList<Semaphore>(sl);
	}
	
	public void addSemaphore(Semaphore s) {
		sesSemaphores.add(s);
	}
	
	public void setSonElement(ElementDeRegulation e) {
		sonElement=e;
	}
	
	public void setSonSens(boolean sens) {
		sonSens = sens;
	}
	
	public Route getSonSegment() {
		return sonSegment;
	}
	
	public ArrayList<Semaphore> getSesSemaphores() {
		return sesSemaphores;
	}
	
	public ElementDeRegulation getSonElement() {
		return sonElement;
	}
	
	public boolean getSonSens() {
		return sonSens;
	}

}
