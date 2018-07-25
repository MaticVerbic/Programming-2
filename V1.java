public class V1 {
    public static String znaki (int n, String znak) {
        String s = "";
        for (int i = 0; i < n; i++ ){
            s += znak;
        };
        return s;
        
    }

    static void pravokotnik(int odmik, int visina, int sirina){
        String presledki = znaki(odmik, " "); 
        for (int i=0; i < visina; i++) {
            System.out.println(presledki + znaki(sirina, "*"));
        }
    }

    static void trikotnik(int odmik, int visina){
        for(int i=0; i< visina; i++){
            String presledki = znaki((odmik+(visina-i)-1), " "); 
            System.out.println(presledki + znaki(2*(i)+1, "*"));

        }
    }

    static void okvir(int odmik, int visina, int sirina) {
       String presledki = znaki(odmik, " ");
       String a = znaki(sirina, "*"); 
       String b = "*" + znaki(sirina-2, " ") + "*"; 
       System.out.println(presledki + a);
       for (int i=0; i+2 < visina; i++){
           System.out.println(presledki + b); 
       } 
       System.out.println(presledki+a); 

    }

    static void okvir2(int odmik, int visina, int sirina) {
       String presledki = znaki(odmik, " ");
       String a = znaki(sirina, "*"); 
       String b = "*" + znaki(sirina-2, " ") + "*"; 
       /*System.out.println(presledki + a);*/
       for (int i=0; i+2 < visina; i++){
           System.out.println(presledki + b); 
       } 
       System.out.println(presledki+a); 

    }
    static void hisa(int odmik, int visina_t, int visina_o){
        trikotnik(odmik, visina_t); 
        okvir2(odmik+2, visina_o, 2*(visina_t)-5); 
    }

    static void dvaPravokotnika(int odmik, int razmik, int visina, int sirina){
        String presledki = znaki(odmik, " ");
        String prav = znaki(sirina, "*");
        String raz = znaki(razmik, " "); 
        for(int i=0; i < visina; i++) {
            System.out.println(presledki+prav+raz+prav); 
        }
    }

    static void raketa(){
        trikotnik(2, 5); 
        pravokotnik(2, 11, 9);
        dvaPravokotnika(3, 3, 10, 2);
    }

    static void X(int n){
        for(int i=1; i <= n; i++){
            if (i == n) {
                pravokotnik(n*5-5, 3, 5);     
            } else if (i < n) {
                dvaPravokotnika((i-1)*5, ((n-i)*10)-5, 3, 5); 
            }
        }
        for (int j=n; j > 1; j--){
            dvaPravokotnika((j-2)*5, ((n-j)*10)+5, 3, 5);
        }
    }



    public static void main(String[] args){   
        
        pravokotnik(5, 3, 7);
        trikotnik(1, 5);
        okvir(2, 4, 15);
        hisa(5, 10, 15);
        System.out.println();
        dvaPravokotnika(5, 15, 3, 6); 
        raketa(); 
        X(8);
    }

}