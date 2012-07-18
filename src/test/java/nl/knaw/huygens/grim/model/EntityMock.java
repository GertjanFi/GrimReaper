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
package nl.knaw.huygens.grim.model;

import java.util.List;

import nl.knaw.huygens.grim.model.ConvertsFrom;
import nl.knaw.huygens.grim.model.Entity;

public class EntityMock extends Entity {

	private String name;
	private String property;
	private List<String> children;

	public EntityMock() {
	}
	
	public String getName() {
		return name;
	}
	
	@ConvertsFrom(service="someService", namespace="someNamespace/", property="name")
	public void setName(String name) {
		this.name = name;
	}
	
	public String getProperty() {
		return property;
	}
	
	@ConvertsFrom(service="someService", namespace="someNamespace/", property="property")
	public void setProperty(String property) {
		this.property = property;
	}
	
	public List<String> getChildren() {
		return children;
	}
	
	public void setChildren(List<String> children) {
		this.children = children;
	}
}
