import java.io.File;

import student.TestCase;

public class DatabaseTest extends TestCase{
	
	
	private Database database;
	private File file;
	

	public void setUp()
	{
//		file = new File();
		database = new Database(file);
	}
	
	public void testLineSplitting()
	{
		String empty = "                  	";
		String[] emptyArray = database.parseLine(empty);
		assertEquals(emptyArray, null);
		
		String insert = "insert r_r          -1 -20";
		String[] insertArray = database.parseLine(insert);
		assertEquals(insertArray.length, 4);
		assertEquals(insertArray[0], "insert");
		assertEquals(insertArray[1], "r_r");
		assertEquals(insertArray[2], "-1");
		assertEquals(insertArray[3], "-20");
	}
	
}
