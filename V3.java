import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class V3 {

    public static void main(String[] args) {
        Tarok t = new Tarok(); 
        String[] k = t.ustvariKarte(); 

        /* Izpis kart */
        System.out.println("\n\n#################\n1. naloga\n#################");
        t.izpisi(k); 
        System.out.println("\n\n#################\n2. naloga\n#################");
        t.premesajKarte(k, 200);
        t.izpisi(k); 
        System.out.println("\n\n#################\n3. naloga\n#################");
        System.out.println("Rezultat preprostega štetja je: " + t.preprostoStetje(k));
        System.out.println("\n\n#################\n4. naloga\n#################");
        String[] nk = t.ustvariKarte();
        System.out.println("Rezultat natančnega štetja je: " + t.natancnoStetje(nk, true, 0, 54));
    }

}

class Tarok {
    static char pik = '\u2660'; // ♠
    static char kriz = '\u2663'; // ♣
    static char srce = '\u2665'; // ♥
    static char karo = '\u2666'; // ♦

    static char barve[] = {srce, karo, kriz, pik};
    static String prazneRdece[] = {"1", "2", "3", "4"};
    static String prazneCrne[] = {"7", "8", "9", "10"};

    static String figure[] = {"Fant", "Kaval", "Dama", "Kralj"};

    static String taroki[] = {
                "I", "II", "III", "IIII", "V", "VI", "VII",
                "VIII", "IX", "X", "XI", "XII", "XIII", "XIV",
                "XV", "XVI", "XVII", "XVIII", "XIX", "XX",
                "XXI", "SKIS" };


    public static String[] ustvariKarte() {
        /**
         * Creates a deck of tarot cards  
         * O(n^2)
         * 
         * @param /none/
         * @returns String[] ph; 
         * 
         */
    
        String[] ph = new String[54];
        StringBuilder sb = new StringBuilder(); 
        Integer indeks_counter = 0; 
        for (int znaki = 0; znaki < barve.length; znaki++) {
            for (int indeksi = 0; indeksi < prazneRdece.length + figure.length; indeksi++){
                if (znaki < 2) {
                    sb.append(barve[znaki]);
                    if (indeksi < prazneRdece.length) {
                        sb.append(prazneRdece[indeksi]);
                        ph[indeks_counter] =  sb.toString();
                    } else {
                        sb.append(figure[indeksi % (figure.length)]);
                        ph[indeks_counter] =  sb.toString();       
                    }
                } else {
                    sb.append(barve[znaki]);
                    if (indeksi < prazneRdece.length) {
                        sb.append(prazneRdece[indeksi]);
                        ph[indeks_counter] =  sb.toString();
                    } else {
                        sb.append(figure[indeksi % (figure.length)]);
                        ph[indeks_counter] =  sb.toString();    
                    }
                    sb = new StringBuilder();
                } 
                indeks_counter += 1;
                sb = new StringBuilder(); 
            }   
        } 
        int n = 0; 
        for (int i = indeks_counter; i < ph.length; i++) {
            ph[i] = taroki[n];
            n++;
        }
        return ph; 
    }   

    public static void izpisi(String[] karte){
        /**
         * Prints a block string from a string array karte
         * 
         * @param String[] karte
         * @returns /none/
         *
         */
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < karte.length; i++) {
            if (i%8==0 && i != 0) {
                sb.append("\n");
            }
            sb.append(karte[i] + " ");
            
        }
        System.out.printf(sb.toString());
    }

    public static void premesajKarte(String[] karte, int koliko) {
        /**
         * Randomizez the order of the items in list cards, 
         * by switching indexes of random elements koliko-times
         * 
         * @params 
         * String[] karte, int koliko
         * @returns /none/
         * 
         */
        String placeholder = "";
        for (int i = 0; i < koliko; i++) {
            int firstCard = ThreadLocalRandom.current().nextInt(0, karte.length);
            int secondCard = ThreadLocalRandom.current().nextInt(0, karte.length);
            placeholder = karte[firstCard]; 
            karte[firstCard] = karte[secondCard];
            karte[secondCard] = placeholder;
        }
    }

    public static Integer preprostoStetje(String[] karte) {
        /**
         * Does a simple calculation of tarot cards from karte. 
         * 
         * @params String[] karte
         * @returns int sestevek
         * 
         */
        int sestevek = 0; 
        for (int i = 0; i < karte.length; i++) {
            String k = karte[i];
            if (k.contains("Kralj") || k.equals("I") || k.equals("XXI") || k.equals("SKIS")) {
                sestevek += 5; 
            } else if (k.contains("Dama")) {
                sestevek += 4;
            } else if (k.contains("Kaval")) {
                sestevek += 3;
            } else if (k.contains("Fant")) {
                sestevek += 2; 
            } else if (k.equals("")) {
                continue; 
            } else {
                sestevek += 1; 
            }
            
        }
        return sestevek; 
    }

    public static int natancnoStetje(String[] karte, boolean izpis, int z, int k) {
        /**
         * Does a true count of tarot cards from z-th to k-1th element of karte. 
         * Groups elements by true, calls preprostoStetje and subtracts 2 from result. 
         * If less than 3 elements are left after final loop it subtracts 1 from result. 
         * 
         * @params String[] karte, boolean izpis, int z, int k
         * @returns int sestevek
         * 
         */
        StringBuilder sb = new StringBuilder(); 
        String[] brezOstalih = Arrays.copyOfRange(karte, z, k);
        if (izpis) { 
            System.out.println("Karte: "); 
            izpisi(karte);
            System.out.println("\n");
        }
        int sestevek = 0;
        int last = 0; 
        String[] temp = {"", "", ""};  
        int tempSestevek = 0;
        for (int i=0; i < brezOstalih.length; ++i) {
            if (i%3==0 && i!=0){
                for (int n=0; n < temp.length; n++){
                    sb.append(temp[n] + " ");
                }
                tempSestevek = (preprostoStetje(temp) -2);
                sestevek += tempSestevek;
                sb.append(" - " + tempSestevek + "\n");
                last = i; 
            } 

            temp[i%3] = brezOstalih[i]; 
        }
        Arrays.fill(temp, "");
        int j = 0; 
        for (int i = last; i < brezOstalih.length; i++) {
            temp[j] = brezOstalih[i]; 
            j++;
        }
        for (int n=0; n < temp.length; n++){
            sb.append(temp[n] + " ");
        } 
        if (brezOstalih.length%3 == 0) {
            tempSestevek = (preprostoStetje(temp)-2);
        } else { 
            tempSestevek = (preprostoStetje(temp) -1);
        }
        sestevek += tempSestevek;
        sb.append(" - " + tempSestevek + "\n");
        if (izpis) { 
            System.out.println(sb.toString());
        }
            return sestevek; 
    }

}