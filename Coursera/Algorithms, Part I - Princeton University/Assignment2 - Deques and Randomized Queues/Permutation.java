import java.util.Iterator;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;

public class Permutation {

	public static void main(String[] args) {

		RandomizedQueue<String> r = new RandomizedQueue<>();

		int k = Integer.parseInt(args[0]);

		while (!StdIn.isEmpty()) {
			String item = StdIn.readString();
			r.enqueue(item);
		}

		Iterator<String> iterator = r.iterator();

		int count = 0;
		while (count < k) {
			StdOut.println(iterator.next());
			count++;
		}
	}
}
