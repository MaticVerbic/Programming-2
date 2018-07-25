import java.io.File;

public class DN12 {

    public static void main(String[] args){
        File f = new File(args[0]);

        System.out.println(f); 
        rec(f, 0);       
    }

    public static boolean rec(File f, int someCounter) {
        if(! f.isDirectory()) {
            return false;
        }
        String lines="|  ";
        if (someCounter==0) {
            lines = "";
        } else {
            for (int i=0; i < someCounter-1; i++) {
                lines+=lines;
                
            }
            lines= "  " + lines;
            lines=lines.substring(0, lines.length() - 2);
        }
        for(File dir : f.listFiles()) {
            //System.out.println(lines.length());
            if (lines.length() > 11 && dir.isFile()) {
                lines = lines.substring(0, lines.length()-3);
            }
            System.out.print(lines + "  |___" + dir.getName() +"\n");
            rec(dir, someCounter+1);
        }
        return true;
    }

}