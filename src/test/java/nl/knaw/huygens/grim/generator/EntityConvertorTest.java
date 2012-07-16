package nl.knaw.huygens.grim.generator;

import static org.junit.Assert.*;
import java.util.List;
import nl.knaw.huygens.grim.generator.EntityConvertor;
import nl.knaw.huygens.grim.model.EntityMock;
import nl.knaw.huygens.grim.rdf.PropertyConversionWrapper;

import org.junit.Before;
import org.junit.Test;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.ResourceFactory;

public class EntityConvertorTest {

	private EntityConvertor<EntityMock> convertor;

	@Before
	public void beforeTest() {
		convertor = new EntityConvertor<EntityMock>("someService", EntityMock.class);
	}
	
	@Test
	public void testCreateConversionMap() throws SecurityException, NoSuchMethodException {
		List<PropertyConversionWrapper> list = convertor.createConversionMap("someService", EntityMock.class);
		assertEquals(list.get(0), new PropertyConversionWrapper(EntityMock.class.getDeclaredMethod("setProperty", String.class), "someNamespace/", "property"));
		assertEquals(list.get(1), new PropertyConversionWrapper(EntityMock.class.getDeclaredMethod("setName", String.class), "someNamespace/", "name"));
	}

	@Test
	public void testConvert() {
		Model model = ModelFactory.createDefaultModel();
		Resource subject = model.createResource("http://somewhere/entity");
		
		Property namePredicate = ResourceFactory.createProperty("someNamespace/", "name");
		Literal nameObject = ResourceFactory.createPlainLiteral("Blub_name");
		subject.addProperty(namePredicate, nameObject);
		
		Property propPredicate = ResourceFactory.createProperty("someNamespace/", "property");
		Literal propObject = ResourceFactory.createPlainLiteral("Blub_property");
		subject.addProperty(propPredicate, propObject);
		
		EntityMock mock = convertor.convert(subject);
		assertEquals("Blub_name", mock.getName());
		assertEquals("Blub_property", mock.getProperty());
	}
}
