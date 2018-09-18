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

        File toyMatrix = new File ("C:\\Users\\roym3\\IdeaProjects\\COP5537_18FALL_Assignment1\\src\\main\\resources\\toyMatrix.txt");
        File networkMatrix = new File ("C:\\Users\\roym3\\IdeaProjects\\COP5537_18FALL_Assignment1\\src\\main\\resources\\networkMatrix.txt");

        MatrixLoader matrixLoader = new MatrixLoader();
        List theMatrix = matrixLoader.loadMatrix(networkMatrix);

    }

}

class MatrixLoader {

    //load matrix files into Matrix object, return the resulting Matrix object
    public ArrayList loadMatrix(File matrixFile) {

        try {
            Scanner fileIn = new Scanner(matrixFile);
            ArrayList<ArrayList<Integer>> theMatrix = new ArrayList<>();
            while(fileIn.hasNextLine()){
                Scanner colReader = new Scanner(fileIn.nextLine().replaceAll("[0-9]*\t",""));
                colReader.useDelimiter(",");
                ArrayList col = new ArrayList();
                while(colReader.hasNextInt())
                {
                    col.add(colReader.nextInt());
                }
                theMatrix.add(col);
            }
            return (theMatrix);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
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