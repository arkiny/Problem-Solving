package p903_LinkedList;

/**
 * The linked list should maintain a "head" pointer that points to the FIRST element on the list.
 * The head should be "null" when the list is empty. 
 * The last NODE on the list should have it's next pointer set to "null".
 * 
 * @author smitra
 * @param <E>
 */
public interface ILinkedList<E> 
{
	/**
	 * Adds the parameter to the beginning of the list
	 * @param data This is the data that has to be added to the list
	 */
	public void add(E data);

	/**
	 * Inserts the parameter to the at the specified position in the list.
	 * Note that it throws an Exception if the position is non-existent
	 * (for example, if you want to insert at location 3 in an empty list). 
	 * Note that the first element is considered to be at index position 0.
	 * The exception message is "No such index" 
	 * @param pos index at which the element should be inserted
	 * @param data element to be inserted
	 */
	public void add(int pos, E data) throws Exception; 

	/**
	 * Returns true if the list contains the specified element. 
	 * Returns false otherwise.
	 * @param data Element to be searched in list
	 * @return true if list contains the element
	 */
	public boolean contains(E data);
	
	/**
	 * Gets the element at the specified position
	 * Note that it throws an Exception if the position is non-existent
	 * (for example, if you want to insert at location 3 in an empty list). 
	 * Note that the first element is considered to be at index position 0. 
	 * The exception message is "out of bounds"
	 * @param pos index of the element to be returned
	 * @return the element at the specified position
	 */
	public E get(int pos) throws Exception;
	
	/**
	 * Finds the index of the first occurrence of the specified element. 
	 * Note that it throws an Exception if the element is not found in the list
	 * The exception message is "not found"
	 * @param data Element to be searched
	 * @return index of element to be searched. 
	 */
	public int indexOf(E data) throws Exception;
	
	/**
	 * Returns true if list is empty (i.e. head is null)
	 * Returns false otherwise
	 * @return true if list is empty false otherwise
	 */
	public boolean isEmpty();
	
	/**
	 * Removes the first occurring node corresponding to the data element from the list. 
	 * Note that it throws an Exception if the element is not found in the list
	 * The exception message is "not found"
	 * @param data Element to be removed
	 */
	public void remove(E data) throws Exception;
	
	/**
	 * Replaces the element at the specified position with the provided data
	 * Note that it throws an Exception if the position is non-existent
	 * (for example, if you want to insert at location 3 in an empty list). 
	 * Note that the first element is considered to be at index position 0. 
	 * The exception message is "out of bounds"
	 * @param pos Index of the element to be replaced
	 * @param data element to be stored at the specified position
	 * @return the element previously at the specified position
	 */
	public E set(int pos, E data) throws Exception;
	
	/**
	 * Size of the list
	 * Empty list has size 0.
	 * @return the number of elements in the list	
	 */
	public int size();

	
	/**
	 * ALSO, IMPLEMENT THE public String toString() method!
	 * print <EMPTY> when list is empty.
	 */

}
