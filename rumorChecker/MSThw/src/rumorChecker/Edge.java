package rumorChecker;

public class Edge {

	public final Vertex from;
	public final Vertex target;
	public final double weight;
	public Edge(Vertex inputfrom, Vertex inputTarget, double inputWeight){
		target = inputTarget;
		weight = inputWeight;
		from = inputfrom;
	}
	public Vertex from(){
		return from;
	}
	
	public Vertex to(){
		return target;
	}
	
	public double weight(){
		return weight;
	}
	
    public String toString() {
        return from + "->" + target + " " + String.format("%5.2f", weight);
    }
}
