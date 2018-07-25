import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.management.RuntimeErrorException;

public class DN09 {


    public static void main(String[] args) throws Exception{
        int i = 0;
        switch (args[0]) {
            case "dateTime": 
                System.out.println(new DateTime(args[1] + " " + args[2]));
                break;
            case "candle":
                System.out.println(new Candle(args[1] + " " + args[2], args[3], args[4], args[5], args[6]));
                break;
            case "movingAverage":
                System.out.printf("%7f", new FinancialInstrument(args[2]).movingAverage(new DateTime(args[3]+" "+args[4]), args[1]));
                break;
            case "najvisja":
                System.out.printf("Najvisja vrednost za dani interval: %7f", new FinancialInstrument(args[1]).maxValue(new DateTime(args[2] + " " + args[3]), new DateTime(args[4] + " " + args[5])));
                break;
            case "najnizja":
                System.out.printf("Najnizja vrednost za dani interval: %7f", new FinancialInstrument(args[1]).minValue(new DateTime(args[2] + " " + args[3]), new DateTime(args[4] + " " + args[5])));
                break;
            case "nonBusiness":
                System.out.println("Trgovanja prostih dni: " + new CurrencyPair(args[1]).nonBusinessDays(new DateTime(args[2] + " " + args[3]), new DateTime(args[4] + " " + args[5])));
                break;
            case "masterCandles":
                DateTime dt1 = new DateTime(args[2] + " " + args[3]);
                DateTime dt2 = new DateTime(args[4] + " " + args[5]);
                int s = new CurrencyPair(args[1]).masterCandles(dt1, dt2);
                System.out.println("Med " + dt1 + " in " + dt2 + "je bilo " + String.valueOf(s) + ((s > 2) ? " master sveck. " : (s == 2) ? " master svecki. " : (s == 1) ? " master svecka. " : " master sveck. "));
                break;
            case "convert":
                System.out.printf("Kriptovaluta '" + args[1].substring(0, 3) + "' je bila " + new DateTime(args[3] + " " + args[4]) + " vredna %.3f", new CryptoCurrency(args[1]).convert(new CurrencyPair(args[2]), new DateTime(args[3] + " " + args[4])));
                break;
            case "izris":
                break;
            default: 
                throw new RuntimeException("Unrecognized command");
        }
    
    }

}

//Datetime Class
class DateTime {
    /**
     * What's the object-oriented way to become wealthy?
     * 
     * Inheritance
     */

    private String leto;
    private String mesec;
    private String dan;
    private String ura;
    private String minuta;
    private String dt;
    private String vStamp;
    private String[] splt;
    public Date date;
    private SimpleDateFormat smdf;

    //constructor
    public DateTime(String dt) throws Exception{
        this.splt = split(dt); //split 
        if (validate(splt)) { //validate
            this.leto = splt[0];
            this.mesec = splt[1];
            this.dan = splt[2];
            this.ura = splt[3];
            this.minuta = splt[4];
            this.dt = dt;
            this.vStamp = splt[5];
            this.smdf = new SimpleDateFormat("YYYY:MM:DD:HH:mm");
            this.date = smdf.parse(this.leto + ":" + this.mesec + ":" + this.dan + ":" + this.ura + ":" + this.minuta);
        } else { //error
            throw new RuntimeException("Unexpected DateTime error occured. ");
            
        }
    }

    //getters
    public String getStamp(){
        return this.vStamp;
    }

    //helper methods
    private String[] split(String dt) {
        String[] st = new String[6];
        st[0] = dt.split(" ")[0].split("\\.")[0];
        st[1] = dt.split(" ")[0].split("\\.")[1];
        st[2] = dt.split(" ")[0].split("\\.")[2];
        st[3]= dt.split(" ")[1].split(":")[0];
        st[4]= dt.split(" ")[1].split(":")[1];
        //timestamp for testing
        st[5] = String.valueOf(Long.parseLong(st[0] + st[1] + st[2] + st[3] + st[4])); 
        return st;
    }

