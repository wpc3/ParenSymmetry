import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Stack;
import java.util.stream.Stream;

public class ParenSymmetry {


    private Boolean isBalanced(String s) {
        // implement this method
        Stack<Character> stack = new Stack <>();

        for (char c : s.toCharArray()){
            if(c == '(' || c == '[' || c == '{' ){
                stack.push(c);
            }
            else if (c == ')' || c == ']' || c == '}'){
                if (stack.isEmpty()){
                    return false;
                }
                char top = stack.pop();
                if ((c == ')' && top != '(') || (c == ']' && top != '[' ) || (c == '}' && top != '{')){
                    return false;
                }
            }
        }



        return stack.isEmpty();
    }




    private void checkFile(String filename) throws IOException  {
//        try{
//            Scanner fileIn = new Scanner(new File("input.txt"));
//
//            while (fileIn.hasNext())
//            {
//                // Reads the entire line as a string
//                String lineIn = fileIn.nextLine();
//                // Split the line into a String array
//                String[] input = lineIn.split(",");
//                int sum = 0;
//                for (int i = 0; i < input.length; i ++) {
//
//                    sum += Integer.parseInt(input[i]);
//                }
//                System.out.println(sum);
//            }
//        }
//        catch (IOException e) {
//            System.out.println("File not found");
//
//    }

        //This a java class to get a file path
        Path filePath = Paths.get(filename);


        Stream<String> lines = Files.lines(filePath);

        //helps you go through each line in a file
        Iterator<String > iterator = lines.iterator();

        //the loop to go through each line
        while ((iterator.hasNext())){

            String line = iterator.next();

            System.out.println(line + " >>> " + isBalanced(line));
        }

        lines.close();



        // open file named filename

        // for each line in the file
            // read the line
            // print whether or not the line's parenthesis are balanced

        // CLOSE the file
    }

    public static void main(String[] args) {



        ParenSymmetry ps = new ParenSymmetry();


        Boolean b0 = ps.isBalanced("()");
        printResult(b0, true);

        String[] falseStrings = {"(", "((", ")", "", "(()())((())))"};
        Boolean falses = true;
        for (String strToTest : falseStrings) {
            falses = ps.isBalanced(strToTest);
        }
        printResult(falses, false);

        String[] trueStrings = {"()", "(())", "(((())))", "", "(()())((()))", "(   )", "( () ( ) )"};
        Boolean trues = false;
        for (String strToTest : trueStrings) {
            trues = ps.isBalanced(strToTest);
        }
        printResult(trues, true);

    }

    private static void printResult(Boolean b0, boolean b) {
        if (b0 == null) {
            System.out.println("Null Failure");
            return;
        }
        if (b0 == b) {
            System.out.println("Success");
        } else {
            System.out.println("Failure");
        }
    }
}
