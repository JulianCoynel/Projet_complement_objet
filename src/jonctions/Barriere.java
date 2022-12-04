package jonctions;

import java.util.HashMap;

import exception.ErreurConstruction;
import routes.Route;

public class Barriere extends Jonction{

	public Barriere(Route r) throws ErreurConstruction {
		sesVoies = new HashMap<Route, Boolean>();
		addRoute(r);
	}
}
