/*
 * Šī klase pārvalda uzdevumu sarakstu – pievienošanu, rediģēšanu, dzēšanu un saglabāšanu failā.
 * Datu glabāšana tiek veikta ar serializāciju (ObjectOutputStream).
 */

 package lv.rvt;

 import java.io.*;
 import java.util.ArrayList;
 import java.util.List;
 
 public class TaskManager {
     private List<Task> tasks = new ArrayList<>();
     private final String FILE_NAME = "tasks.dat"; // Faila nosaukums saglabāšanai
 
     // Iegūst uzdevumu sarakstu
     public List<Task> getTasks() {
         return tasks;
     }
 
     // Pievieno jaunu uzdevumu
     public void addTask(String title, String description) {
         tasks.add(new Task(title, description));
     }
 
     // Rediģē esošu uzdevumu pēc indeksa
     public void editTask(int index, String newTitle, String newDescription) {
         if (index >= 0 && index < tasks.size()) {
             tasks.set(index, new Task(newTitle, newDescription));
         }
     }
 
     // Dzēš uzdevumu pēc indeksa
     public void removeTask(int index) {
         if (index >= 0 && index < tasks.size()) {
             tasks.remove(index);
         }
     }
 
     // Saglabā uzdevumus failā
     public void saveTasks() {
         try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
             out.writeObject(tasks);
         } catch (IOException e) {
             System.out.println("❌ Error saving tasks: " + e.getMessage());
         }
     }
 
     // Ielādē uzdevumus no faila
     public void loadTasks() {
         try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
             tasks = (List<Task>) in.readObject();
         } catch (IOException | ClassNotFoundException e) {
             // Klusē, ja fails neeksistē – tas nav kļūda pirmajā palaišanas reizē
         }
     }
 }
 