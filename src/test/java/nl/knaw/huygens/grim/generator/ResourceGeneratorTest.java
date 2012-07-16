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

import static org.mockito.Mockito.*;
import nl.knaw.huygens.grim.generator.ResourceGenerator;
import nl.knaw.huygens.grim.rdf.PropertyConversionWrapper;
import nl.knaw.huygens.grim.rdf.RDFHandler;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.hp.hpl.jena.rdf.model.ResIterator;
import com.hp.hpl.jena.rdf.model.Resource;

public class ResourceGeneratorTest {


	private ResourceGenerator resourceGenerator;
	@Mock
	private RDFHandler mockedRdfHandler;
	@Mock
	private Resource mockedResource;
	@Mock
	private PropertyConversionWrapper mockedPropertyWrapper;
	@Mock
	private ResIterator mockedResources;
	private String uri = "http://somewhere/entity";;
	
	@Before
	public void beforeTest() {
	    MockitoAnnotations.initMocks(this);
	    resourceGenerator = new ResourceGenerator(mockedRdfHandler);
	    when(mockedRdfHandler.getSubject(anyString())).thenReturn(mockedResource);
	    when(mockedRdfHandler.getSubjectsWithProperty(mockedPropertyWrapper, uri)).thenReturn(mockedResources);
	    when(mockedResources.hasNext()).thenReturn(true).thenReturn(true).thenReturn(false); 
	    when(mockedResources.next()).thenReturn(mockedResource);
	}
	
	@Test
	public void testGetEntity() {
		resourceGenerator.getEntity(uri);
		verify(mockedRdfHandler).getSubject(uri);
	}
	
	@Test
	public void testGetEntitiesByPropertyPath() {
		resourceGenerator.getEntitiesByPropertyPath(uri, mockedPropertyWrapper);
		verify(mockedRdfHandler).getSubjectsWithProperty(mockedPropertyWrapper, uri);
		verify(mockedResources).next();
	}

	@Test
	public void testGetModel() {
		resourceGenerator = new ResourceGenerator();
		Resource resource = resourceGenerator.getEntity("http://live.dbpedia.org/resource/Ghent");
		System.out.println("Resource: " + resource.toString());
//		System.out.println("HasProp: " + resource.hasProperty(ResourceFactory.createProperty("http://dbpedia.org/property/", "s")));
//		NodeIterator list = resource.getModel().listObjectsOfProperty(ResourceFactory.createProperty("http://dbpedia.org/property/", "s"));
//		while(list.hasNext()) {
//			System.out.println("Value: " + list.next().asLiteral().getString());
//		}
		resource.getModel().write(System.out);
	}
}
