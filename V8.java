import java.util.Scanner;
import java.io.File;


public class V8 {
    public static void main(String[] args) throws Exception {
        Imenik imen = new Imenik(args[0]);
        imen.izpisiImenik();    
        Oseba najdeno = imen.isciOseboPoImenu("Maja"); 
        System.out.println(najdeno);
        System.out.println(imen.isciOseboPoPosti("1000"));
    }
}

class Imenik {
    String vir;
    Oseba[] imenik;
    Oseba[] osebe;

    public Imenik(String vir) throws Exception{
        this.vir = vir;
        this.osebe = this.preberiOsebe(vir);
    }
    public Oseba[] preberiOsebe(String vir) throws Exception {
        /**
         * Reads file and creates an array of Oseba objects
         * 
         * @param vir | path to file 
         * @return imenik 
         */
        imenik = new Oseba[100];
        Oseba os;
        Scanner sc = new Scanner(new File(vir));
        int i = 0;
        while(sc.hasNextLine()){
            String[] spl = sc.nextLine().split(", ");
            os = new Oseba(spl[0], spl[1], spl[2], spl[4], spl[3], spl[5]);
            imenik[i] = os;
            i++;
        }
        return imenik; 
    }

    public void izpisiImenik() throws Exception{
        /**
         * Prints the entire address book
         * 
         */
        System.out.println("\n\n******IMENIK******\n---------------------");
        for(int i=0; i<this.osebe.length;i++) {
            if (this.osebe[i] == null){
                return;
            } else {
                this.osebe[i].izpisi();
                System.out.println("---------------------");
            }
        }
    }
    
    public Oseba isciOseboPoImenu(String ime){
        /**
         * Searches for people by name
         * 
         * @param name
         * @return first occurance of name
         */
        for (int i=0; i < this.osebe.length; i++) {
            if (osebe[i] == null) {
                return null; 
            }
            if (this.osebe[i].ime.equals(ime)) {
                return this.osebe[i];
            }
        }
        return null; 
    }


    public String isciOseboPoPosti(String posta) {
        /**
         * Searches people in address by post number
         * 
         * @param posta 
         * @return all people that live in a area code
         */
        StringBuilder sb = new StringBuilder();
        int counter = 0;
        for (int i=0; i < this.osebe.length; i++) {
            if (osebe[i] == null) {
                break; 
            }
            if (this.osebe[i].posta.equals(posta)) {
                counter++; 
                sb.append(this.osebe[i].ime + " " + this.osebe[i].priimek + "\n");
            }
        }
        return "V naselju z poštno številko " + posta + " zivi " + counter + " oseb. \n" + sb.toString();
    }

}


class Oseba {
    String ime;
    String priimek;
    String naslov;
    String mesto;
    String posta;
    String telefon;

    public Oseba(String ime, String priimek, String naslov, String mesto, String posta, String telefon) {
        //constructor
        this.ime = ime; 
        this.priimek = priimek; 
        this.naslov = naslov; 
        this.mesto = mesto; 
        this.posta = posta;
        this.telefon = telefon; 
    }

    public void izpisi(){
        /**
         * Prints contact 
         * 
         * name lastname
         * phone
         * address
         * postnumber town
         */
        System.out.printf("%s %s\n%s\n%s, \n%s %s\n", ime, priimek, telefon, naslov, posta, mesto);
    }


}