package graphs;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.jupiter.api.Test;

class TestVertex {

	private Vertex<String> vertex;
	private static final String TEST = "test";
	
	
	private void setupScenario1() {
		
	}
	
	private void setupScenario2() {
		this.vertex = new Vertex<String>(TEST);
	}
	
	@Test
	public void testVertex() {
		setupScenario1();
		this.vertex = new Vertex<String>(TEST);
		if(!vertex.getValue().equals(TEST)) {
			fail();
		}
	}
	
	@Test
	public void testGetters() {
		setupScenario2();
		assertTrue("Value attribute hasn't been added correctly", vertex.getValue().equals(TEST));
		assertTrue("Color attribute hasn't been added correctly", vertex.getColor() == Vertex.WHITE);
		assertTrue("Distance attribute hasn't been added correctly", vertex.getD() == 0.0);
		assertTrue("Final distance attribute hasn't been added correctly", vertex.getF() == 0);
		assertTrue("Index attribute hasn't been added correctly", vertex.getIndex() == 0);
		assertTrue("Pred attribute hasn't been added correctly", vertex.getPred() == null);
	}
	
	@Test
	public void testSetters() {
		setupScenario2();
		this.vertex.setColor(Vertex.BLACK);
		this.vertex.setD(1D);
		this.vertex.setF(1);
		this.vertex.setIndex(1);
		String testPred = "pred";
		Vertex<String> pred = new Vertex<>(testPred);
		this.vertex.setPred(pred);
		assertTrue(vertex.getValue().equals(TEST));
		assertTrue(vertex.getColor() == Vertex.BLACK);
		assertTrue(vertex.getD() == 1D);
		assertTrue(vertex.getF() == 1);
		assertTrue(vertex.getIndex() == 1);
		assertTrue(vertex.getPred() == pred);
	}
	
	@Test
	public void testCompareTo1() {
		setupScenario1();
		String test = "test";
		Vertex<String> vertex1 = new Vertex<String>(test);
		Vertex<String> vertex2 = new Vertex<String>(test);
		assertTrue("The comparison went wrong" , vertex1.compareTo(vertex2) == 0);
	}
	
	@Test
	public void testCompareTo2() {
		setupScenario1();
		String test1 = "test1";
		String test2 = "test2";
		Vertex<String> vertex1 = new Vertex<String>(test1);
		Vertex<String> vertex2 = new Vertex<String>(test2);
		assertTrue("The comparison went wrong" , vertex1.compareTo(vertex2) != 0);
	}
	
}
