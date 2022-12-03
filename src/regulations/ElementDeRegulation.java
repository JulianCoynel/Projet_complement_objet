package regulations;

import java.util.ArrayList;
import java.util.function.Function;

import capteurs.Capteur;
import semaphores.Semaphore;

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

	@Override
	public ArrayList<Semaphore> apply(Capteur c) {
		String nElem=c.getSonElement().getNom();
		switch (nElem) {
			case "id":
				return this.id(c.getSesSemaphores());
			default :
				return this.id(c.getSesSemaphores());
			
		}
	}
	
	

}
