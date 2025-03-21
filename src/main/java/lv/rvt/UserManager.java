package lv.rvt;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class UserManager {
    private static final String FILE_PATH = "data/users.csv";
    private List<User> users;

    public UserManager() {
        users = new ArrayList<>();
        loadUsers();
    }

    // Load users from file
    private void loadUsers() {
        Path path = Paths.get(FILE_PATH);
        if (!Files.exists(path)) {
            System.out.println("No users found. Please register.");
            return;
        }

        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    users.add(new User(parts[0], parts[1], parts[2]));
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading users: " + e.getMessage());
        }
    }

    // Save users to file
    private void saveUsers() {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(FILE_PATH))) {
            for (User user : users) {
                writer.write(user.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving users: " + e.getMessage());
        }
    }

    // Register new user
    public boolean register(String username, String password, String email) {
        if (getUser(username) != null) {
            System.out.println("Username already exists!");
            return false;
        }

        users.add(new User(username, password, email));
        saveUsers();
        System.out.println("User registered successfully!");
        return true;
    }

    // Authenticate user
    public boolean login(String username, String password) {
        User user = getUser(username);
        return user != null && user.getPassword().equals(password);
    }

    // Find user by username
    private User getUser(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }
}
