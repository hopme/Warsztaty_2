package coderslab.warsztat2.zadanie1;

import java.util.Arrays;

public class App {
    public static void main(String[] args) {
        //User
        System.out.println("Testowanie klasy User i UserDAO");
        User user =
                new User("Blazej", "mail", "haslo");

        UserDao userDao = new UserDao();
        User userFromDb = userDao.create(user);

        User user1 = userDao.read(1);
        System.out.println(user1.toString());
        user1.setUserName("nowe imie");
        userDao.update(user1);
        System.out.println("User1 = ");
        System.out.println(user1.toString());

        System.out.println("FindAll - list wszystkich user'ow dostepnych w bazie danych:");
        UserDao userDao1 = new UserDao();
        User[] usersAllFromDb = userDao.findAll();
        System.out.println(Arrays.toString(usersAllFromDb));

//        Group
        System.out.println();
        System.out.println("Testowanie klasy Group i GroupDAO");
        Group group = new Group("Nowa nazwa grupy");
        GroupDao groupDao = new GroupDao();
        Group groupFromDb = groupDao.create(group);
        Group group1 = groupDao.read(1);
        group1.setName("Zmieniona nazwa grupy");
        groupDao.update(group1);
        System.out.println(group1.toString());

//        Excercise
        System.out.println();
        System.out.println("Testowanie klasy Excercise i ExcerciseDAO");
        Excercise excercise = new Excercise("Zadanie1", "Opis1");
        ExcerciseDao excerciseDao = new ExcerciseDao();
        Excercise excerciseFromDb = excerciseDao.create(excercise);
        Excercise excercise1 = excerciseDao.read(2);
        excercise1.setTitle("Nowy tytul zadania");
        excerciseDao.update(excercise1);
        System.out.println("Excercise1 = ");
        System.out.println(excercise1.toString());

//        Solution
        System.out.println();
        System.out.println("Testowanie klasy Solution i SolutionDAO");
        Solution solution = new Solution("2011-04-04", "2016-08-08", "Opis rozwiazania", excercise.getId(), user.getId());
        SolutionDao solutionDao = new SolutionDao();
        solutionDao.create(solution);
        Solution solution1 = new Solution();
        solution1.setDescription("Zmieniony opis rozwiazania");
        solutionDao.update(solution1);
        System.out.println("Solution1 = ");
        System.out.println(solution1.toString());
        System.out.println("Lista wszystkich rozwiazan nalezacych do usera: " + user1.toString() + ":");

        Solution[] solutionsByUserFromDb = solutionDao.findAllByUserId(user1.getId());
        System.out.println(Arrays.toString(solutionsByUserFromDb));

        System.out.println("Lista wszystkich rozwiazan  danego zadania: " + excercise1.toString() + ":");
        Solution[] solutionsByExcerciseFromDb = solutionDao.findAllByExcerciseId(excercise1.getId());
        System.out.println(Arrays.toString(solutionsByExcerciseFromDb));

//        User_Group
        System.out.println();
        System.out.println("Testowanie UserGroup");
        System.out.println("FindAllByGroupId - lista wszystkich user'ow nalezacych do danej grupy:" );
        User[] usersGroupFromDb = userDao.findAllByGroupId(group1);
        System.out.println(Arrays.toString(usersGroupFromDb));

    }
}