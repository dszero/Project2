import student.TestCase;

public class DLinkedListTest extends TestCase{
	/**
	 * initialize variables
	 */
	private DLinkedList list;
	private DLinkedList list1;
	private DLinkedList list2;
	private Point point1;
	private Point point2;
	private Point point3;
	private Point point4;

	/**
	 * setup before testing
	 */
	public void setUp()
	{
		list = new DLinkedList();
		list1 = new DLinkedList();
		list2 = new DLinkedList();
		point1 = new Point("Jim", 4, 6);
		point2 = new Point("Jack", 6, 9);
		point3 = new Point("kick", 4, 6);
		point4 = new Point("Yeah", 12, 3);		
	}
	
	/**
	 * test for adding and removing methods
	 */
	public void testForAddingAndRemoving()
	{
		//three different points
		assertTrue(list.add(point1));
		list.add(point2);
		list.add(point3);
		assertFalse(list.add(point1));
		assertFalse(list.add(point4));
		assertFalse(list.getIdenticalPoints());
		assertEquals(3, list.getLIstSize());
		//remove with x = 4, y = 6
		assertTrue(list.remove(4, 6));
		assertEquals(2, list.getLIstSize());
		//remove with name "Jack"
		assertFalse(list.remove("Kick"));
		assertTrue(list.remove("Jack"));
		assertEquals(1, list.getLIstSize());
		
		//five identical points 
		list1.add(point1);
		list1.add(point1);
		list1.add(point1);
		list1.add(point1);
		list1.add(point1);
		assertFalse(list1.add(point4));
		assertTrue(list1.add(point1));
		assertEquals(6, list1.getLIstSize());
		assertTrue(list1.getIdenticalPoints());
		//remove wirh name "Jim"
		assertTrue(list1.remove("Jim"));
		assertEquals(0, list1.getLIstSize());
		
		//four location-identical and one distinct points
		list2.add(point1);
		assertTrue(list2.add(point3));
		list2.add(point1);
		assertFalse(list2.add(point2));
		assertTrue(list2.getIdenticalPoints());
		list2.add(point3);
		assertEquals(4, list2.getLIstSize());
		//remove nodes with x = 4 y = 6 
		assertFalse(list2.remove(3, 5));
		assertTrue(list2.remove(4, 6));
		assertEquals(0, list2.getLIstSize());
		
		
		
		Node current = list1.head.next();
		for (int i = 0; i < list1.getLIstSize(); i++)
		{
			System.out.println(current.point().getName());
			current = current.next();
		}
		
		
	}


	

}
