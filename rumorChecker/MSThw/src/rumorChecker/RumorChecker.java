package rumorChecker;

import java.util.Scanner;

public class RumorChecker {

	public RumorChecker(Scanner input) {

		// String scanned = new String();
		// initiated;
		// scanned = input.nextLine();

		while (input.hasNext()) {
			// Scanner stored = new Scanner(scanned);
			int init = input.nextInt();
			if (init > 100) {
				System.out.println("out of range");
			}

			// terminate command and disjointed;
			else if (init == 0) {
				break;
			}

			else if (init != 0) {
				int numOfbroker = init;

				// init graph
				Graph inputGraph = new Graph(numOfbroker);
				for (int i = 1; i < numOfbroker + 1; i++) {
					int numOfContacts = input.nextInt();

					if (numOfContacts == 0) {
						Edge[] edges = new Edge[0];
					} else {
						Edge[] edges = new Edge[numOfContacts];
						for (int j = 0; j < numOfContacts; j++) {
							edges[j] = new Edge(
									inputGraph.getVertices(i - 1),
									inputGraph.getVertices(input.nextInt() - 1),
									input.nextDouble());
						}
						inputGraph.getVertices(i - 1).changeEdges(edges);
					}
				}

				// computing FloydWarshall algorithm
				FloydWarshall a = new FloydWarshall(inputGraph);

				// shortest path

				// for (int v = 0; v < inputGraph.vertices(); v++) {
				// System.out.printf("%3d: ", v + 1);
				// for (int w = 0; w < inputGraph.vertices(); w++) {
				// if (a.hasPath(v, w))
				// System.out.printf("%6.2f ", a.dist(v, w));
				// else
				// System.out.printf("   Inf ");
				// }
				// System.out.println();
				// }

				// all path

				double minDist = Double.POSITIVE_INFINITY;
				int vertex = -1;

				boolean disjointed = true;
				for (int v = 0; v < inputGraph.vertices(); v++) {
					boolean hasAllpath = true;

					double minDistChecker = Double.NEGATIVE_INFINITY;
					for (int w = 0; w < inputGraph.vertices(); w++) {
						if (v != w) {
							if (a.dist(v, w) != Double.POSITIVE_INFINITY) {
								// System.out.printf("%d to %d (%5.2f)  ", v +
								// 1,
								// w + 1, a.dist(v, w));
								// for (Edge e : a.path(v, w))
								// System.out.print(e + "  ");
								// System.out.println();

								hasAllpath = true;
								if (a.dist(v, w) > minDistChecker) {
									minDistChecker = a.dist(v, w);
								}

							} else {
								// System.out.printf(
								// "%d to %d          no path\n", v + 1,
								// w + 1);

								hasAllpath = false;
								minDistChecker = Double.NEGATIVE_INFINITY;
								break;
							}

						} else {
							continue;
						}
					}

					if (hasAllpath && minDistChecker < minDist) {
						vertex = v + 1;
						minDist = minDistChecker;
						disjointed = false;
					} else {
						continue;
					}
				}

				if (disjointed == false) {
					int output = (int) (0 + minDist);
					System.out.println(vertex + " " + output);
				} else {
					System.out.println("disjoint");
				}
			}
		}
	}

	// private int optTime(Graph input) {
	// int minimumTime = 0;
	// Stack<Vertex> unvisited = new Stack<Vertex>();
	// Stack<Vertex> visited = new Stack<Vertex>();
	//
	// // push all unvisited vertex in the stack.
	// // Get min spanning tree from vertex V
	// for (int v = 0; v < input.vertices(); v++) {
	// //get min tree value from v(start)
	//
	//
	// }
	// return minimumTime;
	// }
	//
	// private

	//
	// public void computePaths(Vertex source) {
	// source.changeMindist(0);
	// PriorityQueue<Vertex> vertexQueue = new PriorityQueue<Vertex>();
	// vertexQueue.add(source);
	//
	// while (!vertexQueue.isEmpty()) {
	// Vertex u = vertexQueue.poll();
	// for (Edge e : u.edges()) {
	// Vertex v = e.target;
	// double weight = e.weight;
	//
	// // relax part
	// double distthruU = u.minDist() + weight;
	// if (distthruU < v.minDist()) {
	// vertexQueue.remove(v);
	// v.changeMindist(distthruU);
	// v.changePrev(u);
	// vertexQueue.add(v);
	// }
	// }
	// }
	// }
	//
	// public List<Vertex> getShortedstPathTo(Vertex t) {
	// List<Vertex> path = new ArrayList<Vertex>();
	// for (Vertex vertex = t; vertex != null; vertex = vertex.prev())
	// path.add(vertex);
	//
	// Collections.reverse(path);
	// return path;
	// }

	// check if is this line is number of stock brokers, terminate command
	// this function
	// return 1 when this line include number of brokers
	// return 0 when this line is terminated set

	// private int checker(Scanner input) {
	// int size = 0;
	// Scanner checking = input;
	// while (checking.hasNext()) {
	// checking.next();
	// size++;
	// }
	// if (size == 1)
	// return 1;
	// else
	// return 2;
	// }
}
