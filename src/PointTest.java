import junit.framework.TestCase;

public class PointTest extends TestCase {
	private Point point;
	private Point pointE;
	private Point pointNE1;
	private Point pointNE2;
	private Point pointEXY;
	private Point pointEName;
	
	
	/**
	 * Setup the initial point object(s)
	 */
	public void setUp() {
		point = new Point("test", 56, 34);
		
		pointE = new Point("test", 56, 34);
		pointNE1 = new Point("test23", 36, 4);
		pointNE2 = new Point("test23", 100, 4);
		
		pointEXY = new Point("test345", 56, 34);
		pointEName = new Point("test", 106, 86);
	}
	
	/**
	 * test the getName method
	 */
	public void testGetName() {
		assertEquals("test", point.getName());
	}
	
	/**
	 * test the getX method
	 */
	public void testGetX() {
		assertEquals(56, point.getX());
	}
	
	/**
	 * test the getY method
	 */
	public void testGetY() {
		assertEquals(34, point.getY());
	}
	
	/**
	 * test the CompareTo method
	 */
	public void testCompareTo() {
		assertEquals(point.compareTo(pointE), 0);
		assertTrue(point.compareTo(pointNE1) != 0);
		
		assertEquals(point.compareTo(pointEName), 0);
		assertTrue(point.compareTo(pointEXY) != 0);
	}
	
	/**
	 * test the CompareX method
	 */
	public void testCompareX() {
		assertTrue(point.compareX(106) < 0);
		assertTrue(point.compareX(15) > 0);
		assertTrue(point.compareX(56) == 0);
	}
	
	/**
	 * test the CompareY method
	 */
	public void testCompareY() {
		assertTrue(point.compareY(106) < 0);
		assertTrue(point.compareY(15) > 0);
		assertTrue(point.compareY(34) == 0);
	}
	
	/**
	 * test the Compare2D method
	 */
	public void testCompare2D() {
		assertEquals(Direction.NE, point.compare2D(pointE));
		assertEquals(Direction.SE, point.compare2D(pointNE1));
		assertEquals(Direction.SW, point.compare2D(pointNE2));
		assertEquals(Direction.NW, point.compare2D(pointEName));
	}
	
	/**
	 * test the Compare2D method (with x and y inputs)
	 */
	public void testCompare2DXY() {
		assertEquals(Direction.NE, point.compare2D(56, 34));
		assertEquals(Direction.SE, point.compare2D(36, 4));
		assertEquals(Direction.SW, point.compare2D(100, 4));
		assertEquals(Direction.NW, point.compare2D(106, 86));
	}
	
	/**
	 * test the Equals2D method
	 */
	public void testEquals2D() {
		assertTrue(point.equals2D(pointE));
		assertFalse(point.equals2D(pointNE1));
		
		assertTrue(point.equals2D(pointEXY));
		assertFalse(point.equals2D(pointEName));
	}
	
	/**
	 * test the equals method
	 */
	public void testEquals() {
		assertTrue(point.equals(pointE));
		assertFalse(point.equals(pointNE1));
		
		assertFalse(point.equals(pointEXY));
		assertFalse(point.equals(pointEName));
	}
}
