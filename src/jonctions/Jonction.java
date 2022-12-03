package jonctions;

import java.util.Map;
import java.util.Random;
import java.util.Set;

import exception.ErreurConstruction;
import routes.Route;

public abstract class Jonction {
	protected Map<Route, Boolean> sesVoies;
	
	
	public Route getRouteAleatoire() {
		Random random = new Random();

		Route[] sesSegmentsDeRoute = getSesRoutes().toArray(new Route[0]);
		
		return sesSegmentsDeRoute[random.nextInt(sesVoies.size())];
	}
	
	public boolean getSensSortant(Route r) {
		return sesVoies.get(r);
	}
	
	public Set<Route> getSesRoutes () {
		return sesVoies.keySet();
	}
	
	/**
	 * Ajoute une route cette jonction.
	 * @param r la route a connecter.
	 * @throws ErreurConstruction Une erreur si r vaut null, ou si r est deja connecte a 2 jonctions.
	 */
	protected void addRoute(Route r) throws ErreurConstruction {
		if (r == null) {
			throw new ErreurConstruction("Une jonction ne peut pas etre reliee a une Route null");
		}
		boolean sensEntrant = r.addJonction(this);
		boolean sensSortant = !sensEntrant;
		sesVoies.put(r, sensSortant);
	}
}
