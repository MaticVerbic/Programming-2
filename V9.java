import java.util.Scanner; 
import java.io.File; 
import java.lang.StringBuilder;


public class V9 {
    public static void main(String[] args) throws Exception {
        Komponenta[] k = preberi(args[0]); 
        System.out.println(cenik(k));  
        //System.out.println(cpumobo(k));     
    }

    public static Komponenta[] preberi(String filename) throws Exception{
        Komponenta[] k = new Komponenta[100];
        Scanner sc = new Scanner(new File(filename)); 
        int i = 0; 
        while(sc.hasNextLine()){
            String[] split = sc.nextLine().split(";");
            
            switch(split[0]) {
                case "Pomnilnik": 
                    k[i] = new Pomnilnik(split[1], Double.parseDouble(split[2]), Integer.parseInt(split[3]), Integer.parseInt(split[4]), Integer.parseInt(split[5])); 
                    i++; 
                    break; 
                case "Maticna": 
                    k[i] = new MaticnaPlosca(split[1], Double.parseDouble(split[2]), split[3], split[4], Integer.parseInt(split[5]));        
                    i++; 
                    break;
                case "Procesor": 
                    k[i] = new Procesor(split[1], Double.parseDouble(split[2]), split[3], Integer.parseInt(split[4]), Integer.parseInt(split[5]));        
                    i++; 
                    break;
                default: 
                    System.out.println("Error when reading file, type not found at line:" + i + ", component: " + split[0]);       
            }
        }
        return k; 
    }

    public static String cenik(Komponenta[] k) {
        StringBuilder sb = new StringBuilder(); 
        for (int i=0; i<k.length; i++) {
            if (k[i] == null) {
                break; 
            } else {
                sb.append(k[i].toString() + "\n\n");
            }
        }
        return sb.toString(); 
    }

    /*public static String cpumobo(Komponenta[] k) {
        StringBuilder sb = new StringBuilder(); 
        for(int i=0; i<k.length; i++) {
            if (k[i] == null) {
                break; 
            } else if(k[i] instanceof Procesor) {
                sb.append(k[i].getName() + " " + k[i].getPodnozje() +" is compatible with: ");
                for(int j=0; j<k.length; j++) {
                    if (k[j] == null) {
                        break; 
                    } else if(k[i] instanceof MaticnaPlosca && k[i].getPodnozje().equals(k[j].podnozje)){
                        sb.append(k[j].getName()); 
                    }
                sb.append("\n\n");
                }
            }
        }
        return sb.toString(); 
    }*/
}



class Komponenta {
    String ime; 
    double cena; 
    int popust; 

    public Komponenta(String ime, double cena, int popust){
        this.ime = ime; 
        this.cena = cena; 
        this.popust = popust;
    }

    public String toString(){
        return "Ime: " + this.ime + " Cena: " + String.format("%1$,.2f", cenaSPopustom());
    }

    public double cenaSPopustom(){
        return this.cena - (this.cena * ((double)this.popust/100)); 
    }

}

class Pomnilnik extends Komponenta{
    int tip; 
    int hitrost; 
    int velikost; 

    public Pomnilnik(String ime, double cena, int tip, int hitrost, int velikost){
        super(ime, cena, 10); 
        this.tip = tip;
        this.hitrost = hitrost; 
        this.velikost = velikost; 
    }

    public String toString(){
        return super.toString() + " Tip: " + this.tip + " Hitrost: " + this.hitrost + " Velikost: " + this.velikost; 
    }
}

class MaticnaPlosca extends Komponenta{
    String format; 
    String podnozje; 
    int steviloPomnilniskihRez; 

    public MaticnaPlosca(String ime, double cena,  String format, String podnozje, int steviloPomnilniskihRez){
        super(ime, cena, 15); 
        this.format = format; 
        this.podnozje = podnozje; 
        this.steviloPomnilniskihRez = steviloPomnilniskihRez; 
    }


    public String getName(){
        return this.ime;
    }

    public String getPodnozje(){
        return this.podnozje;
    }

    public String toString() {
        return super.toString() + " Format: " + this.format + " Podnozje: " + this.podnozje + " Stevilo Rez: " + this.steviloPomnilniskihRez; 
    }

}

class Procesor extends Komponenta {
    String podnozje;
    int steviloJeder;
    int hitrostJedra;

    public Procesor(String ime, double cena, String podnozje, int steviloJeder, int hitrostJedra) {
        super(ime, cena, 5); 
        this.podnozje = podnozje; 
        this.steviloJeder = steviloJeder; 
        this.hitrostJedra = hitrostJedra; 
    }

    public String getName(){
        return this.ime;
    }

    public String getPodnozje(){
        return this.podnozje;
    }


    public String toString(){
        return super.toString() + " Podnozje: " + this.podnozje + 
        " SteviloJeder: " + this.steviloJeder + " Hitrost: " + this.hitrostJedra; 
    }

}

