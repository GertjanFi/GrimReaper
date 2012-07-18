package nl.knaw.huygens.grim.traversal;

import nl.knaw.huygens.grim.generator.Reaper;
import nl.knaw.huygens.grim.model.Entity;
import nl.knaw.huygens.grim.modelhandlers.EntityHandlerFactory;
import nl.knaw.huygens.grim.modelhandlers.IEntityHandler;
import nl.knaw.huygens.grim.utils.StringUtils;

public class TraversalActor<T extends Entity> {

	private final String resourceNs;
	private final String service;
	private final Class<T> entityClass;	
	private Reaper<T> reaper;
	private IEntityHandler<T> entityHandler;
	
	public TraversalActor(String resourceNs, String service, Class<T> clazz) {
		EntityHandlerFactory<T> factory = new EntityHandlerFactory<T>();
		this.service = service;
		this.entityClass = clazz;
		this.entityHandler = factory.getHandler(clazz);
		this.resourceNs = resourceNs;	
		this.reaper = new Reaper<T>(this.service, this.entityClass);  		
	}
	
	public void setReaper(Reaper<T> reaper) {
		this.reaper = reaper;
	}

	public void setEntityHandler(IEntityHandler<T> entityHandler) {
		this.entityHandler = entityHandler;
	}	
	
	public T act(String name) {
//		TODO: if the name does not result in a resource: figure out a way to look for a valid resource in the string.         		
    	name = StringUtils.normalize(name.replace(" ", "_"));    	
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
