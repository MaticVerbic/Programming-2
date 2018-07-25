import java.util.Locale;


public class DN03 {

    public static double[] todouble(String[] args){
        /* 
        Casts all elements of array String to array of doubles        
        */
        double[] doubles = new double[args.length-1];
        for (int i = 1; i < args.length; i++){
            try { 
                /*if arg is not double, skip arg*/
                doubles[i-1] = Double.parseDouble(args[i]);
            }
            catch (Exception e) { 
                continue;
            }
        }
        return doubles;
    }

    public static String stevila(String[] args){
        /*All args to a single string*/
        String str = "";
        for (int i = 1; i < args.length; i++){
            str += args[i] + " ";             
        }
        return str;
    }



    public static void main(String[] args){
        Locale.setDefault(Locale.ENGLISH);

        int len = args.length; 
        /*Error handling for 0 arguments*/
        if (len == 0) {
            System.out.println("Vnesite argumente!");
            System.exit(0);
        } 

        double dbargs[] = todouble(args); 
        
        switch (args[0]) {
            case "1": 
                /* Addition */
                if (len != 3 || dbargs.length != 2) {
                    /*error*/
                     System.out.println("Za operacijo sestevanja sta potrebna 2 argumenta!");
                } else {
                    System.out.printf("%s + %s = %s\n", dbargs[0], dbargs[1], dbargs[0]+dbargs[1]); 
                }
                break;
            case "2": 
                /* Subtraction */
                if (len != 3 || dbargs.length != 2) {
                     /*error*/
                     System.out.println("Za operacijo sestevanja sta potrebna 2 argumenta!");
                } else {
                    System.out.printf("%s - %s = %s\n", dbargs[0], dbargs[1], dbargs[0]-dbargs[1]); 
                }
                break;
            case "3": 
                /* Multiplication */
                if (len != 3 || dbargs.length != 2) {
                     /*error*/
                     System.out.println("Za operacijo sestevanja sta potrebna 2 argumenta!"); 
                } else {
                    System.out.printf("%s * %s = %s\n", dbargs[0], dbargs[1], dbargs[0]*dbargs[1]); 
                }
                break;
            case "4": 
                /* Division */
                if (len != 3 || dbargs.length != 2) {
                     /*error*/ 
                     System.out.println("Za operacijo sestevanja sta potrebna 2 argumenta!");
                } else if (dbargs[1] == 0) {
                    /*division by 0*/
                    System.out.println("Deljenje z 0 ni dovoljeno!");
                } else {
                    System.out.printf("%s / %s = %s\n", dbargs[0], dbargs[1], dbargs[0]/dbargs[1]); 
                }
                break;
            case "5": 
                /*dbargs.min()* - finds lowest value*/
                Boolean ints = true; 
                double currentmin = dbargs[0]; 
                for (double item : dbargs){                 
                    if (item <= currentmin) {
                        currentmin = item;
                    }
                    if (item % 1 != 0) {
                        ints = false; 
                    }
                }
                if (ints) {
                    /* if all values are ints */
                    Integer newmin = (int)currentmin;
                    String st = stevila(args);
                    System.out.println("Minimum stevil " + st + "je " + newmin); 
                } else {
                    /* if not all values are ints */
                    String st = stevila(args);
                    System.out.println("Minimum stevil " + st + "je " + currentmin); 
                }
                break;

        } 
    }
}