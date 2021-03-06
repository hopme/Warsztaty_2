package pl.coderslab.warsztat2.zadanie2;

import pl.coderslab.warsztat2.zadanie1.Excercise;
import pl.coderslab.warsztat2.zadanie1.ExcerciseDao;


import java.util.Arrays;
import java.util.Scanner;

public class ExcercisesManagement {

    private static Scanner scanner = new Scanner(System.in);
    private static ExcerciseDao excerciseDao = new ExcerciseDao();

    public static void main(String[] args){

        System.out.println("--- EXCERCISES MANAGEMENT ---");

        while(true) {
            //Shows all excercises
            System.out.println("List of all excercises:");
            Excercise[] allExcercises = excerciseDao.findAll();
            System.out.println(Arrays.toString(allExcercises));

            //Shows options to choose
            System.out.println("Choose one of the actions on Excercises database.");
            System.out.println("Actions to choose from: 'add', 'edit', 'delete', 'quit' :");
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
                        editExcercise(excerciseId);
                    } else {
                        deleteExcercise(excerciseId);
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

        scanner.close();
    }

    private static void deleteExcercise(int id) {
        System.out.println("Are you sure you want to delete excercise ID = " + id);
        String confirm = scanner.next().toLowerCase();
        if (confirm.equals("yes")) {
            try {
                excerciseDao.delete(id);
            } catch (Exception e) {
                System.out.println("No such excerciseId");
            }
            System.out.println("Excerise with ID = " + id + " has been deleted succesfuly!");
        } else if (confirm.equals("no")) {
            System.out.println("Make up your mind");
        }

    }

    private static void editExcercise(int id) {
        System.out.println("Edit data for excercise ID: " + id);
        try {
            excerciseDao.update(setExcercise(id));
        } catch (Exception e) {
            System.out.println("No such excercise ID");
        }
        System.out.println("Updating excercise in DB successful");
    }

    private static Excercise setExcercise(int id) {

        System.out.println("Set title:");
        Excercise excercise = new Excercise();
        excercise.setId(id);
        if(scanner.hasNextLine()){
            String title = scanner.next();
            excercise.setTitle(title);
        }
        System.out.println("Set description:");
        if(scanner.hasNextLine()){
            String description = scanner.next();
            excercise.setDescription(description);
        }
        return excercise;
    }
}