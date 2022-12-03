package jonctions;

import java.util.HashMap;

import exception.ErreurConstruction;
import routes.Route;

public class JonctionSimple extends Jonction {
	
	public JonctionSimple(Route r1, Route r2) throws ErreurConstruction {
		sesVoies = new HashMap<Route, Boolean>();
		addRoute(r1);
		addRoute(r2);
	}

}
