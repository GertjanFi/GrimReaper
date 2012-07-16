package nl.knaw.huygens.grim.model;

import java.util.List;

import nl.knaw.huygens.grim.model.ConvertsFrom;
import nl.knaw.huygens.grim.model.Entity;

public class EntityMock extends Entity {

	private String name;
	private String property;
	private List<String> children;

	public EntityMock() {
		// TODO Auto-generated constructor stub
	}
			
	public String getName() {
		return name;
	}
	
	@ConvertsFrom(service="someService", namespace="someNamespace/", property="name")
	public void setName(String name) {
		this.name = name;
	}
	
	public String getProperty() {
		return property;
	}
	
	@ConvertsFrom(service="someService", namespace="someNamespace/", property="property")
	public void setProperty(String property) {
		this.property = property;
	}
	
	public List<String> getChildren() {
		return children;
	}
}