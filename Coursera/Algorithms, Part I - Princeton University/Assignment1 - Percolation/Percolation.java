import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

	private WeightedQuickUnionUF uf;
	private WeightedQuickUnionUF uf2;

	private int size;
	private boolean[][] grid;

	private int top;
	private int bottom;

	private int numberOpen;

	public Percolation(int n) {
		if (n <= 0)
			throw new IllegalArgumentException();

		size = n;
		top = n * n;
		bottom = n * n + 1;

		grid = new boolean[size][size];

		uf = new WeightedQuickUnionUF(size * size + 2);
		uf2 = new WeightedQuickUnionUF(size * size + 1);

		numberOpen = 0;
	}

	public void open(int i, int j) {
		if (!checkIndex(i, j))
			throw new IllegalArgumentException();

		if (!grid[i - 1][j - 1]) {
			grid[i - 1][j - 1] = true;
			numberOpen++;
		}

		if (i == 1) {
			uf.union(j - 1, top);
			uf2.union(j - 1, top);
		}

		if (i == size) {
			uf.union(indexNumber(i, j), bottom);
		}

		if (i > 1 && isOpen(i - 1, j)) {
			uf.union(indexNumber(i, j), indexNumber(i - 1, j));
			uf2.union(indexNumber(i, j), indexNumber(i - 1, j));
		}

		if (i < size && isOpen(i + 1, j)) {
			uf.union(indexNumber(i, j), indexNumber(i + 1, j));
			uf2.union(indexNumber(i, j), indexNumber(i + 1, j));
		}

		if (j > 1 && isOpen(i, j - 1)) {
			uf.union(indexNumber(i, j), indexNumber(i, j - 1));
			uf2.union(indexNumber(i, j), indexNumber(i, j - 1));
		}

		if (j < size && isOpen(i, j + 1)) {
			uf.union(indexNumber(i, j), indexNumber(i, j + 1));
			uf2.union(indexNumber(i, j), indexNumber(i, j + 1));
		}

	}

	public int numberOfOpenSites() {
		return numberOpen;
	}

	private int indexNumber(int i, int j) {
		return size * (i - 1) + j - 1;
	}

	public boolean isOpen(int row, int col) {
		if (checkIndex(row, col) == false)
			throw new IndexOutOfBoundsException();

		return grid[row - 1][col - 1];
	}

	public boolean isFull(int row, int col) {
		if (checkIndex(row, col) == false)
			throw new IndexOutOfBoundsException();

		return uf2.connected(indexNumber(row, col), top);
	}

	public boolean percolates() {
		return uf.connected(top, bottom);
	}

	private boolean checkIndex(int i, int j) {
		if (i < 1 || i > size || j < 1 || j > size)
			return false;

		return true;
	}
}
