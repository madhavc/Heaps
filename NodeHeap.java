/**
 *  @author Madhav Chhura
 */
import java.util.LinkedList;
import java.util.Queue;

/**
 * This is an implementation of a heap using nodes.
 *
 * This implementation will accept a generic object that can be used to
 * define an ordering of the items contained in this heap, other than the
 * objects' default compareTo method (if they are comparable).
 *
 * @param <V>
 *            The type of objects in this heap
 */
@SuppressWarnings("unchecked")
public class NodeHeap <V extends Comparable<V>> implements Heap<V> {
	
	int count;
	int lastIndex = count;
	Node lastNode;
	Node root = null;
	private MODE mode;
	
	/**
	   * Adds an entry to the heap, sorting the heap for priority afterwards.
	   * @param entry of generic type the entry to be added.
	   */
	@Override
	public void add(V value) {
		if(root == null)
		{
			root = new Node(value);
			lastNode = root;
			count++;
		}
		else{
			String binary = Integer.toBinaryString(count + 1);
			Node node = root;
			Node last = lastNode;
			for(int i = 1; i < binary.length(); i++)
			{
				if(binary.charAt(i) == '1'){
					if(node.right != null){
						node = node.right;

					}
					else{
						node.right = new Node(value);
						node = node.right;
						node.parent = last;
					}
				}
				else{
					if(node.left != null){
						node = node.left;
					}
					else{
						node.left = new Node(value);
						node = node.left;
						node.parent = last;
					}
					
				}
			}
			node.prev = lastNode;
			lastNode = node;
			count++;
			siftUp(node);
		}
	}
	
	/**
     * Removes the top of the heap (highest priority item, root)
     * @return The top of the heap
     */
	@Override
	public V remove() {
		Object result = null;
		if(root == null || count == 0)
			return null;
		else{
			result = root.nodeValue;
			root.nodeValue = lastNode.nodeValue;
			lastNode = lastNode.prev;
			--count;
			siftDown(root);
			if(count == 0)
				root = null;
			return (V) result;
		}
	}
    /**
     * Creates a new heap using an array
     * @param array heap values in the array.
     */
	public void fromArray(V[] array) {
		if(array[0] == null) 
			throw new RuntimeException("Array is empty!");
		for(int i = 0; i < array.length; i++){
			add(array[i]);
		}
	}
	/**
     * Converts a heap into an array of generic type.
     * @return array of heap values.
     */
	@Override
	public V[] toArray() {
		if(isEmpty()) throw new RuntimeException("Tree is empty!");
		V[] result = (V[]) java.lang.reflect.Array.newInstance(root.nodeValue.getClass(),count);
		
		if(count < 0 || root == null)
			return result;
		Queue<Node> q = new LinkedList<Node>();
		q.add(root);
		int i = 0;
		while(!q.isEmpty() && i < result.length){
			Node n = q.peek();
			result[i] = n.nodeValue;
			i++;
			q.remove();
			if(n.left != null)
				q.add(n.left);
			if(n.right != null)
				q.add(n.right);	
		}
		return result;

	}

	/**
	 * @return true if there are no items in the Heap.
	 */
	private boolean isEmpty() {
		return (count == 0);
	}

	/**
     * "siftUp" checks if child obeys heap property given parents value, if
     * not we swap them and compare the element with its new parents as before.
     * 
     * @param index Item to check
     */
 
	private void siftUp(Node index) {
		if(index == root)
			return;
		if(compareValues(index, index.parent))
		{
			swapValues(index, index.parent);
			siftUp(index.parent);
		}
	}
	
	/**
     * Pushes an item down if it's larger than one of its children
     * @param index The item to check
     */
	private void siftDown(Node index)
	{
		if(index.left == null)
			return;               
		if(index.right == null)      
		{
			if(compareValues(index.left, index))
				this.swapValues(index, index.left);
			return;
		}
		else                      
		{
			if((compareValues(index.left,index)) || compareValues(index.right,index))
			{
				Node temp = this.max(index.left, index.right);   
				this.swapValues(index, temp);
				this.siftDown(temp);
			}
		}
	}
	
	/**
     * This method is used to get sorted array of the Heap.
     * It uses Heap Sort for sorting purpose. 
     * @return Sorted array of heap values.
     */
	public V[] getSortedContents() {
	    V[] result = this.toArray();
	    this.heapSort(result);
	    return result;
	}
	
