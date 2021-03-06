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
package nl.knaw.huygens.grim.rdf;

import java.io.InputStream;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.ResIterator;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.util.FileManager;

public class RDFHandler {

	public Resource getSubject(String uri) {
		Model model = buildModel(uri);
		return model.getResource(uri);		
	}
	
	public ResIterator getSubjectsWithProperty(PropertyConversionWrapper p, String uri) {
		Model model = buildModel(uri);
		return model.listSubjectsWithProperty(p.getProperty());		
	}

	private Model buildModel(String uri) {
		Model model = ModelFactory.createDefaultModel();	
		try {
			InputStream in = FileManager.get().open(uri);
			if (in == null) {
			    throw new IllegalArgumentException("Uri: " + uri + " not found");
			}
			model.read(in, null);
		} catch (Exception e) {
		    System.out.println("Uri: " + uri + " invalid.");	
		}
		return model;
	}	
}
