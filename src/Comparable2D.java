/**
 * The interface for comparable2D
 * @author Shan Ding (dszer); David Thames (davidct)
 * @version 10.22.2017
 *
 * @param <T> generic type
 */
public interface Comparable2D<T> extends Comparable<T> {
	
	/**
	 * compare this objects x position with the given position
	 * 
	 * @param otherX - x coordinate to compare against
	 * @return < 0, 0 , or > 0 depending on the order of the two positions
	 */
	int compareX(int otherX);
	
	/**
	 * compare this objects y position with the given position
	 * 
	 * @param otherY - y coordinate to compare against
	 * @return < 0, 0 , or > 0 depending on the order of the two positions
	 */
	int compareY(int otherY);
	
	/**
	 * Compare x and y coordinates to another 2D comparable object
	 * 
	 * @param obj - object to compare with this object
	 * @return direction based on x & y of both objects
	 */
	Direction compare2D(T o);
	
	/**
	 * Compare x and y coordinates
	 * 
	 * @param otherX - x coordinate to compare against
 	 * @param otherY - y coordinate to compare against
	 * @return direction from the point (otherX, otherY)
	 */
	Direction compare2D(int otherX, int otherY);
	
	
	/**
	 * Check if objects are at the same position
	 * 
	 * @param o - object to compare against
	 * @return true if same position, false if not
	 */
	boolean equals2D(T o);
	
}
