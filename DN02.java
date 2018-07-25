public class DN02 {
    public static String znaki (int n, String znak) {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < n; i++ ){
            s.append(znak);
        };
        String niz = s.toString();
        return niz;
        
    }
    public static void pravokotnik(int a, int b) {
        String sprem = "a = " + a + " , b = " + b + "    "; 
        String presledki = znaki(sprem.length(), " ");
        for (int i=0; i<a; i++) {
            if (i==0){  
                System.out.println(sprem + znaki(b, "X"));
            } else {
                System.out.println(presledki + znaki(b, "X"));
            }
        }
    }



    public static void main(String[] args) {
        if (args.length == 0){
            for (int i=1; i < 6; i++) {
                for (int n=1; n < 6; n++) {
                    pravokotnik(i, n); 
                    System.out.println();
                }
            }
        } else {
            int a = Integer.parseInt(args[0]);
            int b = Integer.parseInt(args[1]); 
            pravokotnik(a, b);
        }
    }
}


