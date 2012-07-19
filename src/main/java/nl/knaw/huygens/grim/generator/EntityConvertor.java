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
package nl.knaw.huygens.grim.generator;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import nl.knaw.huygens.grim.model.Conversions;
import nl.knaw.huygens.grim.model.ConvertsFrom;
import nl.knaw.huygens.grim.model.Entity;
import nl.knaw.huygens.grim.rdf.PropertyConversionWrapper;

import com.google.common.collect.Lists;
import com.hp.hpl.jena.rdf.model.NodeIterator;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;

public class EntityConvertor<T extends Entity> {

	private final Class<T> clazz;
	private List<PropertyConversionWrapper> conversions;
	
	public EntityConvertor(String service, Class<T> clazz) {
		this.clazz = clazz;
		this.conversions = createConversionMap(service, clazz);
	}
	
	public T convert(Resource resource) {
		try {
			T entity = clazz.newInstance();
			return doConversion(resource, entity);
		} catch (InstantiationException e) {
			throw new RuntimeException("Cannot instantiate class: " + clazz.getCanonicalName());
		} catch (IllegalAccessException e) {
			throw new RuntimeException();
		}		
	}

	private T doConversion(Resource resource, T entity) {
		for(PropertyConversionWrapper p : conversions) {
			try {
				if (resource.hasProperty(p.getProperty())) {
					NodeIterator list = resource.getModel().listObjectsOfProperty(p.getProperty());
					while(list.hasNext()) {
						RDFNode node  = list.next();
						String value = "";
						if(node.isLiteral()) {
							value = node.asLiteral().getString();
						} else if(node.isResource()) {
							//value = node.asResource().getLocalName(); JENA API BROKEN... content before encoded characters or even comma's is lost
							value = node.asResource().getURI().replace("http://live.dbpedia.org/resource/", "").replace("_", " ");
							System.out.println("Value: " + node.asResource().getLocalName());							
						}
						p.getMethod().invoke(entity, value);	    					
					}	    			
				}
			} catch (IllegalArgumentException e) {
				throw new RuntimeException("Illegal Argument while adding '" + resource.getProperty(p.getProperty()).getLiteral().getString() + "' with method: '" + p.getMethod().getName() + "' on class: '" + entity.getClass().getName() + "'.");
			} catch (IllegalAccessException e) {
				throw new RuntimeException("Illegal Access while adding '" + resource.getProperty(p.getProperty()).getLiteral().getString() + "' with method: '" + p.getMethod().getName() + "' on class: '" + entity.getClass().getName() + "'.");
			} catch (InvocationTargetException e) {
				throw new RuntimeException("Invocation Target Exception while adding '" + resource.getProperty(p.getProperty()).getLiteral().getString() + "' with method: '" + p.getMethod().getName() + "' on class: '" + entity.getClass().getName() + "'.");
			} 		
		}
		return entity;
	}
	
	protected List<PropertyConversionWrapper> createConversionMap(String service, Class<T> clazz) {
		List<PropertyConversionWrapper> list = Lists.newArrayList();
		for(Method method : clazz.getDeclaredMethods()) {
			for(Annotation annotation: method.getDeclaredAnnotations()) {
			    if(annotation instanceof ConvertsFrom){
			    	ConvertsFrom convertsFromAnnotation = (ConvertsFrom) annotation;
			    	if(convertsFromAnnotation.service().equals(service)) {
			    		PropertyConversionWrapper p = new PropertyConversionWrapper(method, convertsFromAnnotation.namespace(), convertsFromAnnotation.property());
			    		list.add(p);	
			    	}
			    }
			    if(annotation instanceof Conversions) {
			    	Conversions conversionsAnnotation = (Conversions) annotation;
			    	for(ConvertsFrom convertsFromAnnotation : conversionsAnnotation.conversions()) {
				    	if(convertsFromAnnotation.service().equals(service)) {
				    		PropertyConversionWrapper p = new PropertyConversionWrapper(method, convertsFromAnnotation.namespace(), convertsFromAnnotation.property());
				    		list.add(p);	
				    	}			    		
			    	}
			    }
			}
		}
		return list;
	}
}
