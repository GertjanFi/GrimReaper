package nl.knaw.huygens.grim.reaper;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import nl.knaw.huygens.grim.generator.EntityConvertor;
import nl.knaw.huygens.grim.generator.ResourceGenerator;
import nl.knaw.huygens.grim.model.EntityMock;
import nl.knaw.huygens.grim.traversal.Reaper;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.hp.hpl.jena.rdf.model.Resource;

public class ReaperTest {

	private String uri = "http://somewhere/entity";
	private Reaper<EntityMock> reaper;
	@Mock
	private ResourceGenerator mockedResourceGenerator;
	@Mock
	private EntityConvertor<EntityMock> mockedEntityConvertor;
	@Mock
	private Resource mockedResource;

	@Before
	public void beforeTest() {
	    MockitoAnnotations.initMocks(this);
	    reaper = new Reaper<EntityMock>(uri, EntityMock.class);
	    when(mockedResourceGenerator.getEntity(uri)).thenReturn(mockedResource);
	    when(mockedEntityConvertor.convert(mockedResource)).thenReturn(new EntityMock());
	    reaper.setResourceGenerator(mockedResourceGenerator);
	    reaper.setConvertor(mockedEntityConvertor);
	}
	
	@Test
	public void testReap() {
		reaper.reap(uri);
		verify(mockedResourceGenerator).getEntity(uri);
		verify(mockedEntityConvertor).convert(mockedResource);
	}
}
