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
package nl.knaw.huygens.grim.reaper;

import java.util.List;
import nl.knaw.huygens.grim.model.Location;
import nl.knaw.huygens.grim.traversal.DFTraversal;
import nl.knaw.huygens.grim.traversal.ITraversal;

public class GrimReaper {
	
	private final static String SERVICE = "http://live.dbpedia.org";
	private final static String RESOURCE_NS = "http://live.dbpedia.org/resource/";
		
	public static void main(String[] args) {
		ITraversal<Location> traversal = new DFTraversal<Location>(RESOURCE_NS, SERVICE, Location.class);
		List<Location> visited = traversal.run("Roman_Kingdom");
		System.out.println("Done! Visited: " + visited.size() + " entities.");
	}
	
}
