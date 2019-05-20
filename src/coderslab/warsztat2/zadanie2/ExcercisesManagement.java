package coderslab.warsztat2.zadanie2;

import coderslab.warsztat2.zadanie1.Excercise;
import coderslab.warsztat2.zadanie1.ExcerciseDao;


import java.util.Arrays;
import java.util.Scanner;

public class ExcercisesManagement {

    public static void main(String[] args){

        System.out.println("--- EXCERCISES MANAGEMENT ---");

        while(true) {
            //Shows all excercises
            System.out.println("List of all excercises:");
            ExcerciseDao excerciseDao = new ExcerciseDao();
            Excercise[] allExcercises = excerciseDao.findAll();
            System.out.println(Arrays.toString(allExcercises));

            //Shows options to choose
            System.out.println("Choose one of the actions on Excercises database.");
            System.out.println("Actions to choose from: 'add', 'edit', 'delete', 'quit' :");
            Scanner scanner = new Scanner(System.in);
            if (scanner.hasNextLine()) {
                String action = scanner.nextLine().toLowerCase();
                if (action.equals("add")) {
                    System.out.println("To add new excercise set up following data:");
                    excerciseDao.create(setExcercise(0));
                    System.out.println("Adding exercise to DB successful");
                } else if (action.equals("edit") || action.equals("delete")) {
                    System.out.println("Specify the excercise's ID number:");
                    int excerciseId = scanner.nextInt();
                    if (action.equals("edit")) {
                        System.out.println("Edit data for excercise ID: " + excerciseId);
                        excerciseDao.update(setExcercise(excerciseId));
                        System.out.println("Updating excercise in DB successful");
                    } else {
                        System.out.println("Are you sure you want to delete excercise ID = " + excerciseId);
                        Scanner scanner1 = new Scanner(System.in);
                        String confirm = scanner1.nextLine().toLowerCase();
                        if (confirm.equals("yes")) {
                            excerciseDao.delete(excerciseId);
                            System.out.println("Excerise with ID = " + excerciseId + " has been deleted succesfuly!");
                        } else if (confirm.equals("no")) {
                            System.out.println("Make up your mind");
                        }
                    }
                } else if (action.equals("quit")) {
                    System.out.println("Closing program...");
                    break;
                } else {
                    System.out.println("No such command, please try again.");
                    System.out.println();
                }
            }
        }

    }

    private static Excercise setExcercise(int id) {

        System.out.println("Set title:");
        Excercise excercise = new Excercise();
        excercise.setId(id);
        Scanner scanner = new Scanner(System.in);
        if(scanner.hasNextLine()){
            String title = scanner.nextLine();
            excercise.setTitle(title);
        }
        System.out.println("Set description:");
        if(scanner.hasNextLine()){
            String description = scanner.nextLine();
            excercise.setDescription(description);
        }
        return excercise;
    }
}