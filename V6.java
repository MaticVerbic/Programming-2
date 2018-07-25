import java.util.Scanner; 
import java.io.File;
import java.lang.Character;

public class V6 {
    public static void main(String[] args) throws Exception{
        String s = odkodiraj(args[0], args[1]);
        System.out.println(s);
        String s2 = kodiraj("v61.txt", "pomlad pomlad pomlad"); 
        System.out.println(s2);
    }

    public static String odkodiraj(String kodirnaKnjiga, String koda) throws Exception {
        Scanner sc = new Scanner(new File(kodirnaKnjiga));
        Scanner sc2 = new Scanner(new File(koda));
        StringBuilder sb = new StringBuilder();
        String readText = "";
        String[] splitText; 
        String rezultat; 
         

        while(sc2.hasNextLine()) {
            sb.append(sc2.nextLine());
            sb.append(" ");
        }
        
        //String presplitKoda = sb.toString().replace("0", "");

        String[] splitKoda = sb.toString().trim().split("\\s+");
        
        sb = new StringBuilder();
        while(sc.hasNextLine()) {
            sb.append(sc.nextLine());
            sb.append(" "); 
        }
        readText = sb.toString(); 
        splitText = readText.split(" "); 

        


        sb = new StringBuilder(); 
        
        int zeroCounter = 0; 

        for(int i=0; i < splitKoda.length; i+=2) {
            while(Integer.parseInt(splitKoda[i]) == 0) {
                zeroCounter++; 
                i++; 
            }        
            
            int index = Integer.parseInt(splitKoda[i]);
            
            String beseda = splitText[index-1];
            char crka = beseda.charAt(Integer.parseInt(splitKoda[i+1])-1); 
            switch (zeroCounter) {
                case 1: 
                    crka = Character.toUpperCase(crka);
                    break; 
                case 2: 
                    sb.append(" ");
                    break; 
                case 3: 
                    sb.append("\n");
                    break; 
            }
            sb.append(crka);
            zeroCounter = 0; 
            //sb.append(splitText[Integer.parseInt(splitKoda[i])].charAt(splitKoda[i+1])); 
        }

        rezultat = sb.toString();         

        return rezultat;

    }

    public static String kodiraj(String kodirnaKnjiga, String niz) throws Exception{
        Scanner sc = new Scanner(new File(kodirnaKnjiga));
        StringBuilder sb = new StringBuilder();
        String readText = "";
        String[] splitText; 
        String rezultat;
        String[] znaki = niz.split(""); 


        while(sc.hasNextLine()) {
            sb.append(sc.nextLine());
            sb.append(" "); 
        }
        readText = sb.toString(); 
        splitText = readText.split(" "); 

        for(String b: splitText) {
            System.out.println(b); 
        }


        sb = new StringBuilder();
        boolean found = false; 
        for (String c: znaki) {
            if (c.equals(" ")) {
                sb.append("0 0 ");
                continue; 
            } 
            for (Integer i=0; i < splitText.length; i++) {
                if (splitText[i].indexOf(c) >= 0) {
                    Integer in = i + 1; 
                    Integer ind = splitText[i].indexOf(c) + 1; 
                    String s = (in).toString() + " " + ind.toString();
                    sb.append(s);
                    found = true;
                    splitText[i] = "+";  
                    break; 
                }
            }
            if (found == false ) {
                sb.append("99 99"); 
            }
            sb.append(" "); 
            found = false; 
        }

        return sb.toString(); 
    }

}