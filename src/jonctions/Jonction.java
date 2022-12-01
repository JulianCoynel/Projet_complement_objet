package jonctions;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

import routes.Route;

public abstract class Jonction {
	private Map<Route, Boolean> sesVoies;

	private int getTaille() {
		return sesVoies.size();
	}
	
	public Route[] getSesSegmentsDeRoute () {
		return sesVoies.keySet().toArray(new Route[0]);
	}

	public Route getRouteAleatoire() {
		Random random = new Random();

		Route[] sesSegmentsDeRoute = getSesSegmentsDeRoute();
		
		return sesSegmentsDeRoute[random.nextInt(getTaille())];
	}
	
	public boolean getSensSortant(Route r) {
		return sesVoies.get(r);
	}
}
