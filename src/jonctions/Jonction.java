package jonctions;

import java.util.Map;
import java.util.Random;
import java.util.Set;

import routes.Route;

public abstract class Jonction {
	private Map<Route, Boolean> sesVoies;
	
	private int getTaille() {
		return sesVoies.size();
	}
	
	public Set<Route> getSesSegmentsDeRoute () {
		return sesVoies.keySet();
	}

	public Route getRouteAleatoire() {
		Random random = new Random();

		Route[] sesSegmentsDeRoute = getSesSegmentsDeRoute().toArray(new Route[0]);
		
		return sesSegmentsDeRoute[random.nextInt(getTaille())];
	}
	
	public boolean getSensSortant(Route r) {
		return sesVoies.get(r);
	}
}
