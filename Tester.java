import org.w3c.dom.ls.LSInput;

import javax.swing.*;
import java.sql.SQLOutput;
import java.util.Scanner;
public class Tester
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Integer: ");
        int index = scanner.nextInt();
        System.out.println(index);


        System.out.print("Integer 2: ");
        int index2 = 0;
        if (scanner.hasNext())
        {
            index2 = scanner.nextInt();
            scanner.nextLine();
        }
        System.out.println(index2);
    }
}
