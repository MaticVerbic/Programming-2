import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.io.File;
import java.io.FileNotFoundException;


public class DN10 {

    public static void main(String[] args) throws Exception {
        if(args.length == 2) {
            Scanner sc = new Scanner(new File(args[0]));
            TreeMap<String, Integer> tm = new TreeMap<String, Integer>();
            while(sc.hasNext()){
                String next = sc.next().replaceAll("[^\\w\']+", "");;
                if (tm.containsKey(next)) {
                   tm.put(next, tm.get(next) + 1);
                } else {
                    tm.put(next, 1);
                }
            }
            TreeMap<String, Integer> sortedByKey = new TreeMap<String, Integer>(tm);
            if (args[1].equals("1")) {
                for(Map.Entry<String, Integer> e : sortedByKey.entrySet()) {
                    System.out.printf("%-6d %s\n", e.getValue(),  e.getKey());
                }
            } else if(args[1].equals("2")) {
                int maxval = 0;
                String maxKey = "";
                while (tm.size() > 0) {
                    for(Map.Entry<String, Integer> e : tm.entrySet()) {
                        if (e.getValue() > maxval) {
                            maxKey = e.getKey();
                            maxval = e.getValue();
                        }
                    }
                    System.out.printf("%-6d %s\n", maxval, maxKey);
                    tm.remove(maxKey);
                    maxKey = "";
                    maxval = 0; 
                }
            }
        }
    }    
}

