package rumorChecker;

public class Graph {
	private Vertex[] vertices;	
		
	public Graph(int v){
		vertices = new Vertex[v];
		for (int i = 0; i < v;i++){
			vertices[i] = new Vertex(i+1+"", i);
		}
	}
	
	public int vertices(){
		return vertices.length;
	}
	
	public Vertex[] getVertices(){
		return vertices;
	}
	
	public Vertex getVertices(int n){
		return vertices[n];
	}
	
	public void addVertice(int input){
		Vertex inputv = new Vertex(""+input+"", input-1);
		vertices[input-1] =  inputv;
	}
	
	public void addEdge(int v, Edge[] input){
		vertices[v-1].changeEdges(input);
	}
	
}
