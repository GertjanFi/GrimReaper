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

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import nl.knaw.huygens.grim.generator.EntityConvertor;
import nl.knaw.huygens.grim.generator.Reaper;
import nl.knaw.huygens.grim.generator.ResourceGenerator;
import nl.knaw.huygens.grim.model.EntityMock;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.hp.hpl.jena.rdf.model.Resource;

public class DFTraversalTest {

	private DFTraversal<EntityMock> traverse;

	@Before
	public void beforeTest() {
	    MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testReap() {
	}
}
