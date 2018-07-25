import java.util.Scanner;
import java.io.File;
import Prikaz;

public class DN13 {

    public static void main(String[] args){
       try {
        Tocka t = new Tocka(0, 255, 126);
        Tocka t1 = new Tocka(150, 255, 10);
        Tocka t2 = new Tocka(150);
        Tocka t3 = new Tocka(255);
        System.out.println(t + " " + t1 + " " + t2 + " " + t3);
       } catch (ColorError e) {
            System.out.println(e);
       }
    }

    public static Slika readImagePublic(String path){
        try {
            Scanner sc = new Scanner(new File(path));
            
            String[] topLine;
            boolean color = true;
            Integer width = 0;
            Integer height = 0;
            Tocka[][] pixles = new Tocka[0][0];
            if (sc.hasNextLine()){
                topLine = sc.nextLine().split(" ");
                if (topLine[0] == "P2TC") {
                    color = true;
                } else {
                    color = false;
                }
                width = Integer.parseInt(topLine[1].split("x")[0]);
                height = Integer.parseInt(topLine[1].split("x")[1]);
                int widthCounter = 0;
                int heightCounter = 0;
                pixles = new Tocka[width][height];
                if (color) {
                    while(sc.hasNext()){
                        if (widthCounter == width) {
                            widthCounter = 0;
                            System.out.println("width error");
                        }
                        if (heightCounter == height) {
                            heightCounter = 0;
                        }
                        String[] colors = sc.next().split(";");
                        pixles[widthCounter][heightCounter] = new Tocka(Integer.parseInt(colors[0]), Integer.parseInt(colors[1]), Integer.parseInt(colors[2]));

                    }
                } else {
                    
                    while(sc.hasNext()){
                        if (widthCounter == width) {
                            widthCounter = 0;
                            System.out.println("width error");
                        }
                        if (heightCounter == height) {
                            heightCounter = 0;
                        }
                        pixles[widthCounter][heightCounter] = new Tocka(Integer.parseInt(sc.next()));

                    }
                }
            }
            
        } catch (Exception e) {
            System.out.println(e);
        }   
        return new Slika(width, height, path, color, pixles);
    }
}

/*#######--MAIN CLASSES--#######*/
class Tocka {

    /*-----Vars-----*/    

    private boolean color = false;
    private boolean grey = false;
    Integer red;
    Integer green;
    Integer blue;

    /*----Constructor----*/

    //Constructor overloaded given by given color bytes
    //Given 3 presumed color
    //Given single presumed gray

    public Tocka(Integer red, Integer green, Integer blue ) throws ColorError{
        //COLOR 
        if (isBetween(red, 0, 255) && isBetween(green, 0, 255) && isBetween(blue, 0, 255)) {
            this.red = red;
            this.green = green;
            this.blue = blue;
            this.color = true;
        } else {
            throw new ColorError(red, green, blue);
        }   
    }

    public Tocka(Integer gray ) throws ColorError{
        //GRAY
        if (isBetween(gray, 0, 255)) {
            this.red = gray;
            this.green = gray;
            this.blue = gray;
            this.grey = true;
        } else {
            throw new ColorError(gray, gray, gray);
        }   
    }

    /*----Getters----*/

    public Integer[] getBarve() {
        return new Integer[]{this.red, this.green, this.blue};
    }

    public Integer getRed() {
        return this.red;
    }
    
    public Integer getGreen() {
        return this.green;
    }
    
    public Integer getBlue() {
        return this.blue;
    }
    
    public boolean getGrey() {
        return this.grey;
    }

    /*----Setters----*/

    public void setRed(Integer newVal) {
        if (isBetween(newVal, 0, 255)){
            this.red = newVal;
            grayTest();
        }
    }
    
    public void setGreen(Integer newVal) {
        if (isBetween(newVal, 0, 255)){
            this.green = newVal;
            grayTest();
        }
    }
    
    public void setBlue(Integer newVal) {
        if (isBetween(newVal, 0, 255)){
            this.blue = newVal;
            grayTest();
        }
    }

    /*----Overloads----*/
    public String toString(){
        return "("+ this.red + ", " + this.green + ", " + this.blue + ")";
    }

    /*----Helpers----*/

