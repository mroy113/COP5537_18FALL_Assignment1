import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        File toyMatrix = new File ("./resources/toyMatrix.txt");
        File networkMatrix = new File ("./resources/networkMatrix.txt");

        MatrixLoader matrixLoader = new MatrixLoader();
        Matrix theMatrix = matrixLoader.loadMatrix(toyMatrix);

    }

}

class MatrixLoader {

    //load matrix files into Matrix object, return the resulting Matrix object
    public Matrix loadMatrix(File matrixFile) {
        Matrix theMatrix = new Matrix();
        try {
            Scanner fileIn = new Scanner(matrixFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return (theMatrix);
    }
}

class Matrix {

    List<List<Integer>> dataMatrix = new ArrayList<List<Integer>>();

    public void addRow(List<Integer> row){
        dataMatrix.add(row);
    }

    public List<Integer> getDijkstraShortestPath(int start, int end){
        List<Integer> shortestPath = new ArrayList<Integer>();

        return shortestPath;
    }
}