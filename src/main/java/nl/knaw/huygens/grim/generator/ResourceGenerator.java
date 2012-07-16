package nl.knaw.huygens.grim.generator;

import java.util.List;

import nl.knaw.huygens.grim.rdf.PropertyConversionWrapper;
import nl.knaw.huygens.grim.rdf.RDFHandler;

import com.google.common.collect.Lists;
import com.hp.hpl.jena.rdf.model.ResIterator;
import com.hp.hpl.jena.rdf.model.Resource;

public class ResourceGenerator {

	private final RDFHandler rdfHandler;
	
	public ResourceGenerator() {
		this(new RDFHandler());
	}
	
	public ResourceGenerator(RDFHandler rdfHandler) {
		this.rdfHandler = rdfHandler;		
	}
	
	public Resource getEntity(String uri) {
		Resource subject = rdfHandler.getSubject(uri);
		if(subject == null) {
			throw new RuntimeException("No entity found with uri: " + uri);
		}
		return subject;
	}	
	
	public List<Resource> getEntitiesByPropertyPath(String uri, PropertyConversionWrapper p) {
		List<Resource> resources = Lists.newArrayList();		
		ResIterator subjects = rdfHandler.getSubjectsWithProperty(p, uri);	
		if(!subjects.hasNext()) {
			throw new RuntimeException("No entities found with property: " + p.getProperty().getLocalName());
		}
		while(subjects.hasNext()) {
			resources.add(subjects.next());
		}	 
		return resources;
	}
}
