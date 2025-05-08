package lv.rvt;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class Task implements Serializable {
    private String title;
    private String description;
    private final String creationDate;
    private boolean isCompleted = false;
    private String completedTime = "";

    public Task(String title, String description) {
        this.title = title;
        this.description = description;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        this.creationDate = ZonedDateTime.now(java.time.ZoneId.of("Europe/Riga")).format(formatter);
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public String getCompletedTime() {
        return completedTime;
    }

    public void toggleCompleted() {
        this.isCompleted = !this.isCompleted;
        if (isCompleted) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
            this.completedTime = ZonedDateTime.now(java.time.ZoneId.of("Europe/Riga")).format(formatter);
        } else {
            this.completedTime = "";
        }
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
