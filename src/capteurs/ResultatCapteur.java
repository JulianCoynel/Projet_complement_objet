package capteurs;

public class ResultatCapteur {
	
	private int idVoiture;
	private int vitesseVoiture;
	
	ResultatCapteur(){
		idVoiture=-1;
		vitesseVoiture=-1;
	}
	
	ResultatCapteur(int id){
		this();
		idVoiture=id;
	}

	ResultatCapteur(int id,int vitesse){
		this(id);
		vitesseVoiture=vitesse;
	}
	
	public int getIdVoiture() {
		return idVoiture;
	}
	
	public int getVitesseVoiture() {
		return vitesseVoiture;
	}
}
