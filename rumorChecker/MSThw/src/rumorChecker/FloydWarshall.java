package rumorChecker;

import java.util.Stack;

public class FloydWarshall {

	private double[][] distTo;
	private Edge[][] edgeto;

	public FloydWarshall(Graph g) {
		int V = g.vertices();
		distTo = new double[V][V];
		edgeto = new Edge[V][V];

		for (int v = 0; v < V; v++) {
			for (int w = 0; w < V; w++) {
				distTo[v][w] = Double.POSITIVE_INFINITY;
			}
		}

		for (int v = 0; v < g.vertices(); v++) {

			if (g.getVertices(v).edges()!= null) {
				for (Edge e : g.getVertices(v).edges()) {
					distTo[e.from().index()][e.to().index()] = e.weight();
					edgeto[e.from().index()][e.to().index()] = e;
				}
			}
			if (distTo[v][v] >= 0.0) {
				distTo[v][v] = 0.0;
				edgeto[v][v] = null;
			}
		}
		for (int i = 0; i < V; i++) {
			for (int v = 0; v < V; v++) {
				if (edgeto[v][i] == null)
					continue;

				for (int w = 0; w < V; w++) {
					if (distTo[v][w] > distTo[v][i] + distTo[i][w]) {
						distTo[v][w] = distTo[v][i] + distTo[i][w];
						edgeto[v][w] = edgeto[i][w];
					}
				}
				if (distTo[v][v] < 0.0)
					return;
			}
		}
	}

	public boolean hasPath(int v, int w) {
		return distTo[v][w] < Double.POSITIVE_INFINITY;
	}

	public double dist(int v, int w) {
		return distTo[v][w];
	}

	public Iterable<Edge> path(int v, int w) {
		if (!hasPath(v, w))
			return null;
		Stack<Edge> path = new Stack<Edge>();
		for (Edge e = edgeto[v][w]; e != null; e = edgeto[v][e.from().index()]) {
			path.push(e);
		}
		return path;
	}

	// check optimality conditions
	private boolean check(Graph G, int s) {
		for (int v = 0; v < G.vertices(); v++) {
			for (Edge e : G.getVertices(v).edges()) {
				int w = e.to().index();
				for (int i = 0; i < G.vertices(); i++) {
					if (distTo[i][w] > distTo[i][v] + e.weight()) {
						System.err.println("edge " + e + " is eligible");
						return false;
					}
				}
			}
		}

		return true;
	}

}
