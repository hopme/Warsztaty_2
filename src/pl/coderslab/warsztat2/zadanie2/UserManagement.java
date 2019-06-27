package pl.coderslab.warsztat2.zadanie2;

import pl.coderslab.warsztat2.zadanie1.User;
import pl.coderslab.warsztat2.zadanie1.UserDao;

import java.util.Arrays;
import java.util.Scanner;

public class UserManagement{


    public static void main(String[] args) {

        System.out.println("--- USERS MANAGEMENT ---");

        while(true) {
            //Shows all users in DB
            System.out.println("List of all Users:");
            UserDao userDao = new UserDao();
            User[] allUsers = userDao.findAll();
            System.out.println(Arrays.toString(allUsers));

            //Shows options to choose
            System.out.println("Choose one of the actions on Users database.");
            System.out.println("Actions to choose from: 'add', 'edit', 'delete', 'quit' :");
            Scanner scanner = new Scanner(System.in);
            if (scanner.hasNextLine()) {
                String action = scanner.nextLine().toLowerCase();
                if (action.equals("add")) {
                    System.out.println("To add new User set up following data:");
                    userDao.create(setUser(0));
                    System.out.println("Adding user to DB successful");
                } else if (action.equals("edit") || action.equals("delete")) {
                    System.out.println("Specify the user's ID number:");
                    int userId = scanner.nextInt();
                    if (action.equals("edit")) {
                        System.out.println("Edit data for user ID: " + userId);
                        userDao.update(setUser(userId));
                        System.out.println("Updating user in DB successful");
                    } else {
                        System.out.println("Are you sure you want to delete user ID = " + userId);
                        String confirm = scanner.nextLine().toLowerCase();
                        if (confirm.equals("yes")) {
                            userDao.delete(userId);
                            System.out.println("User with ID = " + userId + " has been deleted succesfuly!");
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
            scanner.close();
        }

    }

    private static User setUser(int id) {

        System.out.println("Set username:");
        User user = new User();
        user.setId(id);
        Scanner scanner = new Scanner(System.in);
        if(scanner.hasNextLine()){
            String username = scanner.nextLine();
            user.setUserName(username);
        }
        System.out.println("Set Email:");
        if(scanner.hasNextLine()){
            String email = scanner.nextLine();
            user.setEmail(email);
        }
        System.out.println("Set Password:");
        if(scanner.hasNextLine()){
            String password = scanner.nextLine();
            user.setPassword(password);
        }
        scanner.close();
        return user;
    }
}




