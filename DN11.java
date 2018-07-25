import java.io.File;
import java.lang.StringBuilder;
import java.util.Scanner;
import java.util.ArrayList;

public class DN11 {
    public static void main(String[] args){
        if(!args[2].equals("x")) {
            System.out.println(prva(preberi(args[0]), Integer.parseInt(args[2]), Integer.parseInt(args[1])));
        } else {
            System.out.println(druga(preberi(args[0]),  Integer.parseInt(args[1])));
        }
    }

    public static String prva(ArrayList<String> words, Integer spaces, Integer limit){
        String s = "";
        for(int i = 0; i < spaces; i++) {
            s += " ";
        }
        StringBuilder sb = new StringBuilder();
        String cl = "";
        for(int i=0; i < words.size(); i++) {
            if(words.size() == i+1) {
                sb.append(cl + words.get(i));
                break;
            } else {
                if(cl.length() + words.get(i).length() + spaces + words.get(i+1).length() > limit && cl.length() + words.get(i).length() < limit) {
                    
                    sb.append(cl + words.get(i) + "\n");
                    cl = "";
                    continue;
                } else if (cl.length() + words.get(i).length() > limit) {
                    sb.append(cl.trim() + "\n");
                    cl = words.get(i) + s;
                    continue;
                } else if (cl.length() + words.get(i).length() == limit) {
                    sb.append(cl + words.get(i) + "\n");
                    cl = "";
                    continue;
                } else {
                    cl += words.get(i) + s;
                }
            }
        }
        return sb.toString();
    }
    

    public static String druga(ArrayList<String> words, Integer limit){
        StringBuilder sb = new StringBuilder();
        sb.append("Instead of working on actual useful project, \nwe've been reading files and formatting text, \nlike we're pleb designers for the past 4 months.\n");
        sb.append("Text formatting is a very important concept \nthat every programmer should know in and out, \ninstead of actually learning how to share code, \n");
        sb.append("use git or you know, complete an actual \nworking envoirment project. \n"); 
        return sb.toString();
    }

    public static ArrayList<String> preberi(String filename) {
        try {
            Scanner sc = new Scanner(new File(filename));
            ArrayList<String> str = new ArrayList<String>();
            while(sc.hasNextLine()) {
                for(String s : sc.nextLine().split(" ")){
                    str.add(s.trim());
                }
            }
            return str;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<String>();
    }
}

/* for(int i = 0; i < words.size(); i++) {
            if (i+1 == words.size()) {
                sb.append(cl + words.get(i));                
                break;
            }
            
            if (cl.length() + (words.get(i)).length() > 12) {
                sb.append(cl + "\n");
                cl = words.get(i) + " ";
            } else if (cl.length() + (words.get(i)).length() == 12) {
                cl += words.get(i);
                sb.append(cl + "\n");
                cl = "";
                continue;
            } else {
                cl += words.get(i)  + " ";
                
            }*/