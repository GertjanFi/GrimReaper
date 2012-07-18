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

import static org.mockito.Mockito.*;

import nl.knaw.huygens.grim.model.EntityMock;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.google.common.collect.Lists;

public class DFTraversalTest {

	@Mock
	private TraversalActor<EntityMock> mockedTraversalActor;
	private DFTraversal<EntityMock> traverse;

	@Before
	public void beforeTest() {
	    MockitoAnnotations.initMocks(this);
	    EntityMock trunk = new EntityMock();
	    trunk.setName("trunk"); trunk.setChildren(Lists.newArrayList("branche1", "branche2"));
	    EntityMock branche1 = new EntityMock();
	    branche1.setName("branche1"); branche1.setChildren(Lists.newArrayList("leaf1", "leaf2"));
	    EntityMock branche2 = new EntityMock(); branche2.setName("branche2");
	    EntityMock leaf1 = new EntityMock(); leaf1.setName("leaf1");
	    EntityMock leaf2 = new EntityMock(); leaf2.setName("leaf2");
	    
	    when(mockedTraversalActor.act("trunk")).thenReturn(trunk);
	    when(mockedTraversalActor.act("branche1")).thenReturn(branche1);
	    when(mockedTraversalActor.act("branche2")).thenReturn(branche2);
	    when(mockedTraversalActor.act("leaf1")).thenReturn(leaf1);
	    when(mockedTraversalActor.act("leaf2")).thenReturn(leaf2);
	    traverse = new DFTraversal<EntityMock>("http://somewehere.org/outthere", "http://some.service/", EntityMock.class, mockedTraversalActor);
	}
	
	@Test
	public void testRun() {
		traverse.run("trunk");
		verify(mockedTraversalActor).act("trunk");		
		verify(mockedTraversalActor).act("branche1");		
		verify(mockedTraversalActor).act("branche2");		
		verify(mockedTraversalActor).act("leaf1");		
		verify(mockedTraversalActor).act("leaf2");
		
		for (String name : traverse.getVisited()) {
			System.out.println(name);			
		}
	}
}
