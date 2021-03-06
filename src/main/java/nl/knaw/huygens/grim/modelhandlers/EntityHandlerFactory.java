/*******************************************************************************
 * Copyright (c) 2012 Huygens ING.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 * 
 * Contributors:
 *     Huygens ING - initial API and implementation
 ******************************************************************************/
package nl.knaw.huygens.grim.modelhandlers;

import nl.knaw.huygens.grim.model.Entity;
import nl.knaw.huygens.grim.model.Location;
import nl.knaw.huygens.grim.model.Person;

public class EntityHandlerFactory<T extends Entity> {

	public IEntityHandler<T> getHandler(Class<T> clazz) {
		if (clazz.equals(Location.class)) {
			return new LocationHandler<T>();
		} else if (clazz.equals(Person.class)) {
			return new PersonHandler<T>();
		} else return null;
	}
}
