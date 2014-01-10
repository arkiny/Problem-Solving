package p903_LinkedList;


/**
 * 
 * @author Arkiny
 *
 * @param <E>
 */

public class SinglyLinkedList<E> implements ILinkedList<E> {

	private SinglyNode<E> head;
	private SinglyNode<E> tail;
	private int size=-1;
	
	/**
	 * Constructor of SinglyLinkedList
	 */
	
	public SinglyLinkedList(){
		head = new SinglyNode<E>(null);
		head.next=null;
		size=0;
	}
	
	@Override
	public void add(E data) {
		if (isEmpty()){
			head = new SinglyNode<E>(data);
			tail=head;
			size++;
		}
		else{
		SinglyNode<E> temp = new SinglyNode<E>(data);
		temp.next=head;
		head=temp;
		size++;
		}
	}

	@Override
	public void add(int pos, E data) throws Exception {	
		if (pos < 0 || pos > size-1)
			throw new Exception("No such index");
		
		SinglyNode<E> addedNode = new SinglyNode<E>(data);
		
		if (pos == 0){
			this.add(data);
			return;
		}
//		if (pos == 0){
//			addedNode.next=head;
//			head=addedNode;
//			size++;
//		}
		
//		else if (pos == 0){
//			SinglyNode<E> temp = head;
//			addedNode.next=temp;
//			head=addedNode;
//			size++;
//		}
//		else if (pos == size-1){
//			this.add(data);
//			return;
//		}
		else{
			SinglyNode<E> current = head;
			SinglyNode<E> pred = new SinglyNode<E>(null);
			for (int i = 0; i < pos; i++){
				pred=current;
				current=current.next;
			}
			pred.next=addedNode;
			addedNode.next = current;
			size++;
		}
	}
	
//	//returns the Node at index pos -1 
//	private SinglyNode<E> findPredecessorByIndex(int pos){
//		SinglyNode<E> current = head;
//		int count = 0;
//		while (count < pos){
//			current = current.next;
//			++count;
//		}
//		return current;
//	}

	@Override
	public boolean contains(E data) {
		SinglyNode<E> current = head;
		
		while (current.next != null){
			
			if (current.data == null){
				if (data == null)
					return true;
			}
			else
			{
				if (current.data.equals(data))
					return true;
			}
			current = current.next;
		}
		if (current.data.equals(data))
			return true;
		else
			return false;
	}

	@Override
	public E get(int pos) throws Exception {
		if (pos < 0 || pos >= size) throw new Exception("out of bounds");
		SinglyNode<E> current = head;
		for (int i = 0; i < pos; i++){
			current=current.next;
		}
		return current.data;
	}

	@Override
	public int indexOf(E data) throws Exception {
		if (!contains(data)) throw new Exception("not found");
		int count = 0;
		SinglyNode<E> current = head;
		while ((current!=null) && !(current.data.equals(data))){
			++count;
			current = current.next;
		}
		return count;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public void remove(E data) throws Exception {
		if (!contains(data)) throw new Exception("not found");
		
		SinglyNode<E> current = head;
		if (current.data.equals(data)){
			head.next = current.next;
			size -= 1;
			return;
		}
		
		while((current.next!=null)&&(!current.next.data.equals(data))){
			current=current.next;
		}
				
		if (current.next == tail){
			current.next=null;
			tail = current;
			size -= 1;
		}
		else
		{
			current.next = current.next.next;
			size -= 1;
		}
		
				
	}


	@Override
	public E set(int pos, E data) throws Exception {
		if (pos < 0 || pos >= size) throw new Exception("out of bounds");
				
		SinglyNode<E> current = head;
		for (int i = 0; i<pos; i++){
			current=current.next;
		}
		E ret = current.data;
		current.data = data;
		return ret;
	}

	@Override
	public int size() {
		if (size == 0){
			return 0;
		}
		return size;
	}
	
	public String toString(){
		String ret = "";
		SinglyNode<E> current = head;
		if (size == 0){
			return "<EMPTY>";
		}
		else{
			ret=current.data.toString();
			
			while(current.next!=null){
			current=current.next;
			ret= ret+" "+current.data.toString();
			}
		}
		return ret;
	}
}