package jonctions;

import java.util.Map;
import java.util.Random;
import java.util.Set;

import routes.Route;

public abstract class Jonction {
	protected Map<Route, Boolean> sesVoies;
	
	
	private int getTaille() {
		return sesVoies.size();
	}
	
	public Set<Route> getSesRoutes () {
		return sesVoies.keySet();
	}

	public Route getRouteAleatoire() {
		Random random = new Random();

		Route[] sesSegmentsDeRoute = getSesRoutes().toArray(new Route[0]);
		
		return sesSegmentsDeRoute[random.nextInt(getTaille())];
	}
	
	public boolean getSensSortant(Route r) {
		return sesVoies.get(r);
	}
}
