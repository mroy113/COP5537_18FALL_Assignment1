import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        //load matrix files
        File toyMatrix = new File ("C:\\Users\\roym3\\IdeaProjects\\COP5537_18FALL_Assignment1\\src\\main\\resources\\toyMatrix.txt");
        File networkMatrix = new File ("C:\\Users\\roym3\\IdeaProjects\\COP5537_18FALL_Assignment1\\src\\main\\resources\\networkMatrix.txt");
        File toyMatrixWithNames = new File ("C:\\Users\\roym3\\IdeaProjects\\COP5537_18FALL_Assignment1\\src\\main\\resources\\toyMatrixWithNames.txt");

        //create instance of matrix loader and graph classes
        MatrixLoader matrixLoader = new MatrixLoader();
        Graph graph = new Graph();

        //load adjacency matrix from file into 2d list for creation of graph instance
        List<List<Integer>> adjacencyMatrix = matrixLoader.loadMatrix(toyMatrixWithNames);

        //add each vertex to graph with labels and indexes
        List vertexLabels = matrixLoader.loadVertexLabels(toyMatrixWithNames);
        int vertexIndex = 0;
        for (var v : vertexLabels){
            Vertex vertex = new Vertex(v.toString(),vertexIndex);
            graph.addVertex(vertex);
            vertexIndex++;
        }

        //load each edge into the graph
        for (int i = 0; i < adjacencyMatrix.size(); i++) {
            for (int j = i; j < adjacencyMatrix.get(i).size(); j++) {
                if (adjacencyMatrix.get(i).get(j) > 0) {
                    graph.addEdge(graph.getVerticies().get(i), graph.getVerticies().get(j), adjacencyMatrix.get(i).get(j));
                }
            }
        }

        //user input
        //String startingVertex = "zero";
        //String endingVertex = "six";

        DijkstraSolver shortestPathSolver = new DijkstraSolver();
        shortestPathSolver.solve(graph, graph.getVerticies().get(0), graph.getVerticies().get(6));

        System.out.print("wut");

    }

}

class MatrixLoader {
    //load matrix files into Matrix object, return the resulting Matrix object
    public List<List<Integer>> loadMatrix(File matrixFile) {

        try {
            Scanner fileIn = new Scanner(matrixFile);
            List<List<Integer>> theMatrix = new ArrayList<>();
            while(fileIn.hasNextLine()){
                Scanner colReader = new Scanner(fileIn.nextLine().replaceAll(".*\t",""));
                colReader.useDelimiter(",");
                List<Integer> col = new ArrayList<>();
                while(colReader.hasNextInt())
                {
                    col.add(colReader.nextInt());
                }
                theMatrix.add(col);
            }
            fileIn.close();
            return (theMatrix);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List loadVertexLabels (File matrixFile){

        try{
            List vertexLabels = new ArrayList<String>();
            Scanner fileIn = new Scanner(matrixFile);
            while(fileIn.hasNextLine()) {
                Scanner colReader = new Scanner(fileIn.nextLine().replaceAll("\t?[0-9]*,[0-9]*",""));
                vertexLabels.add(colReader.nextLine());
            }
            return vertexLabels;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}

class Vertex {
    private String label;
    private int index;

    public Vertex(String label, int index){
        this.label = label;
        this.index = index;
    }

    public String getLabel() {
        return label;
    }
    public int getIndex() {
        return index;
    }

}

class Edge  {
    private Vertex source;
    private Vertex destination;
    int distance;

    public Edge(Vertex source, Vertex destination, int distance) {
        this.source = source;
        this.destination = destination;
        this.distance = distance;
    }
    public Vertex getSource() {
        return source;
    }

    public Vertex getDestination() {
        return destination;
    }
    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public boolean containsVertex(Vertex vertex){
        if (this.getSource() == vertex || this.getDestination() == vertex){
            return true;
        } else {
            return false;
        }
    }

    public Vertex getVertexPair(Vertex vertex){
        if (containsVertex(vertex)){
            if (vertex == this.destination) {
                return this.getSource();
            } else {
                return this.getDestination();
            }
        } else return null;
    }

}

class Graph {

    private List<Vertex> verticies;
    private List<Edge> edges;

    public Graph(){
        verticies = new ArrayList<Vertex>();
        edges = new ArrayList<Edge>();
    }

    public List<Vertex> getVerticies() {
        return verticies;
    }

    public void setVerticies(List<Vertex> verticies) {
        this.verticies = verticies;
    }

    public void addVertex(Vertex vertex) {
        verticies.add(vertex);
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public void addEdge(Edge edge) {
        edges.add(edge);
    }

    public void addEdge(Vertex vertex, Vertex vertex1, Integer integer) {
        edges.add(new Edge(vertex, vertex1, integer));
    }

    public List getNeighbors(Vertex vertex){
        List neighbors = new ArrayList<Vertex>();
        for (Edge e : edges){
            if (e.containsVertex(vertex)){
                neighbors.add(e);
            }
        }
        return neighbors;
    }
}

class DijkstraSolver {

    public void solve(Graph graph, Vertex start, Vertex end){
        Set visitedVerticies = new HashSet<Vertex>();
        Set unvisitedVerticies = new HashSet<Vertex>();
        Map distances = new HashMap<Vertex,Integer>();
        Map predecessors = new HashMap<Vertex, Vertex>();

        //initialize distances
        for (var v : graph.getVerticies()){
            distances.put(v,Integer.MAX_VALUE);
        }
        distances.computeIfPresent(start, (k,v) -> 0);

        //initialize list of unvisited verticies
        for (Vertex vertex : graph.getVerticies()){
            unvisitedVerticies.add(vertex);
        }

        Vertex current = start;
        while(!unvisitedVerticies.isEmpty()) {

/*            for (var vertex : unvisitedVerticies){
                int dv = (Integer)distances.get(vertex);
                int dc = (Integer)distances.get(current);
                if ( dv <= dc || visitedVerticies.contains(current)){
                    current = (Vertex)vertex;
                }
            }

            List<Edge> neighbors = graph.getNeighbors(current);
            visitedVerticies.add(current);
            unvisitedVerticies.remove(current);
            for (Edge neighbor : neighbors){
                int distance = neighbor.getDistance();
                if (distance > (Integer)distances.get(current)){
                    distances.put(neighbor.getVertexPair(current),distance + (Integer)distances.get(current));
                }

            }
*/

            List<Edge> neighbors = graph.getNeighbors(current);
            Vertex closest = null;
            for (int i = 0; i < neighbors.size(); i++) {
                if (closest == null || neighbors.get(i).getDistance() < neighbors.){
                    closest = neighbors.get(i).getVertexPair(current);
                }

            }
            visitedVerticies.add(current);
            unvisitedVerticies.remove(current);
            System.out.print("wut");
        }
        System.out.print("wut");
    }
}