    private boolean validate(String[] st) throws Exception{
        /**
         * This method validates given DateTime and raises a runtime exception if one occurs. 
         * returns True if all is well. 
         * 
         * 
         */
        for(int i=0; i<st.length; i++){
            switch(i) {
                case 0: 
                    //year
                    if (!(Integer.parseInt(st[0]) > 0 && Integer.parseInt(st[0]) <= 9999)){
                        throw new RuntimeException("Year out of bounds!");
                    }
                    break;
                case 1: 
                    //month
                    if (!(Integer.parseInt(st[1]) > 0 && Integer.parseInt(st[1]) <= 12)){
                        throw new RuntimeException("Month out of bounds!");
                    }
                    break;
                case 2: 
                    //day
                    List<String> enaInTrideset = Arrays.asList("01", "03", "05", "07", "08", "10", "12");
                    List<String> trideset = Arrays.asList("04", "06", "09", "11");
                    
                    if (st[1].equals("02") 
                    && (Integer.parseInt(st[0])%4==0 && (Integer.parseInt(st[0])%100!=0 && Integer.parseInt(st[0])%400==0)) 
                    && !(Integer.parseInt(st[2]) > 0 && Integer.parseInt(st[2]) <= 29)) {
                        //February leap year
                    
                        throw new RuntimeException("Month out of bounds - Leap year - February");     
                    
                    }  else if (st[1].equals("02") 
                    && !(Integer.parseInt(st[0])%4==0 && (Integer.parseInt(st[0])%100!=0 && Integer.parseInt(st[0])%400==0)) 
                    && !(Integer.parseInt(st[2]) > 0 && Integer.parseInt(st[2]) <= 28)) {
                        //February non leap year
                    
                        throw new RuntimeException("Month out of bounds - Non-leap year - February");
                    
                    } else if(trideset.stream().anyMatch(str -> str.trim().equals(st[1])) 
                    && !(Integer.parseInt(st[2]) > 0 && Integer.parseInt(st[2]) <= 30)){ 
                        //Thirty day months

                        throw new RuntimeException("Month out of bounds - 30 day month");
                    
                    } else if(enaInTrideset.stream().anyMatch(str -> str.trim().equals(st[1])) 
                    && !(Integer.parseInt(st[2]) > 0 && Integer.parseInt(st[2]) <= 31)) {
                        //Thirtyone day months

                        throw new RuntimeException("Month out of bounds - 31 day month");
                    } 
                    break;
                case 3: 
                    //hours
                    if (!(Integer.parseInt(st[3]) >= 0 && Integer.parseInt(st[3]) <= 24)){
                        throw new RuntimeException("Hour out of bounds!");
                    }
                    break;
                case 4: 
                    //minutes
                    if (!(Integer.parseInt(st[4]) >= 0 && Integer.parseInt(st[4]) <= 59)){
                        throw new RuntimeException("Minute out of bounds!");
                    }
                    break;
            }
        }

        return true;
    }

    public long getDifference(DateTime dt) {
        //a little cheat. look away
        return (this.date.getTime() - dt.date.getTime()) / 1000;
    }

    //overrides
    public String toString() {
        return String.valueOf(Integer.parseInt(splt[2])) + ". " + String.valueOf(Integer.parseInt(splt[1])) + ". " + splt[0] + " " + splt[3] + "." + splt[4];
    }

    //task methods 
    public boolean isGreater(DateTime dt){
        //if this > dt return true else false
        return Long.parseLong(this.vStamp) > Long.parseLong(dt.getStamp());
    }

    public boolean isLower(DateTime dt) {
        //if this < dt return true else false
        return Long.parseLong(this.vStamp) < Long.parseLong(dt.getStamp());
    }

    public boolean isEqual(DateTime dt) {
        //if this == dt return true else false
        return Long.parseLong(this.vStamp) == Long.parseLong(dt.getStamp());
    }

}

//Candle class
class Candle {
    /**
     * What is a programmer's favourite place? 
     * 
     * Foo bar
     */

    private DateTime dateTme;
    private double open;
    private double high;
    private double low;
    private double close;

    //constructor
    public Candle(String dt, String open, String high, String low, String close) throws Exception {
        this.dateTme = new DateTime(dt);
        this.open = Double.parseDouble(open);
        this.high = Double.parseDouble(high);
        this.low = Double.parseDouble(low);
        this.close = Double.parseDouble(close);
    }

    //overrides
    public String toString() {
        return this.dateTme + ": " + average();
    }

    //getters
    public double getOpen(){
        return this.open;
    }
    
    public double getClose(){
        return this.close;
    }
    
    public double getHigh(){ //420 blaze it maaaan.
        return /*home*/ this.high /*and eat everything*/;
        //then go to sleep for a day or three 
    }
    
    public double getLow(){
        return this.low;
    }

    public DateTime getDT() {
        return this.dateTme;
    }

    //task methods
    public double average(){
        //returns average of open, high, low and close values
        return (this.open + this.high + this.low + this.close)/4;
    }

    public boolean isBullish(){
        //if this.open < this.close then return true otherwise return false
        return (Double.compare(this.open, this.close) < 0) ?  true :  false;
    }

