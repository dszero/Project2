import junit.framework.TestCase;

public class PointTest extends TestCase {
	private Point point;
	
	/**
	 * Setup the initial point object(s)
	 */
	public void setUp() {
		point = new Point("test", 56, 34);
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
		
	}
	
	/**
	 * test the CompareX method
	 */
	public void testCompareX() {
		
	}
	
	/**
	 * test the CompareY method
	 */
	public void testCompareY() {
		
	}
	
	/**
	 * test the Compare2D method
	 */
	public void testCompare2D() {
		
	}
	
	/**
	 * test the Compare2D method (with x and y inputs)
	 */
	public void testCompare2DXY() {
		
	}
	
	/**
	 * test the Equals2D method
	 */
	public void testEquals2D() {
		
	}
	
	/**
	 * test the equals method
	 */
	public void testEquals() {
		
	}
}
