import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        File toyMatrix = new File ("C:\\Users\\roym3\\IdeaProjects\\COP5537_18FALL_Assignment1\\src\\main\\resources\\toyMatrix.txt");
        File networkMatrix = new File ("C:\\Users\\roym3\\IdeaProjects\\COP5537_18FALL_Assignment1\\src\\main\\resources\\networkMatrix.txt");

        MatrixLoader matrixLoader = new MatrixLoader();
        List<List<Integer>> theMatrix = matrixLoader.loadMatrix(toyMatrix);

    }

}

class MatrixLoader {

    //load matrix files into Matrix object, return the resulting Matrix object
    public List<List<Integer>> loadMatrix(File matrixFile) {

        try {
            Scanner fileIn = new Scanner(matrixFile);
            List<List<Integer>> theMatrix = new ArrayList<>();
            while(fileIn.hasNextLine()){
                Scanner colReader = new Scanner(fileIn.nextLine().replaceAll("[0-9]*\t",""));
                colReader.useDelimiter(",");
                List<Integer> col = new ArrayList<>();
                while(colReader.hasNextInt())
                {
                    col.add(colReader.nextInt());
                }
                theMatrix.add(col);
            }
            return (theMatrix);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}