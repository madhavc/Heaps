/**
 * @author Madhav Chhura
 *
 */
public class HeapTester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	
		NodeHeap <Integer> heap = new NodeHeap<Integer>();
		Integer []array1 = {1,3,4,2,24,453,695,53,23};
		heap.fromArray(array1);
		
		heap.setMode(MODE.MAX);
		heap.add(10);
		heap.add(232);
		heap.add(33);
		heap.add(14);
		heap.add(5);
		heap.add(6);
		heap.add(79);
		heap.add(18);
		heap.add(543);
		 

		System.out.println("Printing out a array with nodes added.");
		Integer[] array= heap.toArray();
		for(int i = 0; i < array.length; i++)
			System.out.print(array[i] + " ");
		
	
		System.out.println("\n\nPrinting out a sorted array.");
		array= heap.getSortedContents();
		for(int i = 0; i < array.length; i++)
			System.out.print(array[i] + " ");
		
		System.out.println("\n\nRemoving the top element from the tree.");
		heap.remove();
		
		System.out.println("\nPrinting out a sorted array.");
	
		array = heap.getSortedContents();
		for(int i = 0; i < array.length; i++)
			System.out.print(array[i] + " ");
		

	}

}
