/**
 * 
 * Naloga: 
 * Poišči prvo 5-mestno število x, katerega prva števka je enaka številu ničel v x, 
 * druga števka je enaka številu enic v x, tretja številu dvojk, četrta številu trojk in 
 * zadnja številu štiric. 
 * 
 * a) napiši metodo public static int prestej znak(String s, char c), ki prešteje kolikokrat se znak c pojavi v nizu s. 
 * b) napiši metodo public static int[] vTabelo(int n), ki spremeni celo število n v tabelo števk. 
 * c) napiši metodo public static int[] obrniTabelo(int[] t), ki obrne vrstni red tabele t.
 * 
 * @author Matic Verbič
 * 
 */

public class Digit {


    public static void main(String[] args) {
        /**
         * Naloga 
         */
        int stevilo = 0;
        for (int i = 10000; i <  100000; i++) {
            String steviloString = String.valueOf(i); 
            int[] steviloIntArray = obrniTabelo(vTabelo(i, steviloString.length()));
            if (   steviloIntArray[0] == prestejZnak(steviloString, "0".charAt(0))
                && steviloIntArray[1] == prestejZnak(steviloString, "1".charAt(0)) 
                && steviloIntArray[2] == prestejZnak(steviloString, "2".charAt(0)) 
                && steviloIntArray[3] == prestejZnak(steviloString, "3".charAt(0)) 
                && steviloIntArray[4] == prestejZnak(steviloString, "4".charAt(0))   ) {
                
                System.out.println(steviloString); 
                break;
            }
        } 

    }

/**
 * a)
 */
   
    public static int prestejZnak(String s, char c) {
        int counter = 0;
        char[] charArray = s.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            if (charArray[i] == c) {
                counter++;
            }
        }
        return counter;
    }

/**
 * b)
 */

    public static int[] vTabelo(int n, int dolzina) {
        int[] stevilo = new int[dolzina]; 
        int stevec = 0; 
        while (n >= 1) {
            stevilo[stevec] = n % 10; 
            n /= 10; 
            stevec++; 
        }
         
        return stevilo; 
    }

/**
 * c)
 */

    public static int[] obrniTabelo(int[] t) {
        for(int i = 0; i < t.length/2; i++) {
            int trenutna = t[i];
            t[i] = t[t.length - i - 1];
            t[t.length - i - 1] = trenutna;
        }
        return t; 
    }

}