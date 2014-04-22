/**
 * @author Madhav Chhura
 *
 */
public interface Heap<V extends Comparable<V>> {
	
	public static enum MODE {MAX, MIN};

	/**
	  * Adds an entry to the heap, sorting the heap for priority afterwards.
	  * @param entry of generic type the entry to be added.
	  */
	public void add(V value);

    	/**
     	  * Creates a new heap using an array
     	  * @param array heap values in the array.
     	  */
	public void fromArray(V[] array);

	/**
	 * @return the mode of the current Heap.
	 */
	public MODE getMode();
	
	/**
	 * @return
	 */
	public V[] getSortedContents();

	
	/**
     	  * Removes the top of the heap (highest priority item, root)
          * @return The top of the heap
          */
	public V remove();
	
	/**
	 * This method is used to set the mode of the Heap 
	 * Only two options MAX or MIN
         * @param mode MAX or MIN
	 */
	public void setMode(MODE mode);
	
	/**
          * Converts a heap into an array of generic type.
          * @return array of heap values.
          */
	public V[] toArray();

}
