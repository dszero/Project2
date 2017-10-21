import java.util.Iterator;
import java.util.Stack;

/**
 * 
 * @author David Thames davidct
 * @version 2017-9-25
 *
 * @param <T>
 *            Type of objects to store in BST
 */
public class BST<T extends Comparable<? super T>> 
        implements Iterable<BST<T>.NodeInfo> {

    /**
     * Node containing data for BST and object to be stored
     * 
     * @author davidct
     *
     */
    private class BinaryNode {
        /**
         * Create new node with an object of type T to store
         * 
         * @param elem
         *            - object to store
         */
        public BinaryNode(T elem) {
            element = elem;
            left = null;
            right = null;
        }

        /**
         * element contained in node
         */
        public T element;

        /**
         * Reference to left node
         */
        public BinaryNode left;

        /**
         * Reference to right node
         */
        public BinaryNode right;
    }

    // Iterators ----------------------------------------

    /**
     * Contains info about node
     *
     */
    public class NodeInfo {
        /**
         * element contained in node
         */
        public T element;
        
        /**
         * depth of node
         */
        public int depth;
        
        /**
         * Create new NodeInfo
         * 
         * @param e - element to add
         * @param d - depth of node
         */
        NodeInfo(T e, int d) {
            element = e;
            depth = d;
        }
        
    }
    
    /**
     * Types of iterators
     */
    public enum Order {
        /**
         * post-order
         */
        POST, 
        
        /**
         * pre-order
         */
        PRE, 
        
        /**
         * in-order
         */
        IN
    }

    /**
     * iterator to iterate through BST
     */
    class BstIterator implements Iterator<NodeInfo> {
        /**
         * Stack containing list of nodes to iterate over
         */
        public Stack<NodeInfo> nodeStack;
                
        private NodeInfo current;
        private Order type;

        /**
         * Create new post order iterator
         */
        public BstIterator() {
            if (root == null) {
                current = null;
            }
            else {
                current = new NodeInfo(root.element, 0);
            }
            nodeStack = new Stack<NodeInfo>();
            type = Order.IN;
            orderHelper(root, 0);
        }

        /**
         * Create new iterator of type t
         * 
         * @param t
         *            - type of iterator
         */
        public BstIterator(Order t) {
            if (root == null) {
                current = null;
            }
            else {
                current = new NodeInfo(root.element, 0);
            }
            nodeStack = new Stack<NodeInfo>();
            type = t;
            orderHelper(root, 0);
        }

        /**
         * Used to add nodes to stack in correct order
         * 
         * @param t
         *            - current binary node
         */
        private void orderHelper(BinaryNode t, int depth) {
            if (t == null) {
                return;
            }
            switch (type) {
                case IN:
                    orderHelper(t.right, depth + 1);
                    nodeStack.addElement(new NodeInfo(t.element, depth));
                    orderHelper(t.left, depth + 1);
                    break;
                case PRE:
                    orderHelper(t.right, depth + 1);
                    orderHelper(t.left, depth + 1);
                    nodeStack.addElement(new NodeInfo(t.element, depth));
                    break;
                default: // POST
                    nodeStack.addElement(new NodeInfo(t.element, depth));
                    orderHelper(t.right, depth + 1);
                    orderHelper(t.left, depth + 1);
                    break;
            }

        }

        /**
         * advance iterator to next element
         * 
         * @return next element
         */
        public NodeInfo next() {
            if (!nodeStack.empty()) {
                current = nodeStack.pop();
            }
            return (current);
        }
        

        /**
         * Determine if next element exists
         * 
         * @return true if next exists false if not
         */
        @Override
        public boolean hasNext() {
            return !nodeStack.empty();
        }

        
    }

    // BST ---------------------------

    private BinaryNode root; // root element of BST

    /**
     * Create new BST
     */
    public BST() {
        root = null;
    }

    /**
     * determine if any elements are contained in this BST
     * 
     * @return true if BST is empty, otherwise false
     */
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * Find element in BST
     * 
     * @param x
     *            - element to find
     * @return return object if found or null if not found
     */
    public T find(T x) {
        return find(x, root);
    }

    /**
     * Find element starting from given root node
     * 
     * @param x - element to find
     * @param sRoot - current root node
     * @return return object if found or null if not found
     */
    private T find(T x, BinaryNode sRoot) {
        if (sRoot == null) {
            return null;
        }
        int compareResult = x.compareTo(sRoot.element);

        if (compareResult < 0) {
            return find(x, sRoot.left);
        }
        else if (compareResult > 0) {
            return find(x, sRoot.right);
        }
        else {
            return sRoot.element; // Match
        }
    }

    /**
     * Insert element into BST
     * 
     * @param x - element to insert
     * @return true if inserted, false if not inserted
     */
    public boolean insert(T x) {
//        if(find(x, root) != null) {
//            return false;
//        }
        root = insert(x, root);
        return true;
    }

    /**
     * Insert element into BST
     *
     * @param x
     *            - element to insert
     * @param sRoot
     *            - current node to insert at
     * @return root node
     */
    private BinaryNode insert(T x, BinaryNode sRoot) {
        if (sRoot == null) {
            BinaryNode node = new BinaryNode(x);
            return node;
        }

        int compareResult = x.compareTo(sRoot.element);

        if (compareResult < 0) {
            sRoot.left = insert(x, sRoot.left);
        }
        else {
            sRoot.right = insert(x, sRoot.right);
        }
        return sRoot;

    }

    /**
     * remove element from BST
     * 
     * @param x
     *            - element to remove
     * @return true if removed, false if not removed
     */
    public boolean remove(T x) {
        if (find(x) == null) {
            return false;
        }
        root = remove(x, root);
        return true;
    }

    /**
     * Remove element from BST under node sRoot
     * 
     * @param x
     *            - element to remove
     * @param sRoot
     *            - node to look for element under
     * @return root node
     */
    private BinaryNode remove(T x, BinaryNode sRoot) {
        if (sRoot == null) {
            return null;
        }

        int compareResult = x.compareTo(sRoot.element);

        if (compareResult < 0) {
            sRoot.left = remove(x, sRoot.left);
        }
        else if (compareResult > 0) {
            sRoot.right = remove(x, sRoot.right);
        }
        else {
            // Remove element
            if (sRoot.left == null && sRoot.right == null) {
                return null; 
            }
            else {
                if (sRoot.left != null) {
                    //shift right node to rightmost node under left node
                    BinaryNode rightmost = sRoot.left;
                    while (rightmost.right != null) {
                        rightmost = rightmost.right;
                    }
                    rightmost.right = sRoot.right;
                    return sRoot.left;
                }
                else {
                    return sRoot.right;
                }
            }
        }
        return sRoot;

    }

    /**
     * remove all elements from BST
     */
    public void clear() {
        root = null;
    }

    

    /**
     * Create a new in order iterator
     */
    @Override
    public Iterator<BST<T>.NodeInfo> iterator() {
        return new BstIterator();
    }

    /**
     * create an iterator of the given type
     * 
     * @param t
     *            - order of iterator
     * @return new iterator object
     */
    public Iterator<BST<T>.NodeInfo> iterator(Order t) {
        return new BstIterator(t);
    }

}
