package org.example.SpringBootJDBC;

import org.example.SpringBootJDBC.dao.UserDao;
import org.example.SpringBootJDBC.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication // This will scan for components in org.example and its sub-packages
public class App implements CommandLineRunner {

    @Autowired
    private UserDao userDao;

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // Insertion ------------------>
        // User user1 = new User("rahul@gmail.com", "Dausa", "male", "Rahul");
        // User user = new User("Ram", "ram@gmail.com", "male", "Mumbai");
        // boolean status = userDao.insertUser(user);
        // if (status) {
        //     System.out.println("User inserted successfully");
        // } else {
        //     System.out.println("User not inserted due to some error");
        // }

        // Updation ---------------------------->
        // User user = new User("srahulb6@gmal.com", "dausa", "male", "Rahul");
        // boolean status = userDao.updateUser(user);
        // if (status) {
        //     System.out.println("Updation success");
        // } else {
        //     System.out.println("Updation failed");
        // }

        // DELETE USER  ---------------------------------->
        // boolean status = userDao.deleteUser("srahulb6@gmal.com");
        // if (status) {
        //     System.out.println("Deletion success");
        // } else {
        //     System.out.println("Deletion failed");
        // }

        // Select One User ----------------------------------->
        // User user = userDao.getUserByEmail("ram@gmail.com");
        // System.out.println("Retrieving user: " + user.getName());
        // System.out.println("Gender: " + user.getGender());
        // System.out.println("City: " + user.getCity());

        // Get all users -------------------->
        List<User> list = userDao.getAllUsers();
        for (User user : list) {
            System.out.println("Name: " + user.getName());
            System.out.println("Email: " + user.getEmail());
            System.out.println("Gender: " + user.getGender());
            System.out.println("City: " + user.getCity());
            System.out.println("-------------------------------");
        }
    }
}