    private void grayTest(){
        //checks if all colors are same, sets grey flag otherwise color flag. 
        if (this.red == this.green && this.red == this.blue) {
            this.grey = true;
            this.color = false;
        } else {
            this.color = true;
            this.grey = false;
        }
    }

    private boolean isBetween(Integer i, Integer bottom, Integer top) {
        /**
         * Inclusive isBetween function for an Integer. 
         * 
         * @Param i Integer to be checked
         * @Param bottom Inclusive bottom limit
         * @Param top Inclusive top limit
         * 
         * @Return boolean true or false
         * 
         */
        if (i >= bottom && i <= top ) {
            return true;
        }
        return false;
    }

}

class Slika {

    /*-----Vars-----*/

    Integer width; 
    Integer height; 
    String path;
    String ime;
    boolean color;
    boolean gray;
    Tocka[][] pixles;

    /*----Constructor----*/
    //Constructor overloaded by arguments passed. 
    //Can be passed as all variables
    //Can be passed as path only
    //Can be passed as 2d array of Tocka classes

    public Slika (Integer width, Integer height, String ime, boolean color, Tocka[][] pixles) {
        //All variables
        try {
            this.width = width;
            this.height = height;
            this.ime = ime;
            this.color = color;
            this.pixles = pixles;
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public Slika (String path) {
        //as Path
        this.path = path;
    }

    public Slika (Tocka[][] pixles) {
        //as Path
        this.pixles = pixles;
        getDataFromPixles();
    }
    
    /*-----Getters and Setters-----*/
    
    public String getIme(){
        return this.ime;
    }

    public int getSirina(){
        return this.width;
    }

    public int getVisina(){
        return this.height;
    }

    public Tocka getTocka(Integer x, Integer y){
        return this.pixles[x][y];
    }

    public boolean getColor(){
        return this.color;
    }

    public Tocka[][] getAllPixles(){
        return this.pixles;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }
    
    public void setPath(String path) {
        this.path = path;
    }

    /*-----Helpers-----*/

    private void readImage(){
        Scanner sc = new Scanner(new File(this.path));
        String[] topLine;
        if (sc.hasNextLine()){
            topLine = sc.nextLine().split(" ");
            if (topLine[0] == "P2TC") {
                this.color = true;
            } else {
                this.color = false;
            }
            this.width = Integer.parseInt(topLine[1].split("x")[0]);
            this.height = Integer.parseInt(topLine[1].split("x")[1]);
            int widthCounter = 0;
            int heightCounter = 0;
            this.pixles = new Tocka[width][height];
            if (this.color) {
                while(sc.hasNext()){
                    if (widthCounter == width) {
                        widthCounter = 0;
                        System.out.println("width error");
                    }
                    if (heightCounter == height) {
                        heightCounter = 0;
                    }
                    String[] colors = sc.next().split(";");
                    pixles[widthCounter][heightCounter] = new Tocka(Integer.parseInt(colors[0]), Integer.parseInt(colors[1]), Integer.parseInt(colors[2]));

                }
            } else {
                
                while(sc.hasNext()){
                    if (widthCounter == width) {
                        widthCounter = 0;
                        System.out.println("width error");
                    }
                    if (heightCounter == height) {
                        heightCounter = 0;
                    }
                    pixles[widthCounter][heightCounter] = new Tocka(Integer.parseInt(sc.next()));

                }
            }
        }
            
        
    }

    private boolean getDataFromPixles() {
        this.width = pixles.length;
        this.height = pixles[1].length;
        for(Tocka[] t: this.pixles){ 
            for(Tocka t1: t){
                if (t1.getGrey() != true) {
                    this.color = true;
                    return true;
                }
            }
        }
        this.color = false;
        return false;
    }

    
}



/*########--ERRORS AND EXCEPTIONS--########*/
class ColorError extends Exception {

    public ColorError(Integer red, Integer green, Integer blue){
        //If color exception throw
        super("Wrong RGB value insereted into touple: (" + red + ", " + green + ", " + blue + ")");
    }
    public ColorError(Integer gray){
        //If grayscale exception throw
        super("Wrong RGB value insereted into touple: (" + gray + ", " + gray + ", " + gray + ")");
    }
}