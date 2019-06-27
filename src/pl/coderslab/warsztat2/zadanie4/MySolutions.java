package pl.coderslab.warsztat2.zadanie4;

import pl.coderslab.warsztat2.zadanie1.ExcerciseDao;
import pl.coderslab.warsztat2.zadanie1.Solution;
import pl.coderslab.warsztat2.zadanie1.SolutionDao;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;

public class MySolutions {

    public static void main(String[] args){


        int userId;
        if (args.length > 0) {
            try {
                userId = Integer.parseInt(args[0]);
                while(true) {
                    System.out.println("Choose one of the options:");
                    System.out.println("add - to add your solution");
                    System.out.println("view - to view your solution");
                    System.out.println("comment - to comment solution");
                    System.out.println("rate - to rate a solution");
                    System.out.println("quit - to view your solution");
                    Scanner scanner = new Scanner(System.in);
                    if (scanner.hasNextLine()) {
                        String action = scanner.nextLine().toLowerCase();
                        if (action.equals("add")) {
                            ExcerciseDao excerciseDao = new ExcerciseDao();
                            System.out.println(Arrays.toString(excerciseDao.findAllNotAssignedToUserId(userId)));
                            System.out.println("Choose exercise ID to add the related solution[1/2]:");
                            Scanner scanner1 = new Scanner(System.in);
                            if (scanner1.hasNextInt()) {
                                int excerciseId = scanner1.nextInt();
                                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                                Date date = new Date(System.currentTimeMillis());
                                String strDate = formatter.format(date);
                                System.out.println("Describe the solution[2/2]:");
                                Scanner scanner2 = new Scanner(System.in);
                                if (scanner2.hasNextLine()) {
                                    String excerciseDesc = scanner2.nextLine();
                                    Solution solution = new Solution(strDate, null, excerciseDesc, excerciseId, userId);
                                    SolutionDao solutionDao = new SolutionDao();
                                    solutionDao.create(solution);
                                    System.out.println("Solution has been created. Date: " + strDate + " Exercise id: " + excerciseId + " User id: " + userId);
                                }
                            }


                        } else if (action.equals("view")) {
                            SolutionDao solutionDao = new SolutionDao();
                            System.out.println("Your solutions:");
                            System.out.println(Arrays.toString(solutionDao.findAllByUserId(userId)));
                        } else if (action.equals("quit")) {
                            System.out.println("Closing program...");
                            break;
                        }
                    }
                }
            } catch (NumberFormatException e) {
                System.err.println("Argument" + args[0] + " must be an integer.");
                System.exit(1);
            }
        }



    }

}
