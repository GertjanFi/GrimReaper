package nl.knaw.huygens.grim.traversal;

import com.hp.hpl.jena.rdf.model.Resource;
import nl.knaw.huygens.grim.generator.EntityConvertor;
import nl.knaw.huygens.grim.generator.ResourceGenerator;
import nl.knaw.huygens.grim.model.Entity;

public class Reaper<T extends Entity> {
	
	private ResourceGenerator resourceGenerator;
	private EntityConvertor<T> convertor;
	
	public Reaper(String service, Class<T> clazz) {
		this.convertor = new EntityConvertor<T>(service, clazz);
		this.resourceGenerator = new ResourceGenerator();
	}

	public T reap(String uri) {
		Resource resource = resourceGenerator.getEntity(uri);
		T entity = convertor.convert(resource);				
		entity.setSource(uri);
		return entity;
	}

	public void setResourceGenerator(ResourceGenerator resourceGenerator) {
		this.resourceGenerator = resourceGenerator;
	}
	
	public void setConvertor(EntityConvertor<T> convertor) {
		this.convertor = convertor;
	}
}
