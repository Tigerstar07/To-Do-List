/*
 * Uzdevuma klase – satur informāciju par katru atsevišķu uzdevumu:
 * nosaukumu, aprakstu un izveidošanas datumu.
 */

 package lv.rvt;

 import java.io.Serializable;
 import java.time.ZonedDateTime;
 import java.time.format.DateTimeFormatter;
 
 public class Task implements Serializable {
     private final String title;
     private final String description;
     private final String creationDate;
 
     public Task(String title, String description) {
         this.title = title;
         this.description = description;
 
         // Saglabā izveides datumu un laiku pēc Latvijas laika (Europe/Riga)
         DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
         this.creationDate = ZonedDateTime.now(java.time.ZoneId.of("Europe/Riga")).format(formatter);
     }
 
     // Getter metodes datu piekļuvei
     public String getTitle() {
         return title;
     }
 
     public String getDescription() {
         return description;
     }
 
     public String getCreationDate() {
         return creationDate;
     }
 }
 