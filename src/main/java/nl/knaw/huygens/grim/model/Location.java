package nl.knaw.huygens.grim.model;

import java.util.List;
import com.google.common.collect.Lists;

public class Location extends Entity {

	private List<String> names = Lists.newArrayList();
	private String longitude;
	private String latitude;
	private String continent;
	private List<String> foundingDate = Lists.newArrayList();
	private List<String> dissolutionDate = Lists.newArrayList();
	private String description;
	private List<String> capital  = Lists.newArrayList();
	private List<String> preceded = Lists.newArrayList();
	private List<String> succeeded = Lists.newArrayList();
	

	public Location() {
		// TODO Auto-generated constructor stub
	}
	
	public List<String> getNames() {
		return names;
	}
	
	@Conversions(conversions={
		@ConvertsFrom(service="http://live.dbpedia.org", namespace="http://live.dbpedia.org/property/", property="nativeName"),
		@ConvertsFrom(service="http://live.dbpedia.org", namespace="http://live.dbpedia.org/property/", property="commonName"),
		@ConvertsFrom(service="http://live.dbpedia.org", namespace="http://live.dbpedia.org/property/", property="conventionalLongName")
		})
	public void addName(String name) {
		this.names.add(name);
	}
	
	public List<String> getFoundingDate() {
		return foundingDate;
	}

	@Conversions(conversions={
			@ConvertsFrom(service="http://live.dbpedia.org", namespace="http://live.dbpedia.org/ontology/", property="foundingYear"),
			@ConvertsFrom(service="http://live.dbpedia.org", namespace="http://live.dbpedia.org/ontology/", property="foundingDate")
		})	
	public void addFoundingDate(String foundingDate) {
		this.foundingDate.add(foundingDate);
	}
	
	public List<String> getDissolutionDate() {
		return dissolutionDate;
	}
	
	@Conversions(conversions={
			@ConvertsFrom(service="http://live.dbpedia.org", namespace="http://live.dbpedia.org/ontology/", property="dissolutionYear"),
			@ConvertsFrom(service="http://live.dbpedia.org", namespace="http://live.dbpedia.org/ontology/", property="dissolutionDate")
		})	
	public void addDissolutionDate(String dissolutionDate) {
		this.dissolutionDate.add(dissolutionDate);
	}
	
	public List<String> getPreceded() {
		return preceded;
	}
	
	@ConvertsFrom(service="http://live.dbpedia.org", namespace="http://live.dbpedia.org/property/", property="p")
	public void addPreceded(String preceded) {
		this.preceded.add(preceded);
	}
	
	public List<String>  getSucceeded() {
		return succeeded;
	}
	
	@ConvertsFrom(service="http://live.dbpedia.org", namespace="http://live.dbpedia.org/property/", property="s")
	public void addSucceeded(String succeeded) {
		this.succeeded.add(succeeded);
	}

	public String getContinent() {
		return continent;
	}

	@ConvertsFrom(service="http://live.dbpedia.org", namespace="http://live.dbpedia.org/property/", property="continent")
	public void setContinent(String continent) {
		this.continent = continent;
	}

	public String getLongitude() {
		return longitude;
	}

	@ConvertsFrom(service="http://live.dbpedia.org", namespace="http://www.w3.org/2003/01/geo/wgs84_pos#", property="long")	
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	@ConvertsFrom(service="http://live.dbpedia.org", namespace="http://www.w3.org/2003/01/geo/wgs84_pos#", property="lat")
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	
	public String getDescription() {
		return description;
	}

	@ConvertsFrom(service="http://live.dbpedia.org", namespace="http://live.dbpedia.org/ontology/", property="abstract")
	public void setDescription(String description) {
		this.description = description;
	}

	public List<String> getCapital() {
		return capital;
	}

	@ConvertsFrom(service="http://live.dbpedia.org", namespace="http://live.dbpedia.org/ontology/", property="capital")
	public void addCapital(String capital) {
		this.capital.add(capital);
	}

	@Override
	public List<String> getChildren() {
		List<String> returnValue = Lists.newArrayList();
		returnValue.addAll(succeeded);
		returnValue.addAll(preceded);
		returnValue.addAll(capital);
		return returnValue;
	}
}