	/**
     * This method is a helper function for the getSortedContents
     * method. First the method calls heapify to break the array 
     * in half. The method then swaps the values of the first and 
     * last index of the array for each value of the array.
     * Then calls shift down.
     * 
     * @param Array of generic Objects. 
     */
	public void heapSort(V[] array){
		heapify(array);

		int last = array.length - 1;
		while(last > 0){
			V temp = array[last];
			array[last] = array[0];
			array[0] = temp;
			last--;
			shiftDown(array, 0 , last);
		}
	}
	
	/**
     * This method is a helper function for the heapSort
     * method. The method breaks the array in half and then
     * performs shiftDown on each iteration of the array.
     * Then calls shift down.
     * 
     * @param Array of generic Objects. 
     */
	public void heapify(V[] array){
		int length = array.length;
		int start = (length-2)/2;
		while(start >= 0){
			shiftDown(array, start, length -1);
			start--;	
		}
	}
	/****
     * Internal method to shift down in the array.
     * 
     * @param array is the array in which the shift down 
     * method will applied too. 
     * @param start is the starting index of the array from 
     * where to shift down from. 
     * @param end is the ending index of the array where to 
     * stop shift down. 
     */
	public void shiftDown(V[] array, int start, int end){
		int nodeIndex = start;
		
		while( (nodeIndex *2 + 1) <= end){
			int leftChild = nodeIndex * 2 + 1;
			int rightChild = nodeIndex * 2 + 2;
			
			int swap = nodeIndex;

			if(array[swap]
					.compareTo(array[leftChild]) < 0){
				swap = leftChild;
			}
			if( (rightChild <= end)  && (array[swap].compareTo(array[rightChild]) < 0) ){
				swap = rightChild;
			}
			if(swap != nodeIndex){
				V temp = array[nodeIndex];
				array[nodeIndex] = array[swap];
				array[swap] = temp;
			}else{
				return;
			}
		}
	}
	/**
     * This method is used to compares to Nodes depending 
     * on the {@code mode} which is set by the user. 
     *
     * @param ch2Index is the node which is being compared to
     * @param c1Index.
     * 
     * @return returns true if the mode is MAX and ch2Index is 
     * bigger then ch1Index.
     * 
     * @return returns true if the mode is MIN and ch2Index is 
     * smaller then ch1Index.
     * 
     */
	private boolean compareValues(Node ch2Index, Node ch1Index) {
		if (mode == Heap.MODE.MAX) {
			return (ch2Index.nodeValue.compareTo(ch1Index.nodeValue) > 0);
		} else {
			return (ch2Index.nodeValue.compareTo(ch1Index.nodeValue) < 0);
		}
	}
	
	/**
     * Swap two items from the heap
     * @param index1 The first item to swap
     * @param index2 The second item to swap
     */
	private void swapValues(Node index1, Node index2) {
		Object value = index1.nodeValue;
		index1.nodeValue = index2.nodeValue;
		index2.nodeValue = (V)value;
	}
	
	
	private Node max(Node l, Node r) {
		if(l.nodeValue.compareTo(r.nodeValue) > 0)
			return l;
		else
			return r;
	}
	
	@Override
	/**
	 * @return the mode of the current Heap.
	 */
	public edu.csupomona.cs.cs241.prog_assigmnt_1.Heap.MODE getMode() {
		return mode;
	}

	@Override
	/**
	 * This method is used to set the mode of the Heap 
	 * Only two options MAX or MIN
     * @param mode MAX or MIN
	 */
	public void setMode(edu.csupomona.cs.cs241.prog_assigmnt_1.Heap.MODE mod) {
		mode = mod;
	}
	
	/** 
     * Defines a linked node, for use by a NodeHeap implementation.
     * 
     * This implementation uses "K" for key and "V" to hold the information 
     * stored in the list and next to find what is the next value in the list.
     */
	private class Node 
	{
		protected Node left, right, prev, parent;
		
		protected  V nodeValue;
		
		/**
	     * Create a Node.
	     * @param nodeValue Value of the node.
	     */
		public Node(V value) 
		{
			nodeValue = value;
			left = null;
			right = null;
			prev = null;
			parent = null;
		}
	}

	public NodeHeap(){
		mode = Heap.MODE.MAX;
		root = null;
		lastNode = null;
		count = 0;
	}
}
