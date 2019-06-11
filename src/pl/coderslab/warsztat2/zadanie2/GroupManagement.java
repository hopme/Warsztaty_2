package pl.coderslab.warsztat2.zadanie2;

import pl.coderslab.warsztat2.zadanie1.Group;
import pl.coderslab.warsztat2.zadanie1.GroupDao;

import java.util.Arrays;
import java.util.Scanner;

public class GroupManagement{


    public static void main(String[] args) {

        System.out.println("--- GROUPS MANAGEMENT ---");

        while(true) {
            //Shows all users in DB
            System.out.println("List of all Groups:");
            GroupDao groupDao = new GroupDao();
            Group[] allGroups = groupDao.findAll();
            System.out.println(Arrays.toString(allGroups));

            //Shows options to choose
            System.out.println("Choose one of the actions on groups database.");
            System.out.println("Actions to choose from: 'add', 'edit', 'delete', 'quit' :");
            Scanner scanner = new Scanner(System.in);
            if (scanner.hasNextLine()) {
                String action = scanner.nextLine().toLowerCase();
                if (action.equals("add")) {
                    System.out.println("To add new Group set up following data:");
                    groupDao.create(setGroup(0));
                    System.out.println("Adding group to DB successful");

                } else if (action.equals("edit") || action.equals("delete")) {
                    System.out.println("Specify the group's ID number:");
                    int groupId = scanner.nextInt();
                    if (action.equals("edit")) {
                        System.out.println("Edit data for group ID: " + groupId);
                        groupDao.update(setGroup(groupId));
                        System.out.println("Updating group in DB successful");
                    } else {
                        System.out.println("Are you sure you want to delete group ID = " + groupId);
                        Scanner scanner1 = new Scanner(System.in);
                        String confirm = scanner1.nextLine().toLowerCase();
                        if (confirm.equals("yes")) {
                            groupDao.delete(groupId);
                            System.out.println("Group with ID = " + groupId + " has been deleted succesfuly!");
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

    private static Group setGroup(int id) {

        System.out.println("Set group name:");
        Group group = new Group();
        group.setId(id);
        Scanner scanner1 = new Scanner(System.in);
        if(scanner1.hasNextLine()){
            String groupName = scanner1.nextLine();
            group.setName(groupName);
        }
        return group;
    }
}