package rumorChecker;

public class Vertex implements Comparable<Vertex>{
	
	private final String name;
	private Edge[] adj;
	private double minDist = Double.POSITIVE_INFINITY;
	private Vertex previous;
	private int index;
	
	public Vertex(String argName, int i) { 
		name = argName; 
		index = i;
	}
	
	public String toString(){
		return name;
	}
	
	public int index(){
		return index;
	}

	public int compareTo(Vertex other)
	{
	    return Double.compare(minDist, other.minDist);
	}
	
	public Edge[] edges(){
		return adj;
	}
	
	public double minDist(){
		return minDist;
	}
	
	public void changeMindist(double input){
		minDist = input;
	}
	
	public void changePrev(Vertex input){
		previous = input;
	}

	public Vertex prev(){
		return previous;
	}
	
	public void changeEdges(Edge[] inputadj){
		adj=inputadj;
	}

}
