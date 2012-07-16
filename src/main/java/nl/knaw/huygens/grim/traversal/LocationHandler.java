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
package nl.knaw.huygens.grim.traversal;

import java.io.BufferedWriter;
import java.io.FileWriter;
import org.codehaus.jackson.map.ObjectMapper;

import nl.knaw.huygens.grim.model.Entity;
import nl.knaw.huygens.grim.model.Location;

public class LocationHandler<T extends Entity> implements IActionHandler<T> {
	ObjectMapper mapper = new ObjectMapper();
	
	
	@Override
	public void act(T entity) {
		Location location = (Location) entity;
		try {
			String fileName = "data/json/locations/" + location.getSource().replace("http://live.dbpedia.org/resource/", "dbpedia_") + ".json";
			BufferedWriter out = new BufferedWriter(new FileWriter(fileName));
			out.write(mapper.writeValueAsString(location));
			out.close();
		} catch (Exception e) {
			throw new RuntimeException("Could not write json to file: " + location.getSource());
		}
		System.out.println("LOCATION: " + location.getSource());
		for(String name : location.getNames()) {
			System.out.println("Name: " + name);
		}
		System.out.println("Continent: " + location.getContinent());			
		System.out.println("Longitude: " + location.getLongitude());			
		System.out.println("Latitude: " + location.getLatitude());			
		System.out.println("StartYear: " + location.getFoundingDate());			
		System.out.println("EndYear: " + location.getDissolutionDate());	
		for (String p : location.getPreceded()) {
			System.out.println("PrecededBy: " + p);
		}
		for (String s : location.getSucceeded()) {
			System.out.println("SucceededBy: " + s);
		}					
	}

	@Override
	public boolean verify(T entity) {
		Location location = (Location) entity;
		if (location.getNames().isEmpty()) {
			return false;	
		} else return true;
	}
}
