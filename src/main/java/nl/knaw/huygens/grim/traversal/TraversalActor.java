package nl.knaw.huygens.grim.traversal;

import nl.knaw.huygens.grim.generator.Reaper;
import nl.knaw.huygens.grim.model.Entity;
import nl.knaw.huygens.grim.modelhandlers.EntityHandlerFactory;
import nl.knaw.huygens.grim.modelhandlers.IEntityHandler;
import nl.knaw.huygens.grim.utils.StringUtils;

public class TraversalActor<T extends Entity> {

	private final String resourceNs;
	private final IEntityHandler<T> entityHandler;
	private final String service;
	private final Class<T> entityClass;	
	private Reaper<T> reaper;
	
	public TraversalActor(String resourceNs, String service, Class<T> clazz) {
		EntityHandlerFactory<T> factory = new EntityHandlerFactory<T>();
		this.service = service;
		this.entityClass = clazz;
		this.entityHandler = factory.getHandler(clazz);
		this.resourceNs = resourceNs;	
	}
	
	public T act(String name) {
//		TODO: if the name does not result in a resource: figure out a way to look for a valid resource in the string.         		
    	name = StringUtils.normalize(name.replace(" ", "_"));    	
		reaper = new Reaper<T>(this.service, this.entityClass);  		
    	T entity = reaper.reap(resourceNs + name);
    	if (entityHandler.verify(entity)) {
    		entityHandler.write(entity);
    	}
    	return entity;   	
//    	if (!actionHandler.verify(entity) && !(Strings.isNullOrEmpty(name))) { 
//    		name = StringUtils.dropFromLastUpperCase(name);
//    		if (name != "") {
//    			System.out.println("Checking for: " + resourceNs + name);
//    			doReap(name);
//    		}
//    	} else {
//    		actionHandler.write(entity);
//    		depthFirstTraversal(entity);
//    	}  	
	}   
}
