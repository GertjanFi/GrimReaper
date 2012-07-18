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
package nl.knaw.huygens.grim.modelhandlers;

import java.io.BufferedWriter;
import java.io.FileWriter;
import org.codehaus.jackson.map.ObjectMapper;

import nl.knaw.huygens.grim.model.Entity;
import nl.knaw.huygens.grim.model.Person;

public class PersonHandler<T extends Entity> implements IEntityHandler<T> {
	ObjectMapper mapper = new ObjectMapper();
	
	@Override
	public void write(T entity) {
		Person person = (Person) entity;
		try {
			String fileName = "data/json/persons/" + person.getSource().replace("http://live.dbpedia.org/resource/", "dbpedia_").replace("/", "slash") + ".json";
			BufferedWriter out = new BufferedWriter(new FileWriter(fileName));
			out.write(mapper.writeValueAsString(person));
			out.close();
		} catch (Exception e) {
			throw new RuntimeException("Could not write json to file: " + person.getSource());
		}
		writeOutput(person);					
	}

	@Override
	public boolean verify(T entity) {
		Person person = (Person) entity;
		if (person.getNames().isEmpty()) {
			return false;	
		} else return true;
	}
	
	private void writeOutput(Person person) {
		System.out.println("PERSON: " + person.getSource());
		for(String name : person.getNames()) {
			System.out.println("Name: " + name);
		}
		for (String birth : person.getBirthDate()) {
			System.out.println("BirthDate: " + birth);
		}
		for (String death : person.getDeathDate()) {
			System.out.println("DeathDate: " + death);
		}
		for (String child : person.getBornChildren()) {
			System.out.println("Child: " + child);
		}
		for (String spouse : person.getSpouses()) {
			System.out.println("Spouse: " + spouse);
		}
		for (String p : person.getPreceded()) {
			System.out.println("PrecededBy: " + p);
		}
		for (String s : person.getSucceeded()) {
			System.out.println("SucceededBy: " + s);
		}

	}
}
