package pl.coderslab.warsztat2.zadanie3;

import pl.coderslab.warsztat2.zadanie1.ExcerciseDao;
import pl.coderslab.warsztat2.zadanie1.Solution;
import pl.coderslab.warsztat2.zadanie1.SolutionDao;
import pl.coderslab.warsztat2.zadanie1.UserDao;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;

public class ExcerciseAssignment {

    public static void main(String[] args) {

        while (true) {
            System.out.println("Choose one of the actions:");
            System.out.println("add - to assign exercise to user");
            System.out.println("view - to view all solutions of a user");
            System.out.println("quit - to close the program");

            Scanner scanner = new Scanner(System.in);
            if (scanner.hasNextLine()) {
                String action = scanner.nextLine().toLowerCase();
                if (action.equals("add")) {
                    System.out.println("--- LIST OF ALL USERS ---");
                    UserDao userDao = new UserDao();
                    System.out.println(Arrays.toString(userDao.findAll()));
                    System.out.println("To create a Solution choose user ID [1/2]:");
                    Scanner scanner1 = new Scanner(System.in);
                    if (scanner1.hasNextInt()){
                        int userId = scanner1.nextInt();
                        System.out.println("--- LIST OF ALL EXERCISES ---");
                        ExcerciseDao excerciseDao = new ExcerciseDao();
                        System.out.println(Arrays.toString(excerciseDao.findAll()));
                        System.out.println("Choose excercise ID [2/2]:");
                        Scanner scanner2 = new Scanner(System.in);
                        if (scanner2.hasNextInt()){
                            int excerciseId = scanner2.nextInt();
                            SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
                            Date date = new Date(System.currentTimeMillis());
                            String strDate = formatter.format(date);
                            Solution solution = new Solution(strDate, null, null, excerciseId, userId);
                            SolutionDao solutionDao = new SolutionDao();
                            solutionDao.create(solution);
                            System.out.println("Solution has been created on " + strDate + " userid no " + userId + " has been assigned to exercise no " + excerciseId);
                        }
                    }
                } else if (action.equals("view")){
                    System.out.println("Type User ID to display his solutions:");
                    Scanner scanner1 = new Scanner(System.in);
                    if (scanner1.hasNextInt()){
                        int userId = scanner1.nextInt();
                        SolutionDao solutionDao = new SolutionDao();
                        System.out.println(Arrays.toString(solutionDao.findAllByUserId(userId)));
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
}