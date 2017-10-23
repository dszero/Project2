import junit.framework.TestCase;

/**
 * The junit test class for DLinkedList
 * @author Shan Ding (dszer); David Thames (davidct)
 * @version 10.22.2017
 *
 */

public class DLinkedListTest extends TestCase 
{
	/**
	 * initialize variables
	 */
	private DLinkedList<String> list;


	/**
	 * setup before testing
	 */
	public void setUp()
	{
		list = new DLinkedList<String>();
	}
	
	/**
	 * test the add method
	 */
	public void testAdd() {
		assertFalse(list.add((String) null));
		assertTrue(list.add("test"));
	}
	
	/**
	 * test the size method
	 */
	public void testLength() {
		assertEquals(list.size(), 0);
		list.add("test");
		assertEquals(list.size(), 1);
	}
	
	/**
	 *  test the remove method
	 */
	public void testRemove() {
		assertNull(list.remove("test"));
		
		list.add("test");
		assertNull(list.remove("test1"));
		assertNotNull(list.remove("test"));
		assertEquals(list.size(), 0);
		
		list.add("test1");
		list.add("test2");
		list.add("test3");
		assertNotNull(list.remove("test2"));
		assertEquals(list.size(), 2);
		
		list.add("test4");
		assertNotNull(list.remove("test4"));
		assertEquals(list.size(), 2);
		
	}
	
	/**
	 * test the get method
	 */
	public void testGet() {
		list.add("test1");
		list.add("test2");
		list.add("test3");
		
		assertEquals(list.get(0), "test1");
		assertEquals(list.get(1), "test2");
		assertNull(list.get(3));
		assertEquals(list.get(2), "test3");
		
	}
	
	/**
	 * test the iterator method
	 */
	public void testIterator() {
		list.add("test1");
		list.add("test2");
		list.add("test3");

		int i = 0;
		for (String item : list) {
			assertEquals(item, list.get(i));
			i++;
		}
		assertEquals(list.size(), i);
	}
	
	/**
	 * test the addAll method
	 */
	public void testAddAll() {
		DLinkedList<String> list1 = new DLinkedList<String>();
		list.add("test1");
		list.add("test2");
		list.add("test3");
		
		list1.add("test4");
		list1.add("test5");
		list1.add("test6");
		
		list.addAll(list1);
		
		assertEquals(list.get(0), "test1");
		assertEquals(list.get(1), "test2");
		assertEquals(list.get(2), "test3");
		assertEquals(list.get(3), "test4");
		assertEquals(list.get(4), "test5");
		assertEquals(list.get(5), "test6");
	}
	

	

}
