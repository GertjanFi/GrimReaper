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
package nl.knaw.huygens.grim.rdf;

import java.lang.reflect.Method;

import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.ResourceFactory;

public class PropertyConversionWrapper {
	
	private final Method method;
	private final Property property;
	private final String namespace;
	private final String localName;

	public PropertyConversionWrapper(Method method, String namespace, String localName) {
		this.method = method;
		this.namespace = namespace;
		this.localName = localName;
		this.property = ResourceFactory.createProperty(namespace, localName);
	}

	public Method getMethod() {
		return method;
	}

	public Property getProperty() {
		return property;
	}
	
	public String getNamespace() {
		return namespace;
	}

	public String getLocalName() {
		return localName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((localName == null) ? 0 : localName.hashCode());
		result = prime * result + ((method == null) ? 0 : method.hashCode());
		result = prime * result
				+ ((namespace == null) ? 0 : namespace.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof PropertyConversionWrapper)) {
			return false;
		}
		PropertyConversionWrapper other = (PropertyConversionWrapper) obj;
		if (localName == null) {
			if (other.localName != null) {
				return false;
			}
		} else if (!localName.equals(other.localName)) {
			return false;
		}
		if (method == null) {
			if (other.method != null) {
				return false;
			}
		} else if (!method.equals(other.method)) {
			return false;
		}
		if (namespace == null) {
			if (other.namespace != null) {
				return false;
			}
		} else if (!namespace.equals(other.namespace)) {
			return false;
		}
		return true;
	}
}