    public boolean isBearish(){
        //if this.open > this.close then return true otherwise return false
        return (Double.compare(this.open, this.close) > 0) ?  true :  false;
    }

}

//FinancialIntstrument class
class FinancialInstrument {
    /**
     * Why did a programmer quit his job?
     * 
     * He didn't get arrays
     *
     * #I'll see myself out
     */

    protected Candle[] candles; 
    private String currency1;
    private String currency2;

    //constructor overload
    public FinancialInstrument(String filename, String currency1, String currency2) throws Exception{
        this.candles = read(filename);
        this.currency1 = currency1;
        this.currency2 = currency2;
    }

    public FinancialInstrument(String filename) throws Exception {
        this.candles = read(filename); 
        this.currency1 = filename.substring(0,3);
        this.currency2 = filename.substring(3,6);
    }
    
    //getters 
    public Candle[] getCandles(){
        return this.candles;
    }
    
    //task methods
    public double movingAverage(DateTime dateTime, String windowSize) {
        /**
         * Returns an average value of average values of windowSize amount of candles
         * before the given dateTime (dateTime inclusive)
         * 
         * @param dateTime
         * @param windowSize
         * @return double
         * 
         */
        int i = find_index(dateTime);
        double sum = 0; 
        double avg = 0; 
        if (i >= 0) {
            Candle[] cd = collectCandles(i, Integer.parseInt(windowSize));
            avg = calculateAverage(cd); 
            
        }
        return avg;
    }

    public double maxValue(DateTime dt1, DateTime dt2) throws Exception {
        /**
         * Returns the highest high value of all stored candles between 
         * dt1 and d2
         * 
         * @param dt1 
         * @param dt2
         * @return double
         * 
         */
        Candle[] cd = collectCandlesByIndex(find_index(dt1), find_index(dt2));
        double max;
        if (cd.length > 1) {
            max = cd[0].getHigh();
            for (Candle c : cd) {
                if (c.getHigh() > max){
                    max = c.getHigh();
                }
            }
            return max;
        } else if (cd.length == 1) {
            return cd[0].getHigh();
        } else {
            return 0;
        }
    }

    public double minValue(DateTime dt1, DateTime dt2) throws Exception {
        /**
         * Returns the smallest low value of all stored candles between 
         * dt1 and d2
         * 
         * @param dt1 
         * @param dt2
         * @return double
         */
        Candle[] cd = collectCandlesByIndex(find_index(dt1), find_index(dt2));
        double min;
        if (cd.length > 1) {
            min = cd[0].getLow();
            for (Candle c : cd) {
                if (c.getLow() < min){
                    min = c.getLow();
                }
            }
            return min;
        } else if (cd.length == 1) {
            return cd[0].getLow();
        } else {
            return 0;
        }
    }

    //helpers
    protected int find_index(DateTime dateTime) {
        /**
         * Finds a candle that matches or is first lower than given DateTime object
         * 
         * @param DateTime dateTime
         * @return int i 
         * @except Raises exception if neither are found
         */
        for(int i=0; i < candles.length; i++) {
            if (dateTime.isEqual(this.candles[i].getDT())) {
                return i;
            }
            if (i == candles.length -1 ) {
                return i;
            }
        }
        //obsolete, but kept for the sake of backwards compatibility, no touchy touchy. 
        for(int i=0; i < candles.length; i++) {
            if (dateTime.isGreater(this.candles[i].getDT())) {
                return i;
            }
        }
        throw new RuntimeException("Failed to find index of candle - FinancialInstrument.find_index()");
    }

    private double calculateAverage(Candle[] cd) {
        //calculates an average of closed values of given candles
        double sum = 0; 
        for(Candle c : cd) {
            sum += c.getClose();
        }
        return sum/cd.length;
        
    }

    private boolean validateWindowSize(int index, int windowSize) {
        //validates that windowsize can be captured
        return (windowSize < candles.length && (index - windowSize >= 0)) ? true : false; 
    }
    
    private Candle[] collectCandles(int index, int windowSize) {
        /**
         * Collects given amount of candles. 
         * formula for first index is index - windowSize + 1. 
         * Collects windowSize(amount) elements from first index
         * 
         * @param int index -> index returned by find_index()
         * @param windowSize -> amount of elements to collect
         * @return cd
         */
        if (validateWindowSize(index, windowSize)) {

            Candle[] cd = new Candle[windowSize];
            int i = index - windowSize+1;
            int c = 0;
            while(i <= index) {
                cd[c] = this.candles[i];
                i++;
                c++;
            }
            return cd;
        } else {
            throw new RuntimeException("Window Size too big!");
        }

    }   
    
