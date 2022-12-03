package regulations;

import java.util.ArrayList;
import java.util.function.Function;

import capteurs.Capteur;
import semaphores.*;
import semaphores.FeuBicolore.Bicolore;
import semaphores.FeuTricolore.Tricolore;

public class ElementDeRegulation implements Function<Capteur,ArrayList<Semaphore>>{
	
	private String nom;
	
	ElementDeRegulation(){
		nom="id";
	}
	
	ElementDeRegulation(String nomElem){
		this();
		nom=nomElem;
	}
	
	public String getNom() {
		return nom;
	}
	
	private ArrayList<Semaphore> id(ArrayList<Semaphore> s) {
		return s;
	}
	
	private ArrayList<Semaphore> toutRouge(ArrayList<Semaphore> semaphores) {
		for (Semaphore s : semaphores) {
			s.passerAuRouge();
		}
		return semaphores;
	}
	
	private ArrayList<Semaphore> toutOrange(ArrayList<Semaphore> semaphores) {
		for (Semaphore s : semaphores) {
			s.passerAuOrange();
		}
		return semaphores;
	}

	@Override
	public ArrayList<Semaphore> apply(Capteur c) {
		String nElem=c.getSonElement().getNom();
		switch (nElem) {
			case "id":
				return this.id(c.getSesSemaphores());
				
			case "toutRouge":
				return this.toutRouge(c.getSesSemaphores());
				
			case "toutOrange":
				return this.toutOrange(c.getSesSemaphores());
				
			default :
				return this.id(c.getSesSemaphores());
			
		}
	}
	
	

}
