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

import org.codehaus.jackson.map.ObjectMapper;

import nl.knaw.huygens.grim.model.Entity;

public class EntityMockHandler<T extends Entity> implements IEntityHandler<T> {
	ObjectMapper mapper = new ObjectMapper();
	
	@Override
	public void write(T entity) {
		
	}

	@Override
	public boolean verify(T entity) {
		return true;
	}
}
