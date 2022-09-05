import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        System.out.println("Hello World...");
        Scanner sc = null;
        try {
            sc = new Scanner(new File("input.txt"));
            String name = sc.nextLine();
            System.out.print("Hi I am " + name);
        } catch (FileNotFoundException fe) {
            System.out.println("Error occurred : " + fe);
        } finally {
            if (sc != null) {
                sc.close();
            }
        }
    }
public String myName(){
return "Nirakh";
}
}