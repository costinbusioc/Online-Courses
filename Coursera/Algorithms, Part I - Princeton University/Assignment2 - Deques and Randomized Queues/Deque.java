import java.util.Iterator;
import java.util.NoSuchElementException;


public class Deque<Item> implements Iterable<Item> {
	
	private class Node<Item> {
		Item info;
		Node<Item> next, prev;
		
		public Node(Item info) {
			this.info = info;
			this.next = null;
			this.prev = null;
		}
	}
	
	private int size;
	private Node<Item> first, last;
	
	public Deque() {
		size = 0;
		first = null;
		last = null;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public int size() {
		return size;
	}
	
	public void addFirst(Item item) {
		if (item == null)
			throw new IllegalArgumentException();
		
		Node<Item> newNode = new Node<Item>(item);
		
		if (size == 0) {
			first = newNode;
			last = newNode;
		} else {
			newNode.next = first;
			first.prev = newNode;
			first = newNode;
		}
		
		size++;
	}
	
	public void addLast(Item item) {
		if (item == null)
			throw new IllegalArgumentException();
		
		Node<Item> newNode = new Node<Item>(item);
		
		if (size == 0) {
			first = newNode;
		} else {
			last.next = newNode;
			newNode.prev = last;
			
		}
		
		last = newNode;
		size++;
	}
	
	public Item removeFirst() {
		if (size == 0)
			throw new NoSuchElementException();
		
		Item item = first.info;
		
		first = first.next;
		size--;
		
		return item;
	}
	
	public Item removeLast() {
		if (size == 0)
			throw new NoSuchElementException();
		
		Item item = last.info;
		
		last = last.prev;
		last.next = null;
		size--;
		
		return item;
	}
	

	@Override
	public Iterator<Item> iterator() {
		return new ListIterator<Item>(first);
		
	}
	
	private class ListIterator<Item> implements Iterator<Item> {

		private Node<Item> current;
		
		public ListIterator(Node<Item> first) {
			current = first;
		}
		
		public boolean hasNext() {
			return current != null;
		}
		
		public void remove() {
			throw new UnsupportedOperationException();
		}
		
		public Item next() {
			if (!hasNext())
				throw new NoSuchElementException();
			
			Item item = current.info;
			current = current.next;
			
			return item;
		}
		
	}

	public static void main(String[] args) {

		Deque<String> d = new Deque<String>();
        
        d.addFirst("John");
        d.addFirst("Jack");
        d.addLast("MIchael");
        d.addLast("Alex");
        d.addFirst("Tim");
        d.addLast("Morgan");
        d.removeLast();
        d.removeLast();
        d.removeFirst();
        d.removeFirst();
        d.removeLast();
        d.removeLast();
       
        for (String x : d) {
        	System.out.print(x + " ");
        }
        System.out.println();
	}



}