import java.util.*;
import java.text.*;
public class Activity {
    private Subject subject;
    private String group;
    private int number;
    private String day;
    private int start;
    private int duration;
    private String room;
    private int capacity;
    private int enrolled;

    public Activity (Subject subject, String group, int number, String day, int start, int duration, String room, int capacity) {
        this.subject = subject;
        this.group = group;
        this.number = number;
        this.day = day;
        this.start = start;
        this.duration = duration;
        this.room = room;
        this.capacity = capacity;
    }

    public String toString() {
        return subject.getNumber() + " " + group + " " + number + " " + day + " " + room + " " + formatted(start) + " " + duration + "hrs " + enrolled + "/" + capacity;
    }

    public String formatted(int start) {
        return new DecimalFormat("00.00").format(start).replace('.', ':');
    }

    public String getCode() {
        return group + ":" + number; 
    }

    public boolean seatsAvailable() {
        return enrolled < capacity;
    }
    
    public String getGroup() {
        return group;
    }
    
    public void enrol() {
        enrolled += 1;
    }
    
    public void unenrol() {
        enrolled -= 1;
    }
    
    public String getNumGroup() {
        return subject.getNumber() + ":" + group;
    }
    
    public Subject getSubject() {
        return subject;
    }
}
