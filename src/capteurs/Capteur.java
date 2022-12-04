package capteurs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import exception.ErreurResultatCapteurImpossible;
import regulations.ElementDeRegulation;
import routes.*;
import semaphores.*;
import vehicules.Vehicule;

public abstract class Capteur {
	private Route sonSegment;
	private ArrayList<Semaphore> sesSemaphores;
	private ElementDeRegulation sonElement;
	private boolean sonSens;
	private int saPosition;
	
	
	public Capteur(Route segment,Set<Semaphore> semaphores,ElementDeRegulation element,boolean sens,int position) {
		sonSegment=segment;
		setSesSemaphores(semaphores);
		sonElement=element;
		sonSens=sens;
		segment.addCapteur(this);
		for(Semaphore s : semaphores) {
			s.addCapteur(this);
		}
		if(0<=position && position<=segment.getLongueur()) {
			saPosition=position;
		}
		else {
			throw new IllegalArgumentException("Capteur pas sur la route");
		}
	}
	
	/*On met de base le capteur au milieu de la route*/
	public Capteur(Route segment,Set<Semaphore> semaphores,ElementDeRegulation element,boolean sens) {
		this(segment,semaphores,element,sens,segment.getLongueur()/2);
	}
	
	public Capteur(Route segment,Semaphore semaphore,ElementDeRegulation element,boolean sens,int position) {
		this(segment,new HashSet<Semaphore>(),element,sens,position);
		addSemaphore(semaphore);
	}
	
	/*On met de base le capteur au milieu de la route*/
	public Capteur(Route segment,Semaphore semaphore,ElementDeRegulation element,boolean sens) {
		this(segment,semaphore,element,sens,segment.getLongueur()/2);
	}
	
	Set<Vehicule> resultatEstPossible(Route r) throws ErreurResultatCapteurImpossible {
		if(r.equals(sonSegment)) {
			return r.getVehicules(sonSens, saPosition);
		}
		throw new ErreurResultatCapteurImpossible("mauvais segment");
	}
	
	public void actionnerElement() {
		sonElement.actionner();
	}
	
	public void addSemaphore(Semaphore s) {
		sesSemaphores.add(s);
	}
	
	public abstract Set<ResultatCapteur> getResultatCapteur(Route r);
	
	@Override
	public String toString() {
		Set<ResultatCapteur> res=getResultatCapteur(sonSegment);
		return res.toString();
	}
	
	public void setSonSegment(Route s) {
		sonSegment=s;
	}
	
	public void setSesSemaphores(Set<? extends Semaphore> sl) {
		sesSemaphores=new ArrayList<Semaphore>(sl);
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