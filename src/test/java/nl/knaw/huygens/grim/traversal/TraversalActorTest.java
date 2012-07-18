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

import nl.knaw.huygens.grim.generator.Reaper;
import nl.knaw.huygens.grim.model.EntityMock;
import nl.knaw.huygens.grim.modelhandlers.EntityMockHandler;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class TraversalActorTest {

	@Mock
	private Reaper<EntityMock> mockedReaper;
	@Mock
	private EntityMockHandler<EntityMock> mockedEntityHandler;
	private TraversalActor<EntityMock> actor;
	private EntityMock entity = new EntityMock();
	
	@Before
	public void beforeTest() {
	    MockitoAnnotations.initMocks(this);	    
	    when(mockedReaper.reap(anyString())).thenReturn(entity);
	    when(mockedEntityHandler.verify(entity)).thenReturn(true);
	    actor = new TraversalActor<EntityMock>("http://somewehere.org/outthere/", "http://some.service/", EntityMock.class);
	    actor.setEntityHandler(mockedEntityHandler);
	    actor.setReaper(mockedReaper);
	}
	
	@Test
	public void testAct() {
		actor.act("someResource");
		verify(mockedReaper).reap("http://somewehere.org/outthere/someResource");
		verify(mockedEntityHandler).verify(entity);
		verify(mockedEntityHandler).write(entity);
	}
}
