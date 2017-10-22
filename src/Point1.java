import java.io.File;
import java.io.FileNotFoundException;
/**
 * Main class
 * @author Shan Ding (dszer); David Thames (davidct)
 * @version 10.22.2017
 */

public class Point1 {
	
	/**
	 * main class
	 * @param fileName name of the commands file
	 * @throws FileNotFoundException
	 * 					throws an exception if a file 
	 * 					with the file name is not found
	 */
	public static void main(String[] args) throws FileNotFoundException
	{
		if (args[0] != null)
		{
			File file = new File(args[0]);
			BST<Point> bst = new BST<Point>();
			PRQuadTree<Point> quadtree = new PRQuadTree<Point>(0, 1024, 0, 1024);
			Database database = new Database(file, quadtree, bst);
			database.parse();
		}
		else
		{
			System.out.println("Invalid File Name");
		}
		
	}

}



//On my honor:
//
//- I have not used source code obtained from another student,
//or any other unauthorized source, either modified or
//unmodified.
//
//- All source code and documentation used in my program is
//either my original work, or was derived by me from the
//source code published in the textbook for this course.
//
//- I have not discussed coding details about this project with
//anyone other than my partner (in the case of a joint
//submission), instructor, ACM/UPE tutors or the TAs assigned
//to this course. I understand that I may discuss the concepts
//of this program with other students, and that another student
//may help me debug my program so long as neither of us writes
//anything during the discussion or modifies any computer file
//during the discussion. I have violated neither the spirit nor
//letter of this restriction.
