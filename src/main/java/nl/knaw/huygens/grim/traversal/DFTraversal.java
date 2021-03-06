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

//import com.google.common.base.Strings;
import com.google.common.collect.Lists;

import nl.knaw.huygens.grim.model.Entity;

public class DFTraversal<T extends Entity> implements ITraversal<T> {

	private final List<T> entities;
	private final List<String> visited;
	private final TraversalActor<T> actor;


	public DFTraversal(String resourceNs, String service, Class<T> clazz) {
		this(resourceNs, service, clazz, new TraversalActor<T>(resourceNs, service, clazz));
	}
	
	public DFTraversal(String resourceNs, String service, Class<T> clazz, TraversalActor<T> actor) {
		this.entities = Lists.newArrayList();
		this.visited = Lists.newArrayList();
		this.actor = actor;		
	}

	@Override
	public List<T> run(String rootName) {
		return depthFirstTraversal(doReap(rootName));
	}

	public List<String> getVisited() {
		return visited;
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
		if (entity.getChildren() != null) {
			return entity.getChildren().size() == 0 ? false : true;
		} else {
			return false;
		}
	}
	
	private T doReap(String name) {
    	if (!visited.contains(name) && name != null) {
    		visited.add(name);
    		T entity = actor.act(name);
    		depthFirstTraversal(entity);
    		return entity;
    	} else return null;	//FIXME: null suxx
	}
     	
}
