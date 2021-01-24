import java.util.*;

public class Subject {
    private int number;
    private String name;
    private LinkedList<Activity> activities = new LinkedList<Activity>();  

    public Subject(int number, String name) { 
        this.number = number;
        this.name = name;
    }

    public void addActivity(String group, int number, String day, int start, int duration, String room, int capacity) {
        activities.add(new Activity(this, group, number, day, start, duration, room, capacity));
    }

    public int getNumber() {
        return number;
    }

    public String toString() {
        return number + " " + name;
    }

    public Activity enrolActivity() {
        System.out.println("Select an activity");
        for (Activity activity : activities) {
            System.out.println(activity);
        }
        System.out.print("Activity code (group:activity): ");
        String selection = In.nextLine();
        boolean exists = false;
        for (Activity activity : activities) {
            if (activity.getCode().equals(selection)) {
                exists = true;
                if (activity.seatsAvailable()){
                    return activity;
                }
                else {
                    System.out.println("No available seats");
                }
            }
        }
        if (exists == false) {
            for (Activity activity : activities) {
                if (activity.getGroup().equals(selection)) {
                    exists = true;
                    if (activity.seatsAvailable()){
                        return activity;
                    }
                }
            }
            if (exists == true) {
                    System.out.println("no available seats");
                }
        }
        if (exists == false) {
            System.out.println("No such activity");
        }
        return null;
    }
}
