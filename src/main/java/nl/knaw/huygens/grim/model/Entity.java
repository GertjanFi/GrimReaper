package nl.knaw.huygens.grim.model;

import java.util.List;

public abstract class Entity {

	private String id;
	private String rev;
	private String source;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRev() {
		return rev;
	}
	public void setRev(String rev) {
		this.rev = rev;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	
	public abstract List<String> getChildren();
}
