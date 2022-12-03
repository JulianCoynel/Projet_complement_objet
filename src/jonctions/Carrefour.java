package jonctions;

import java.util.HashMap;
import java.util.Set;

import exception.ErreurConstruction;
import routes.Route;

public class Carrefour extends Jonction{

	public Carrefour(Set<Route> routes) throws ErreurConstruction {
		sesVoies = new HashMap<Route, Boolean>();
		
		if (routes.size() < 3) {
			throw new ErreurConstruction("Un carrefour connecte au moins 3 segments de routes");
		}
		
		for (Route r : routes) {
			addRoute(r);
		}
	}
	
	@Override
	protected void addRoute(Route r) throws ErreurConstruction {
		if (r == null) {
			throw new ErreurConstruction("Une jonction ne peut pas etre reliee a une Route null");
		}
		boolean sensEntrant = r.addJonction(this);
		boolean sensSortant = !sensEntrant;
		
		if (r.feu(sensEntrant) != true) {
			throw new ErreurConstruction("Tout carrefour est suppose etre regule par des feu de circulation");
		}
		sesVoies.put(r, sensSortant);
	}
}
