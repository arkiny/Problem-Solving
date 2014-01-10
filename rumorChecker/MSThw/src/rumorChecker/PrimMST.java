package rumorChecker;

import java.util.PriorityQueue;
import java.util.Queue;

// Minimum spanning tree with prim's algorithm

public class PrimMST {
	private double weight;
	private Queue<Edge> mst;
	private boolean[] marked;
	private PriorityQueue<Edge> pq;

	public PrimMST(Graph inputGraph) {
		mst.clear();
		pq.clear();
		marked = new boolean[inputGraph.vertices()];
		for (int v = 0; v < inputGraph.vertices(); v++)
			if (!marked[v])
				prim(inputGraph, v); // get a minimum spanning forest
	}
	
    private void prim(Graph inputGraph, int s) {
        scan(inputGraph, s);
        while (!pq.isEmpty()) {                    
            Edge e = pq.remove();                  
            int v = e.from().index(), w = e.to().index(); 
            assert marked[v] || marked[w];
            if (marked[v] && marked[w]) continue;     
            mst.add(e);                            
            weight += e.weight();
            if (!marked[v]) scan(inputGraph, v);   
            if (!marked[w]) scan(inputGraph, w);   
        }
    }

    private void scan(Graph inputGraph, int v) {
        assert !marked[v];
        marked[v] = true;
        for (Edge e : inputGraph.getVertices(v).edges())
            if (!marked[e.to().index()]) pq.add(e);
    }
        
    public Iterable<Edge> edges() {
        return mst;
    }

    public double weight() {
        return weight;
    }

}
