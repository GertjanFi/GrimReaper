package nl.knaw.huygens.grim.traversal;

import nl.knaw.huygens.grim.model.Entity;
import nl.knaw.huygens.grim.model.Location;

public class ActionHandlerFactory<T extends Entity> {

	public IActionHandler<T> getHandler(Class<T> clazz) {
		if (clazz.equals(Location.class)) {
			return new LocationHandler<T>();
		} else return null;
	}
}
