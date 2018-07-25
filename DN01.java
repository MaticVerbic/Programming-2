public class DN01 {

    private static String generate(int n) {
        StringBuilder s = new StringBuilder();

        for (int i = 0; i < n; i++ ){
            s.append("*");
        };
        String niz = s.toString();
        return niz; 
    };

    public static void main(String[] args) {
	// write your code here 
        System.out.println("Moje ime je: Matic Verbič\nMoja vpisna številka: 63170302\n");
        System.out.println(generate(50));
        System.out.println("* Izjavljam, da sem prebral vsebino strani       *\n" +
                           "* 'Samostojna izdelava programov' na eUčilnici.  *\n" +
                           "* Vse obveznosti predmeta Programiranje 2 bom    *\n" +
                           "* skladno s temi navodili opravil samostojno.    *"); /** z dodan za testiranje */
        System.out.println(generate(50));
    }
}


