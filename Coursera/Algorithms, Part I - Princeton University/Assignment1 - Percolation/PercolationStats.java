import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
	
	private double[] fractions;
	private int trials;
	
	public PercolationStats(int N, int T) {
		
		trials = N;
		
		if (N <= 0 || T <= 0) 
			 throw new IllegalArgumentException("Given N <= 0 || T <= 0");
		
		fractions = new double[trials];
		
		for (int i = 0; i < trials; i++) {
			int number = 0;
			
			Percolation p = new Percolation(N);
			while (!p.percolates()) {
				int a = StdRandom.uniform(1, N+1);
                int b = StdRandom.uniform(1, N+1);
                
                if (!p.isOpen(a, b) && !p.isFull(a, b)) {
                    p.open(a, b);
                    number++;
                }
            }
            
			fractions[i] = (double) number/(N*N);
		}
	}
	
	public double mean() {
		return StdStats.mean(fractions);
	}
	
	public double stddev() {
		return StdStats.stddev(fractions);
	}
	
	public double confidenceLo() {
		return mean() - ((1.96 * stddev() / Math.sqrt(trials)));
	}
	
	public double confidenceHi() {
		return mean() + ((1.96 * stddev() / Math.sqrt(trials)));
	}

	public static void main(String[] args) {
		PercolationStats stats = new PercolationStats(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
		
		StdOut.println("mean                    = " + stats.mean());
        StdOut.println("stddev                  = " + stats.stddev());
        StdOut.println("95% confidence interval = " 
                       + stats.confidenceLo() + ", " +  stats.confidenceHi());
	}

}
