import java.util.Arrays;
import java.util.Scanner;
import java.io.File;

public class DN05 {

    public static void main (String[] args) throws Exception{
        /**
         * Calls methods
         * tests for amount of arguments given. Prints error otherwise. 
        */
        if (args.length > 1) { 
            switch (args[0]) {
            case "1":
                File f = new File(args[1]); 
                Scanner sc = new Scanner(f);
                prvaNaloga(sc);
                break;
            case "2": 
                if (args.length > 2) {
                drugaNaloga(args[1], Integer.parseInt(args[2])); 
                } else {
                    System.out.println("Potrebni so 3 argumenti.");
                }
                break; 
            case "3": 
                tretjaNaloga(args[1]);
            }
        } else {
            System.out.println("Niste vnesli dovolj argumentov. Potrebna sta najmanj 2 in največ 3. ");
        }

    }

    public static void prvaNaloga(Scanner sc) {
        /**
         * Main method of 1st exercise. 
         * Given a file of ids and scores separated by ":"  (63170123:100), 
         * finds all occurences of the biggest score and prints them on screen. 
         */
        String[] best = new String[10];
        int biggestScore = 0; 
        int i = 0; 
        while(sc.hasNextLine()) {
            String[] nl = sc.nextLine().split(":"); 
            String id = nl[0];
            String score = nl[1];
            if (Integer.parseInt(score) > biggestScore) {
                Arrays.fill(best, null); 
                biggestScore = Integer.parseInt(score);
                i = 0;
                best[i] = id; 
                i++; 
            } else if (Integer.parseInt(score) == biggestScore) {
                best[i] = id; 
                i++; 
            }
        }
        System.out.println("Max " + biggestScore + " so dosegli: ");
        for (int n=0; n < best.length; n++) {
            if (best[n] != null){
                System.out.println(best[n]);
            }    
        }
        sc.close();
        
    }

    public static void drugaNaloga(String filename, int k) throws Exception {
        /**
         * Main method of 2nd exercise
         * Given a file of numbers calls methods preberi (read), duplikati (duplicates) and
         * rotiraj (rotate). It reads the file, finds all duplicates in the array and removes them. 
         * Then it rotates the array.  
         */
        Integer[] l = preberi(filename);
        Integer[] l3 = duplikati(l);
        Integer[] l2 = rotiraj(l3, k); 
        izpisi(l2);
    }

    public static void tretjaNaloga(String filename) throws Exception {
        /**
         * Main method of 3rd exercise
         * Given a file of two lines, first specifying the number of Arrays and 
         * second the number of elements in each arary, the method will create a 2d array of ints
         * sort it vertically (by indeks of elements in child array) and 
         * then sum all elements of child array. Prints results on screen. 
         * 
         */
        Integer[][] l = preberi2d(filename);
        Integer[] s = sestej(l);
        for (int i=0; i < s.length; i++) {
            System.out.print(s[i] + " ");
        }
    }

    public static Integer[] preberi(String filename) throws Exception {
        /**
         * Helper method of 2nd exercise
         * Scans new file and returns it as array of integers
         */
        File f = new File(filename); 
        Scanner sc = new Scanner(f);
        String[] nums = sc.nextLine().split(" "); 
        Integer[] list = new Integer[Integer.parseInt(nums[0])];

        for(int i=1; i <= Integer.parseInt(nums[0]); i++) {
            list[i-1] = Integer.parseInt(nums[i]); 
        }
        sc.close();
        return list; 
    }

    public static Integer[] duplikati (Integer[] l) {
        /**
         * Helper method of 2nd exercise
         * Given an array of integers it removes all duplicates 
         * and returns new array          
         */
        Integer[] temp = new Integer[l.length]; 
        int counter = 0; 
        for(int i=0; i < l.length; i++) {
            if (!Arrays.asList(temp).contains(l[i])) {
                temp[counter] = l[i]; 
                counter++; 
            }
        }
        Integer[] newList = new Integer[counter]; 
        for (int i=0; i < counter; i++) {
            newList[i] = temp[i]; 
        }
        return newList; 
    }   

