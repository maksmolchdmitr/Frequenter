import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter file path whom statistic you want to see");
        String filepath = scanner.next();
        try {
            Frequenter frequenter = new Frequenter(new FileReader(filepath));
            frequenter.printStatistics(System.out);
            System.out.println("Enter file path where you want to write statistic");
            String outFilepath = scanner.next();
            frequenter.printStatistics(new PrintStream(outFilepath));
            System.out.println("Successfully has written");
        } catch (FileNotFoundException e) {
            System.out.println("File is not found!");
        } catch (Exception e){
            System.out.println("Unknown error: "+e);
        }
    }
}
