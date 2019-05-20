//package coderslab.warsztat2.dodatki;
//
//import coderslab.warsztat2.zadanie1.Excercise;
//import coderslab.warsztat2.zadanie1.ExcerciseDao;
//import coderslab.warsztat2.zadanie1.Solution;
//import coderslab.warsztat2.zadanie1.SolutionDao;
//
//import java.util.Arrays;
//import java.util.Scanner;
//
//public class CommentsManagement {
//
//    public static void main(String[] args){
//
//        System.out.println("--- EXCERCISES MANAGEMENT ---");
//
//        while(true) {
//            //Shows all excercises
//            System.out.println("List of all comments:");
//            SolutionDao solutionDao = new SolutionDao();
//            Solution[] allSolutions = solutionDao.findAll()
//            System.out.println(Arrays.toString(allSolutions));
//
//            //Shows options to choose
//            System.out.println("Choose one of the actions on Comments database.");
//            System.out.println("Actions to choose from: 'add', 'edit', 'delete', 'quit' :");
//            Scanner scanner = new Scanner(System.in);
//            if (scanner.hasNextLine()) {
//                String action = scanner.nextLine().toLowerCase();
//                if (action.equals("add")) {
//
//                    System.out.println("To which solution do you want to add a comment (id)");
//                    Scanner scanner1 = new Scanner(System.in);
//                    int solutionId = scanner1.nextInt();
//                    System.out.println("Put your comment:");
//                    Scanner scanner2 = new Scanner(System.in);
//                    String commentText = scanner2.nextLine();
//                    System.out.println("Put your rating:");
//                    Scanner scanner3 = new Scanner();
//                    int rating = scanner3.nextInt();
//                    Comment comment = new Comment(solutionId, commentText, Integer.toString(rating));
//                    CommentDao commentDao = new CommentDao();
//                    commentDao.create(comment);
//
//                }
//                } else if (action.equals("edit") || action.equals("delete")) {
//                    System.out.println("Specify the excercise's ID number:");
//                    int excerciseId = scanner.nextInt();
//                    if (action.equals("edit")) {
//                        System.out.println("Edit data for excercise ID: " + excerciseId);
//                        excerciseDao.update(setExcercise(excerciseId));
//                        System.out.println("Updating excercise in DB successful");
//                    } else {
//                        System.out.println("Are you sure you want to delete excercise ID = " + excerciseId);
//                        Scanner scanner1 = new Scanner(System.in);
//                        String confirm = scanner1.nextLine().toLowerCase();
//                        if (confirm.equals("yes")) {
//                            excerciseDao.delete(excerciseId);
//                            System.out.println("Excerise with ID = " + excerciseId + " has been deleted succesfuly!");
//                        } else if (confirm.equals("no")) {
//                            System.out.println("Make up your mind");
//                        }
//                    }
//                } else if (action.equals("quit")) {
//                    System.out.println("Closing program...");
//                    break;
//                } else {
//                    System.out.println("No such command, please try again.");
//                    System.out.println();
//                }
//            }
//        }
//
//    }
//
//    private static Comment setComment() {
//
//        System.out.println("Set comment:");
//        Comment comment = new Comment();
//        Scanner scanner = new Scanner(System.in);
//        if (scanner.hasNextLine()) {
//            String title = scanner.nextLine();
//            comment.setComment(title);
//        }
//        return comment;
//
//    }
//    private  static Comment setRating() {
//        Comment comment = new Comment();
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Set rating:");
//        if(scanner.hasNext()){
//            String rating = scanner.next();
//            comment.setRating(rating);
//        }
//        return comment;
//    }
//}