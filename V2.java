import java.lang.Math;

public class V2  {

    public static long fakultetaL(int n) {
        long rezultat = 1; 
        for (int i=1; i <= n; i++){
            rezultat *= i;     
        }
        return rezultat; 
    }

    public static long stirlingL(int n) {
        return Math.round(Math.sqrt(2 * Math.PI * n )* (Math.pow(n/Math.E, n)));
        
    }

    public static double fakultetaD(int n) {
        double rezultat = 1; 
        for (int i=1; i <= n; i++){
            rezultat *= i;     
        }
        return rezultat; 
    }

    public static double stirlingD(int n) {
        return Math.sqrt(2 * Math.PI * n )* (Math.pow(n/Math.E, n));
        
    }

    public static double napakaL(int n) {
        double odstopanje = fakultetaL(n) - (Math.sqrt(2 * Math.PI * n )* (Math.pow(n/Math.E, n))); 
        return 100*(odstopanje)/(double)fakultetaL(n); 
    }

    public static double napakaD(int n) {
        return 100*(fakultetaD(n) - stirlingD(n))/(double)fakultetaD(n);
    }

    public static void printL(){
        System.out.println("  n            n!               Stirling(n)         napaka (%)\n--------------------------------------------------------------");
        for(int i=1; i <= 20; i++) {
            System.out.printf("%3d%21d%21d%16.7f\n", i, fakultetaL(i), stirlingL(i), napakaL(i));

        }
    }

    public static void printD() {

        System.out.println("  n            n!               Stirling(n)         napaka (%)\n--------------------------------------------------------------");
        for(int i=1; i <= 100; i++) {
            System.out.printf("%3d%21e%21e%16.7f\n", i, fakultetaD(i), stirlingD(i), napakaD(i));

        }
    }

    public static void main(String[] args) {
        printL();
        System.out.println("\n\nZadnja izpisljiva fakulteta tipa long je fakulteta števila 46: " + fakultetaL(46));
        printD();
    }

}