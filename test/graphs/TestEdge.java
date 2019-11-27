package graphs;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

class TestEdge {

	private Edge<String> edge;
	private static final Vertex<String> SOURCE = new Vertex<>("source");
	private static final Vertex<String> DEST = new Vertex<>("dest");
	
	private void setupScenario1() {
		
	}
	
	private void setupScenario2() {
		this.edge = new Edge<String>(SOURCE, DEST);
	}
	
	private void setupScenario3() {
		double weight = 2D;
		this.edge = new Edge<String>(SOURCE, DEST, weight);
	}
	
	@Test
	public void testEdge1() {
		setupScenario1();
		this.edge = new Edge<String>(SOURCE, DEST);
		assertNotNull("The source vertex has not been assigned", edge.getSource());
		assertNotNull("The destination vertex has not been assigned", edge.getDestination());
		assertTrue("The destination vertex has not been correctly assigned", edge.getDestination().equals(DEST));
		assertTrue("The source vertex has not been correctly assigned", edge.getSource().equals(SOURCE));
		assertTrue("The edge has an incorrect weight", edge.getWeight() == 1D);
	}
	
	@Test
	public void testEdge2() {
		setupScenario1();
		this.edge = new Edge<String>(SOURCE, DEST, 2D);
		assertNotNull("The source vertex has not been assigned", edge.getSource());
		assertNotNull("The destination vertex has not been assigned", edge.getDestination());
		assertTrue("The destination vertex has not been correctly assigned", edge.getDestination().equals(DEST));
		assertTrue("The source vertex has not been correctly assigned", edge.getSource().equals(SOURCE));
		assertTrue("The edge has an incorrect weight", edge.getWeight() == 2D);
	}
	
	@Test
	public void testGetters1() {
		setupScenario2();
		assertTrue("The destination vertex has not been correctly assigned", edge.getDestination().equals(DEST));
		assertTrue("The source vertex has not been correctly assigned", edge.getSource().equals(SOURCE));
		assertTrue("The edge has an incorrect weight", edge.getWeight() == 1D);
	}
	
	@Test
	public void testGetters2() {
		setupScenario3();
		assertTrue("The destination vertex has not been correctly assigned", edge.getDestination().equals(DEST));
		assertTrue("The source vertex has not been correctly assigned", edge.getSource().equals(SOURCE));
		assertTrue("The edge has an incorrect weight", edge.getWeight() == 2D);
	}
	
	@Test
	public void testSetters() {
		setupScenario2();
		this.edge.setWeight(2D);
		assertTrue("An incorrect value has been assigned to weight attribute", edge.getWeight() == 2D);
	}

}
