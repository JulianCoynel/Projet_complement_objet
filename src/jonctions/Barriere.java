package jonctions;

import java.util.HashMap;
import java.util.Map;

import routes.Route;

public class Barriere extends Jonction{

	public Barriere(Route r) {
		sesVoies = new HashMap<Route, Boolean>();
	}
	
}
