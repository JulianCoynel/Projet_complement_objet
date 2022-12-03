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
			if (s.estFeu()) {
				if ( s instanceof FeuBicolore) {
					FeuBicolore fb= (FeuBicolore) s;
					fb.setCouleur(Bicolore.ROUGE);
				}
				else {
					FeuTricolore ft = (FeuTricolore) s;
					ft.setCouleur(Tricolore.ROUGE);
				}
			}
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
				
			default :
				return this.id(c.getSesSemaphores());
			
		}
	}
	
	

}
