package DictHashProject;


import static DictHashProject.DictOperations.menu;
import static DictHashProject.DictOperations.QuitManager.*;



public class Main {
    public static void main(String[] args) throws InterruptedException {

        while (!isQuit()){
            menu();
            Thread.sleep(2000);
        }


    }
}