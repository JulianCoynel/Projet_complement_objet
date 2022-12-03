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
}