    private Candle[] collectCandlesByIndex(int index1, int index2) throws Exception {
        /**
         * Returns an array of Candle objects from this.candles between 
         * index1 and index2. 
         * 
         * @param index1
         * @param index2
         * 
         * @return cd
         * 
         */
        Candle[] cd;
        int c = 0;
        if (index1 < index2) {
            cd = new Candle[index2 - index1+1];
            for(int i = index1; i <= index2; i++) {
                cd[c] = this.candles[i];
                c++;
            }
            return cd;
        } else if (index1 > index2) {
            cd = new Candle[index1 - index2+1];
            for(int i = index2; i <= index1; i++) {
                cd[c] = this.candles[i];
                c++;
            }
            return cd;
        } else if (index1 == index2) {
            cd = new Candle[1];
            cd[0] = this.candles[index1];
            return cd;
        } else {
            throw new RuntimeException("collectCandlesByIndex() error");
        }
    }

    private Candle[] read(String file) throws Exception {
        /**
         * Reads file, returns Candle[] newCandles
         */
        Scanner sc = new Scanner(new File(file));
        String[] spl;
        String s;
        Candle[] candles = new Candle[32 * 24 * 60];
        int i = 0;
        while(sc.hasNextLine()){
            spl = sc.nextLine().split(",");
            candles[i] = new Candle(spl[0] + " " + spl[1], spl[2], spl[3], spl[4], spl[5]);
            i++;

        }
        Candle[] newCandles = new Candle[i];
        System.arraycopy(candles, 0, newCandles, 0, i);
        sc.close();
        return newCandles;
       
    }

    public void izris(){
        System.out.println("Maybe later if there's time. ");
    }
    
}

//CurrencyPair class
class CurrencyPair extends FinancialInstrument {
    /**
     * What did the router say to the doctor? 
     * 
     * It hurts when IP
     */

    //constructor overload
    public CurrencyPair(String filename, String currency1, String currency2) throws Exception {
        super(filename, currency1, currency2);
    }

    public CurrencyPair(String filename) throws Exception{
        super(filename);
    }

    //task methods
    public int nonBusinessDays(DateTime dt1, DateTime dt2){
        /**
         * Returns an amount of times, a 6hour brake has been taken, call that day off day. 
         * 
         * @param dt1
         * @param dt2
         * @return count
         * 
         */
        int count = 0; 
        int i1 = find_index(dt1);
        int i2 = find_index(dt2);
        for(int i=i1; i <= i2; i++ ) {
            if (this.candles.length == i + 1)  {
                break;
            }
            if ( this.candles[i].getDT().getDifference(this.candles[i+1].getDT()) <= -21599 ) {        
                    count++;
            } 
        }
    
        return count;
    }

    public int masterCandles(DateTime dt1, DateTime dt2) {
        /**
         * Returns an amount of master candles between dt1 and dt2. 
         * 
         * Master candle = candle whose highest value is higher than next 4 candle's highest values 
         * and at the same time low is lower than next 4 candles lowest value
         */
        int count = 0;
        int ij = 0; 
        for(int i=find_index(dt1); i <= find_index(dt2); i++ ) {
            if (this.candles.length <= i)  {
                break;
            }
            int c = 0;
            for(int n=1; n <= 4; n++) {
                if(Double.compare(this.candles[i].getHigh(), this.candles[i+n].getHigh()) > 0 && Double.compare(this.candles[i].getLow(), this.candles[i+n].getLow()) < 0){
                    c++;
                }
            }
            if (c == 4) {
                count++;
            }
            c = 0;
            ij++;
        }
        return count;
    }
        
}

class CryptoCurrency extends FinancialInstrument {



    public CryptoCurrency(String filename) throws Exception {
        /**
         * Why did a blood sell all of his bitcoin stock?
         * It was a form of CRIPtocurrency
         * 
         */

        super(filename, "USD", filename.substring(0, 3));
    }

    protected int find_by_index(DateTime dt){
        /**
         * Cheater method
         * returns index of candle to convert
         */
        int in = 0;
        int l = this.candles.length;
        for(int i=0; i < l; i++){
            if (this.candles[i].getDT().isLower(dt) || this.candles[i].getDT().isEqual(dt)){
                in++;
            }
        }
        if (this.candles[in-1].getDT().isLower(dt)){
            return in-1;
        } else {
            return in;
        }
    }
    public double convert(CurrencyPair cp, DateTime dt) {
        /**converts btc to currency2 */
        int i = find_by_index(dt);
        int ic = cp.find_index(dt);
        return this.candles[i].getClose() / cp.candles[ic].getClose();
    }
}