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
package nl.knaw.huygens.grim.model;

import java.util.List;
import com.google.common.collect.Lists;

public class Person extends Entity {

	private List<String> names = Lists.newArrayList();
	private List<String> birthDate = Lists.newArrayList();
	private List<String> deathDate = Lists.newArrayList();
	private String mother;
	private String father;
	private List<String> bornChildren = Lists.newArrayList();
	private List<String> spouses = Lists.newArrayList();
	private List<String> preceded = Lists.newArrayList();
	private List<String> succeeded = Lists.newArrayList();
	private String description;
	

	public Person() {
		// TODO Auto-generated constructor stub
	}
	
	public List<String> getNames() {
		return names;
	}
	
	@Conversions(conversions={
		@ConvertsFrom(service="http://live.dbpedia.org", namespace="http://live.dbpedia.org/property/", property="name"),
		})
	public void addName(String name) {
		this.names.add(name);
	}
	
	public List<String> getBirthDate() {
		return birthDate;
	}

	@Conversions(conversions={
			@ConvertsFrom(service="http://live.dbpedia.org", namespace="http://live.dbpedia.org/property/", property="dateOfBirth"),
			@ConvertsFrom(service="http://live.dbpedia.org", namespace="http://live.dbpedia.org/property/", property="birthDate")
		})	
	public void addBirthDate(String birthDate) {
		this.birthDate.add(birthDate);
	}
	
	public List<String> getDeathDate() {
		return deathDate;
	}
	
	@Conversions(conversions={
			@ConvertsFrom(service="http://live.dbpedia.org", namespace="http://live.dbpedia.org/property/", property="dateOfDeath"),
			@ConvertsFrom(service="http://live.dbpedia.org", namespace="http://live.dbpedia.org/property/", property="deathDate")
		})	
	public void addDeathDate(String deathDate) {
		this.deathDate.add(deathDate);
	}

	public List<String> getBornChildren() {
		return bornChildren;
	}
	
	@Conversions(conversions={	
			@ConvertsFrom(service="http://live.dbpedia.org", namespace="http://live.dbpedia.org/ontology/", property="father"),
			@ConvertsFrom(service="http://live.dbpedia.org", namespace="http://live.dbpedia.org/property/", property="parent")
	})
	public void addBornChildren(String child) {
		this.bornChildren.add(child);
	}
	
	public String getMother() {
		return mother;
	}

	@ConvertsFrom(service="http://live.dbpedia.org", namespace="http://live.dbpedia.org/property/", property="mother")	
	public void setMother(String mummy) {
		this.mother = mummy;
	}

	public String getFather() {
		return father;
	}

	@ConvertsFrom(service="http://live.dbpedia.org", namespace="http://live.dbpedia.org/property/", property="father")	
	public void setFather(String daddy) {
		this.father = daddy;
	}
	
	public List<String> getSpouses() {
		return spouses;
	}
	
	@Conversions(conversions={
			@ConvertsFrom(service="http://live.dbpedia.org", namespace="http://live.dbpedia.org/ontology/", property="spouse"),
			@ConvertsFrom(service="http://live.dbpedia.org", namespace="http://live.dbpedia.org/ontology/", property="spouses"),
			@ConvertsFrom(service="http://live.dbpedia.org", namespace="http://live.dbpedia.org/property/", property="spouse")
		})	
	public void addSpouse(String spouse) {
		this.spouses.add(spouse);
	}
	
	public List<String> getPreceded() {
		return preceded;
	}
	
	@ConvertsFrom(service="http://live.dbpedia.org", namespace="http://live.dbpedia.org/property/", property="predecessor")
	public void addPreceded(String preceded) {
		this.preceded.add(preceded);
	}
	
	public List<String>  getSucceeded() {
		return succeeded;
	}
	
	@ConvertsFrom(service="http://live.dbpedia.org", namespace="http://live.dbpedia.org/property/", property="successor")
	public void addSucceeded(String succeeded) {
		this.succeeded.add(succeeded);
	}

	@Override
	public List<String> getChildren() {
		List<String> returnValue = Lists.newArrayList();
		returnValue.addAll(succeeded);
		returnValue.addAll(preceded);
		returnValue.addAll(spouses);
		returnValue.add(father);
		returnValue.add(mother);		
		returnValue.addAll(bornChildren);
		return returnValue;
	}

	public String getDescription() {
		return description;
	}

	@ConvertsFrom(service="http://live.dbpedia.org", namespace="http://www.w3.org/2000/01/rdf-schema#", property="comment")
	public void setDescription(String description) {
		this.description = description;
	}
	
}
