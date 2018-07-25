import java.util.Arrays;

public class DN04 {



    public static void main(String[] args){
        String[] prva   = {"Miha", "Micka", "Tone", "Lojze", "Mojca", "Pepca", "Franci", "Francka"};
        String[] druga  = {"vozi", "seka", "potrebuje", "gleda", "barva", "voha", "lomi", "popravlja"};
        String[] tretja = {"kolo", "avto", "likalnik", "sonce", "vrtnico", "drevo", "lopato", "sekiro"};


        if (args.length != 3 || !(Arrays.asList(prva).contains(args[0]) && Arrays.asList(druga).contains(args[1]) && Arrays.asList(tretja).contains(args[2]))) {
            System.out.println("false"); 
        } else {
            System.out.println("true"); 
        }
    }
}