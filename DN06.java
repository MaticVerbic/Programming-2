import java.util.Arrays;
import java.util.Scanner;
import java.io.File;

public class DN06 {

    public static void main (String[] args) throws Exception {
        StringBuilder sb = new StringBuilder();
        Scanner sc = new Scanner(new File(args[0]));
        while(sc.hasNextLine()){
            String[] spl = sc.nextLine().split("(?<=\\G.{8})");
            for (int i = 0; i < spl.length; i++) {
                sb.append(new Character((char)Integer.parseInt(spl[i], 2)).toString());
            }
        }
        System.out.print(sb.toString());
    }
}