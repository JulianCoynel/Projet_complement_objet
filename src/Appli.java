import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;

import exception.*;
import jonctions.*;
import capteurs.*;
import routes.Route;
import semaphores.*;
import vehicules.*;
import regulations.*;

public class Appli {
	static HashSet<Route> listeRoute;
	static HashSet<Semaphore> listeSemaphoreAbcisse;
	static HashSet<Semaphore> listeSemaphoreOrdonnee;
	static HashSet<Vehicule> listeVehicule;
	static int n = 0;
	
	public static void main(String[] args) throws ErreurConstruction {
		Route R1, R2, R3, R4, R5, R6, R7, R8, R9 ;
		FeuBicolore B1, B2, B3, B4 ;
		FeuTricolore T1, T2, T3, T4, T5, T6;
		PanneauLimitationVitesse P1, P2, P3, P4, P5, P6, P7, P8, P9;
		Barriere BA1, BA2, BA3, BA4, BA5, BA6;
		Carrefour C1, C2, C3;
		JonctionSimple J1;
		Voiture V1, V2, V3, V4, V5;
		ElementDeRegulation E1, E2, E3, E4;
		CapteurVitesse CV1, CV2;
		CapteurPresence CP1, CP2;
		
		// creer les routes :
		R1 = new Route(101);
		R2 = new Route(82);
		R3 = new Route(93);
		R4 = new Route(54);
		R5 = new Route(115);
		R6 = new Route(136);
		R7 = new Route(57);
		R8 = new Route(88);
		R9 = new Route(69);
		listeRoute = new HashSet<Route>();
		listeRoute.add(R1);
		listeRoute.add(R2);
		listeRoute.add(R3);
		listeRoute.add(R4);
		listeRoute.add(R5);
		listeRoute.add(R6);
		listeRoute.add(R7);
		listeRoute.add(R8);
		listeRoute.add(R9);
		
		// ajouter les semaphores
		B1 = new FeuBicolore(R1,false);
		B2 = new FeuBicolore(R2,true);
		B3 = new FeuBicolore(R3,true);
		B4 = new FeuBicolore(R6,true);
		T1 = new FeuTricolore(R4,true);
		T2 = new FeuTricolore(R4,false);
		T3 = new FeuTricolore(R5,true);
		T4 = new FeuTricolore(R5,false);
		T5 = new FeuTricolore(R8,true);
		T6 = new FeuTricolore(R9,false);
		P1 = new PanneauLimitationVitesse(R1,true,7);
		P2 = new PanneauLimitationVitesse(R2,false,9);
		P3 = new PanneauLimitationVitesse(R3,true,5);
		P4 = new PanneauLimitationVitesse(R4,false,10);
		P5 = new PanneauLimitationVitesse(R5,false,11);
		P6 = new PanneauLimitationVitesse(R6,false,11);
		P7 = new PanneauLimitationVitesse(R7,true,3);
		P8 = new PanneauLimitationVitesse(R8,false,4);
		P9 = new PanneauLimitationVitesse(R9,true,3);
		
		listeSemaphoreOrdonnee = new HashSet<Semaphore>(Arrays.asList(B1,B2,B3,T5,T6));
		for(Semaphore s : listeSemaphoreOrdonnee) {
			s.passerAuRouge();
		}
		listeSemaphoreAbcisse = new HashSet<Semaphore>(Arrays.asList(B4,T1,T2,T3,T4));
		for(Semaphore s : listeSemaphoreAbcisse) {
			s.passerAuVert();
		}
		
		// ajouter les jonctions
		HashSet<Route> S1 = new HashSet<Route>();
		S1.add(R5);
		S1.add(R8);
		S1.add(R9);
		C1 = new Carrefour(S1);
		HashSet<Route> S2 = new HashSet<Route>();
		S2.add(R5);
		S2.add(R2);
		S2.add(R4);
		C2 = new Carrefour(S2);
		HashSet<Route> S3 = new HashSet<Route>();
		S3.add(R1);
		S3.add(R3);
		S3.add(R4);
		S3.add(R6);
		C3 = new Carrefour(S3);
		J1 = new JonctionSimple(R6,R7);
		BA1 = new Barriere(R1);
		BA2 = new Barriere(R2);
		BA3 = new Barriere(R3);
		BA4 = new Barriere(R7);
		BA5 = new Barriere(R8);
		BA6 = new Barriere(R9);
		
		// ajouter les vehicules
		V1 = new Voiture(9,R1,false);
		V2 = new Voiture(13,R5,false);
		V3 = new Voiture(6,R7,true);
		V4 = new Voiture(10,R2,false);
		V5 = new Voiture(5,R4,true);
		listeVehicule = new HashSet<Vehicule>(Arrays.asList(V1,V2,V3,V4,V5));
		
		//Creation des elements de regulations
		E1 = new ElementDeRegulation((s) -> s.passerAuRouge() , listeSemaphoreOrdonnee);
		E2 = new ElementDeRegulation((s) -> s.passerAuVert() , listeSemaphoreOrdonnee);
		E3 = new ElementDeRegulation((s) -> s.changerFeu() , listeSemaphoreAbcisse);
		E4 = new ElementDeRegulation((s) -> s.passerAuVert() , listeSemaphoreAbcisse);
		
		//Creation des capteurs
		CP1 = new CapteurPresence(R4,listeSemaphoreOrdonnee,E1,true);
		CV1 = new CapteurVitesse(R4,listeSemaphoreAbcisse,E3,false);
		CP2 = new CapteurPresence(R2,listeSemaphoreOrdonnee,E2,true);
		CV2 = new CapteurVitesse(R2,listeSemaphoreAbcisse,E4,false);
		
		//Verification de la validite de la construction de toutes les routes
		for(Route r : listeRoute) {
			if(!r.estValide()) {
				System.out.println("Erreur construction de route");
				return;
			}
		}
		
		//Boucle qui lance la simulation
		while(true) {
			//Les vehicules avancent
			for(Vehicule v : listeVehicule) {
				v.avance();
			}
			//Les feux changent 1 fois sur 3
			if(n%3==0) {
				n = 0;
				for(Semaphore s : listeSemaphoreAbcisse) {
					s.changerFeu();
				}
				for(Semaphore s : listeSemaphoreOrdonnee) {
					s.changerFeu();
				}
			}
			n++;
			System.out.println(R4.toString());
			System.out.println();

			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
