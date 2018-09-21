import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
/*
        //user input
        String startingVertex = "zero";
        String endingVertex = "six";
        //
        DijkstraSolver shortestPathSolver = new DijkstraSolver();
        shortestPathSolver.solve(graph, graph.getVerticies().get(0), graph.getVerticies().get(6));

        System.out.print("wut");*/

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
    Vertex source;
    Vertex destination;
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
}

class Graph {

    List<Vertex> verticies;
    List<Edge> edges;

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
}

class DijkstraSolver {

    public void solve(Graph graph, Vertex start, Vertex end){

    }

}