    public static Integer[] rotiraj(Integer[] l, int k) {
        /**
         * Helper method of 2nd exercise
         * Given an array of integers it reverse the order and 
         * returns reversed array. 
         */
        Integer[] newList = new Integer[l.length]; 
        if (k == 0) {
            return l; 
        }
        for (int i=0; i<l.length; i++) {
            if (i < k) {
                newList[newList.length - (k)+i] = l[i]; 
            } else {
                newList[i-k] = l[i]; 
            }
        }
        return newList; 
    }

    public static void izpisi(Integer[] l) {
        /**
         * Helper method of 2nd exercise
         * Prints array of integers to screen. 
         */
        for (int i=0; i < l.length; i++){
            System.out.print(l[i] + " ");
        }
    }

    public static Integer[][] preberi2d(String filename) throws Exception {
        /**
         * Helper method of 3rd exercise
         * Reads a 2d list of numbers from a file and 
         * returns a 2d array of integers. 
         * Example of file: 
         * 2, 3
         * 1, 2, 3, 4, 5, 6
         * results in: 
         * {{1, 2, 3}, {4, 5, 6}}
         */
        Scanner sc = new Scanner(new File(filename));
        String[] pogoji = sc.nextLine().split(" "); 
        String[] stevila = sc.nextLine().split(" ");
        Integer[] stevila2 = new Integer[stevila.length];
        for (int i=0; i < stevila.length; i++) {
            stevila2[i] = Integer.parseInt(stevila[i]);
            
        }
        //Arrays.sort(stevila2);
        Integer[][] newList = new Integer[Integer.parseInt(pogoji[0])][Integer.parseInt(pogoji[1])];
        int counter = 0; 
        for (int i=0; i < Integer.parseInt(pogoji[0]); i++) {
            for (int n=0; n < Integer.parseInt(pogoji[1]); n++) {
                newList[i][n] = stevila2[counter];
                counter++; 
            }
        }
       Integer[][] sortedList = sorted(newList,Integer.parseInt(pogoji[0]), Integer.parseInt(pogoji[1]));
       sc.close();
       return sortedList; 
    }

    public static Integer[][] sorted(Integer[][] l, int p1, int p2) {
        /**
         * Helper method of 3rd exercise
         * Given a 2d list of integers and respective sizes of arrays, 
         * It will reverse the length of both arrays, order it and 
         * reverse it back 
         * 
         * Example: 
         * {{1, 2}, {5, 6}, {3, 4}} => {{1, 5, 3},{2, 6, 4}} => {{1, 3, 5} {2, 4, 6}} => {{1, 2}, {3, 4}, {5, 6}}
         */
        Integer[][] newList = new Integer[p2][p1];
        Integer[][] newList2 = new Integer[p1][p2];
        
        for (int i=0; i < p2; i++) {
            for (int n=0; n < p1; n++){
                newList[i][n] = l[n][i]; 
            }
            Arrays.sort(newList[i]);
        } 
        for (int i=0; i < p2; i++) {
            for (int n=0; n < p1; n++){
                newList2[n][i] = newList[i][n]; 
            }
            Arrays.sort(newList[i]);
        } 
        /*
        for (int i=0; i < p2; i++) {
            for (int n=0; n < p1; n++){
                System.out.print(" " + newList[i][n]); 
            }
            System.out.println("\n");
        } */
        return newList2; 
    }
     
    public static Integer[] sestej (Integer[][] l) {
        /**
         * Helper method of 3rd exercise
         * Given a 2d array of integers it will sum all the child arrays
         * and return a new array of sums. 
         */

        Integer[] sestevki = new Integer[l.length];
        for (int i=0; i < l.length; i++) {
            int sestevek = 0; 
            for (int n=0; n < l[i].length; n++) {
                sestevek += l[i][n];
            }
            sestevki[i] = sestevek;
        }
        return sestevki;
    }


}