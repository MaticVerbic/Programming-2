import java.util.Scanner;
import java.io.File;
import java.lang.Math;

public class DN08 {
    public static void main(String[] args) throws Exception {
       Planet[] planeti = preberi(args[0]);
        /*for(int i=0; i < planeti.length; i++) {
           if (planeti[i] == null) {break;
           } else {
               planeti[i].izpisi();
           }
        } */
       
        if (args.length > 1) {
            if (args[1].length() > 0) {
                System.out.printf("Povrsina planetov \"%s\" je %s milijonov km2", args[1], (Math.round(skupPov(planeti, args[1])* 0.000001)));
            }
        }
    }

    public static Double skupPov(Planet[] planeti, String arg){
        Double skupnaPovrsina = 0.0;
            String lower = arg.toLowerCase();
            if (lower.contains("+")) {
                String[] spl = lower.split("\\+");
                for(int i=0; i < spl.length; i++) {
                    for(int n=0; n < planeti.length; n++) {
                        if(spl[i].equals(planeti[n].getName().toLowerCase())) {
                            skupnaPovrsina += (planeti[n].povrsina());
                        }
                    }
                }
            } else {
                for (int i=0; i<planeti.length; i++) {
                    if(lower.equals(planeti[i].getName().toLowerCase())){
                        return planeti[i].povrsina();
                    }
                }
            }
        return skupnaPovrsina;
    }



    public static Planet[] preberi(String vir) throws Exception {
        Scanner sc = new Scanner(new File(vir));
        int i = 0;
        Planet[] planeti = new Planet[9];
        while(sc.hasNextLine()){
            String line = sc.nextLine(); 
            if (!line.contains(":")){continue;}
            else {
                String[] spl = line.split(":");
                planeti[i] = new Planet(spl[0], Integer.parseInt(spl[1]));
                i++;
            }
        }
        return planeti;    
    }



}


class Planet {
    String ime;
    int radij;

    public Planet(String ime, int radij) {
        this.ime = ime;
        this.radij = radij;
    }

    public Double povrsina() {
        return 4 * Math.PI * Math.pow(this.radij, 2);
    }


    public String getName(){
        return this.ime;
    }


    public void izpisi(){
        System.out.println("------------\n" + this.ime + " - " + this.radij + " - povrsina: " + povrsina());
    }
}