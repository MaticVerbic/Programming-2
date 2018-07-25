
public class V4 {

    public static void main(String[] args) {
        naloga("1. naloga");
        izpisi(4342175383962075708l);
        makeSpace(2);
        izpisi(-67536333235548144l);
        makeSpace(2);
        naloga("2. naloga");
        izpisi2(new long[] {
            4821103401091611672l, 0, 144680345680364600l, 1739555224076567106l, 
            -9114862049243683816l,1739555224076567106l, 0, 4821103401091611672l
        });
        makeSpace(2);
        naloga("3.naloga");
        long crkaO = getCrko(new int[] {60, 66, 129, 129, 129, 129, 66, 60});    
        System.out.println(crkaO);
        makeSpace(2);
        naloga("4. naloga");
        ime();
    }

    public static String[] prevedi(long crka){
        /**
         * Ex. 1
         * Translates a long integer into a 
         * String of binary numbers and splits it into
         * an 8x8 String array. 
         * 
         * @note uses regex to split lines: "(?<=\\G.{i})" ==> where i 
         * can be replaced for a desired length. 
         * 
         * @param crka | a long representation of an 64-bit binary value 
         *               representing an 8x8 binary table
         * @return splitano | a String array of 1 byte sized lines 
         *                    representing a column of a character 
         */
        String rezultat = Long.toBinaryString(crka);
        while(rezultat.length() < 64) {
            rezultat = "0" + rezultat;
        }
        String[] splitano = rezultat.split("(?<=\\G.{8})");
        return splitano; 
    }

    public static void izpisi(long crka){
        /**
         * Ex. 1
         * Given a long crka, it calls prevedi to get an
         * 8x8 binary representation of a characters and prints
         * it on screen using "*".  
         * 
         *@param crka | a long representation of a 64-bit binary value
         *               representing an 8x8 binary table. 
         *@return void 
         */
        String[]crke = prevedi(crka); 
        for(int i=0; i < crke.length; i++) {
            String[] splitLine = crke[i].split("");
            for (int n=0; n < splitLine.length; n++){
                if (splitLine[n].equals("1")) {
                    System.out.print("*");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println("");
        }
    }

    public static void presledki(long c) {
        /**
         * Helper method for printing out nothing but 
         * an 8x8 array of empty spaces. 
         * 
         * @params reduntant long integer. 
         * @returns void
         */
        for (int i=0; i < 8; i++){
            for (int n=0; n < 8; n++) {
                System.out.print(" "); 
            }
            System.out.println("");
        }
    }

    public static void izpisi2 (long crke[]) {
        /**
         * Ex. 2
         * Given an array of longs each element representing
         * an 8x8 binary table, calls prevedi on each to create 
         * a 2d array of binary Strings. Then it joins each row 
         * of all elements and prints it to screen. 
         * 
         * @param crke[] | array of longs each representing an 8x8
         *                 binary table. 
         * @return void
         */
        String[][] crkeCode = new String[crke.length][8];
        String[] crkeJoined = new String[8];
        StringBuilder sb;

        for (int i=0; i < crke.length; i++) {
            crkeCode[i] = prevedi(crke[i]);
        }

        for (int i=0; i < 8; i++){
            sb = new StringBuilder();
            for (int n=0; n < crke.length; n++) {
                sb.append(crkeCode[n][i]);
            }
            crkeJoined[i] = sb.toString(); 
        }

        for(int i=0; i < crkeJoined.length; i++) {
            String[] splitLine = crkeJoined[i].split("");
            for (int n=0; n < splitLine.length; n++){
                if (splitLine[n].equals("1")) {
                    System.out.print("*");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println("");
        }
    }

    public static long getCrko(int[] vrstice) {
        /**
         * Ex. 3
         * Given an array of integers this will bitshift 
         * all elements of the array to create a single 64-bit long
         * representation of an 8x8 binary table. 
         * 
         * @param vrstice | Array of integers, each representing a row
         *                  in an 8x8 binary table
         * @returns rezultat | long notation of bitshifted row values
         * @see 4342175383962075708=(60<<56)+(66<<48)+(129<<40)+(129<<32)+(129<<24)+(129<<16)+(66<<8)+60
         *      represents an 8x8 binary table displaying character O. 
         */
        int zamik = 56; 
        long rezultat = 0;
        for (int i=0; i < vrstice.length; i++) {
            rezultat += ((long)vrstice[i] << zamik); 
            zamik -= 8; 
        }
        return rezultat; 
    }

    public static void ime() {
        /**
         * Ex. 4
         * Prints my name using getCrko and izpisi2. 
         * @param none
         * @return void
         */
        long m = getCrko(new int[] {129, 195, 165, 153, 129, 129, 129, 129});
        long a = getCrko(new int[] {24, 36, 36, 66, 126, 66, 66, 66});
        long t = getCrko(new int[] {255, 16, 16, 16, 16, 16, 16, 16});
        long i = getCrko(new int[] { 16, 16, 16, 16, 16, 16, 16, 16});
        long c = getCrko(new int[] {255, 128, 128, 128, 128, 128, 128, 255});
        long[] crke = new long[] {m, a, t, i, c}; 
        izpisi2(crke);
        
    }

    public static void makeSpace(int n) {
        /**
         * I'm lazy, sorry. 
         * @param none
         * @return void
         */
        for(int i=0; i<n; i++) {
            System.out.print("\n");
        }
    }

    public static void naloga(String text) {
        /**
         * Looks good. 
         * @param text to be displayed
         * @return void
         */
        System.out.println("\n####################\n" + text + "\n####################\n");
    }
   

}