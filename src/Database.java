import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Database will parse the inpur file and receive the commands 
 * and call functions from both BST and Quadtree 
 * @author Shan Ding (dszero); David Thames (davidct)
 * @version 10.16.2017
 *
 */
public class Database 
{
	private File file;
	private PRQuadTree<Point> quadtree;
	private BST<Point> bst;

	/**
	 * initialize the constructor of the database
	 * @param file will be passed in and parsed
	 */
	public Database(File file, PRQuadTree<Point> quadtree, BST<Point> bst)
	{
		this.file = file;		
		this.quadtree = quadtree;
		this.bst = bst;
	}
	
	/**
	 * parse file and execute all the valid commands in the files
	 * @throws FileNotFoundException if file is not found 
	 */
	public void parse() throws FileNotFoundException
	{
		Scanner scanner = new Scanner(file);
		
		while (scanner.hasNextLine())
		{
			String[] line = parseLine(scanner.nextLine());
			
			//check if the array is null, if 
			if (line != null)
			{
				if (line[0].equals("insert"))//Point Rejected: (r_r, -1, -20)
				{
					String name = line[1];
					int x = Integer.parseInt(line[2]);
					int y = Integer.parseInt(line[3]);
					String pointInfo = "(" + name + ", " + x + ", " + y +")";
					String result = null;
					Point point = new Point(name, x, y);
					
					if (quadtree.insert(point) && bst.insert(point))
					{
						result = "Point Inserted: " + pointInfo;
					}
					else//if it is a invalid point
					{
						result = "Point Rejected: " + pointInfo;
					}
					System.out.println(result);
				}
				else if (line[0].equals("duplicates"))
				{
					quadtree.duplicates(); //Duplicate Points:
				}
				else if (line[0].equals("dump"))
				{
					bst.dump();
					quadtree.dump();
				}
				else if (line[0].equals("search"))//search by name
				{
					bst.search(line[1]);//Point Found: (r_r, 1, 20)
					//Point Not Found: r_r
				}
				else if (line[0].equals("remove") && line.length == 2)//remove by name
				{
					Point point = bst.remove(name);
					if (point != null)
					{
						quadtree.remove(point);//make sure bst and quadtree remove the same point
					}
					else
					{
						System.out.println("Point Rejected: " + name;
					}					
				}
				else if (line[0].equals("remove") && line.length == 3)
				{
					int x = Integer.parseInt(line[1]);
					int y = Integer.parseInt(line[2]);
					Point point = quadtree.remove(x, y);
					if (point != null)
					{
						bst.remove(point);//make sure bst and quadtree remove the same point
					}
					else
					{
						System.out.println("Point Rejected: (" 
											+ x + ", " + y + ")");
					}
					
				}
				else //region search
				{
					
				}
			}
		}
		scanner.close();
	}
	


	/**
	 * parse a line. if line is not empty,
	 * return string array contains all the words
	 * of the line
	 * @param line will be sent from scanner
	 * @return string array
	 */
	public String[] parseLine(String line)
	{
		String[] splitted = null;
		if (!line.trim().equals(""))
		{
			splitted = line.split("\\s+");
		}
		return splitted;
	}
}
