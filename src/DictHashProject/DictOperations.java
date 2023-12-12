package DictHashProject;

import java.util.*;
import java.io.*;
import DictHashProject.myHashTable;

import static DictHashProject.DictOperations.QuitManager.*;

public class DictOperations {
    static myHashTable tableHash;
    static Scanner input = new Scanner(System.in);



    public static void menu(){
        System.out.println("Enter the operation you want to execute");
        System.out.println("1-load A dictionary to text file\n" +
                "2-Search for an entry in this dictionary.\n" +
                "3-Insert a word to the dictionary \n" +
                "4-Delete a word from the dictionary \n" +
                "5-Given a random text file, do a spell check\n" +
                "6-Quit");

        int op = input.nextInt();

        switch (op)
        {
            case(1):
                dictLoad();
                break;
            case 2:
                dictSearch();
                break;
            case 3:
                dictInsert();
                break;
            case 4:
                dictDelete();
                break;
            case 5:
                dictSpellCheck();
                break;
            case 6:
                justQuit();
                break;
            default:
                System.out.println("enter a valid choice");
                break;


        }
    }
    public static void dictLoad(){
        tableHash = new myHashTable();
        System.out.println("Enter the file or path");
        String path = input.next();



        try (BufferedReader reader = new BufferedReader(new FileReader(path)))
        {
            String line;
            while ((line = reader.readLine()) != null)
            {
                tableHash.addWord(line.trim());
            }
        }
        catch (IOException e)
        {
            System.out.println("an error occured" + e);
        }




    }

    public static void dictSearch(){

        if(tableHash.containsWord(askForWord()))
        {
            System.out.println("Word found!");
        }
        else {
            System.out.println("no such word!");
        }
    }


    public static void dictInsert(){
        tableHash.addWord(askForWord());

    }


    public static void dictDelete(){
        tableHash.removeWord(askForWord());

    }


    public static void dictSpellCheck(){
        System.out.println("enter file path you want to check");
        String path = input.next();

            LinkedList<String> wordsList = new LinkedList<>();


            try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] words = line.split("\\s+"); // Boşluklara göre kelimeleri ayır
                    for (String word : words) {
                        wordsList.add(word.trim()); // Kelimeyi LinkedList'e ekle
                    }
                }
            } catch (IOException e) {
                System.err.println("an error occured: " + e.getMessage());
            }

            for(String word : wordsList){
                if (tableHash.containsWord(word)){
                    System.out.printf("Word is Correct : " + word + "\n");
                }
                else{
                    System.out.println("Word is wrong : " + word);
                }
            }

        System.out.println("*-*-*-*-*-*-*-*-*-*-*-*");


    }

    public static String askForWord(){
        System.out.println("Enter the word do you want to look for.");
       return input.next();
    }
    public static class QuitManager {
        private static boolean quit = false;

        public static boolean isQuit() {
            return quit;
        }

        public static void justQuit() {
            System.out.println("Bye!");
            quit = true;
        }
    }



}
