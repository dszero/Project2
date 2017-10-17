
public interface Comparable2D<T> {
	
	/**
	 * Get the x position of this object
	 * 
	 * @return x position
	 */
	int getX();
	
	/**
	 * Get the y position of this object
	 * 
	 * @return y position
	 */
	int getY();
	
	
	/**
	 * Compare to another 2D comparable object
	 * 
	 * @param obj - object to compare with this object
	 * @return direction based on x & y of both objects
	 */
	Direction compareTo(T o);
	
	/**
	 * Compare x and y coordinates
	 * 
	 * @param otherX - x coordinate to compare against
 	 * @param otherY - y coordinate to compare against
	 * @return direction from the point (otherX, otherY)
	 */
	Direction compareTo(int otherX, int otherY);
}
