import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;


public class RandomizedQueue<Item> implements Iterable<Item> {

	private Item[] array;
	private int size;
	private final Random rand;

	public RandomizedQueue() {
		array = (Item[]) new Object[2];
		size = 0;
		rand = new Random();
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public int size() {
		return size;
	}

	public void enqueue(Item item) {
		if (item == null)
			throw new IllegalArgumentException();

		if (size == array.length)
			doubleArray();

		array[size++] = item;
	}

	public Item dequeue() {
		if (isEmpty())
			throw new NoSuchElementException();

		int position = rand.nextInt(size);

		Item item = array[position];

		array[position] = array[size - 1];
		size--;

		if (size < array.length / 4)
			halveArray();

		return item;
	}

	public Item sample() {
		if (isEmpty())
			throw new NoSuchElementException();

		int position = rand.nextInt(size);

		return array[position];
	}

	@Override
	public Iterator<Item> iterator() {
		return new ArrayIterator(rand.nextInt(size));

	}

	private class ArrayIterator<Item> implements Iterator<Item> {

		int current;
		int count;

		public ArrayIterator(int position) {
			current = position;
			count = 0;
		}

		public boolean hasNext() {
			return count < size;
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}

		public Item next() {
			if (!hasNext())
				throw new NoSuchElementException();

			Item item = (Item) array[current % size];
			current++;
			count++;

			return item;
		}

	}

	private void doubleArray() {
		Item[] newArray = (Item[]) new Object[array.length * 2];

		for (int i = 0; i < array.length; i++)
			newArray[i] = array[i];

		array = newArray;
	}

	private void halveArray() {
		Item[] newArray = (Item[]) new Object[array.length / 4];

		for (int i = 0; i < array.length / 4; i++)
			newArray[i] = array[i];

		array = newArray;
	}

	public static void main(String[] args) {
		RandomizedQueue<String> r = new RandomizedQueue<String>();

		r.enqueue("John");
		r.enqueue("Jack");
		r.enqueue("MIchael");
		r.enqueue("Alex");

		r.dequeue();

		for (String x : r) {
			System.out.print(x + " ");
		}
		System.out.println();

		r.dequeue();

		for (String x : r) {
			System.out.print(x + " ");
		}
		System.out.println();

		r.dequeue();

		for (String x : r) {
			System.out.print(x + " ");
		}
		System.out.println();
	}
}
