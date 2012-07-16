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
package nl.knaw.huygens.grim.traversal;
import java.util.List;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;

import nl.knaw.huygens.grim.model.Entity;
import nl.knaw.huygens.grim.utils.StringUtils;

public class DFTraversal<T extends Entity> implements ITraversal<T> {

	private final String resourceNs;
	private final IActionHandler<T> actionHandler;
	private final String service;
	private final Class<T> entityClass;	
	private final List<T> entities;
	private final List<String> visited;
	private Reaper<T> reaper;


	public DFTraversal(String resourceNs, String service, Class<T> clazz) {
		ActionHandlerFactory<T> factory = new ActionHandlerFactory<T>();
		this.service = service;
		this.entityClass = clazz;
		this.actionHandler = factory.getHandler(clazz);
		this.entities = Lists.newArrayList();
		this.visited = Lists.newArrayList();
		this.resourceNs = resourceNs;	
	}

	@Override
	public List<T> run(String rootName) {
		return depthFirstTraversal(doReap(rootName));
	}

	private List<T> depthFirstTraversal(T entity) {
	    if (isLeaf(entity)) {
	        for (String childName : entity.getChildren()) {
	        	doReap(childName);
	        }
	    }
	    entities.add(entity);
	    return entities;
	}	
	
	private boolean isLeaf(T entity) {
	    return entity.getChildren().size() == 0 ? false : true;
	}
	
	private T doReap(String name) {
    	name = StringUtils.normalize(name.replace(" ", "_"));    	
    	if (!visited.contains(name)) {
        	visited.add(name);
    		reaper = new Reaper<T>(this.service, this.entityClass);  		
        	T entity = reaper.reap(resourceNs + name);
        	if (!actionHandler.verify(entity) && !(Strings.isNullOrEmpty(name))) { 
//				TODO: if the name does not result in a resource, figure out a way to look for a valid resource in the string.         		
//        		name = StringUtils.dropFromLastUpperCase(name);
//        		if (name != "") {
//        			System.out.println("Checking for: " + resourceNs + name);
        			doReap(name);
//        		}
        	} else {
        		actionHandler.act(entity);
        		depthFirstTraversal(entity);
        	}
        	return entity;
    	} else return null;
	}
}
