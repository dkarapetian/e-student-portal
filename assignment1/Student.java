import java.util.*;

public class Student {
    private String number;
    private String name;
    private LinkedList<Activity> activities = new LinkedList<Activity>(); 

    public Student(String number, String name) {
        this.number = number;
        this.name = name;
    }

    public String toString() {
        return number + " " + name;
    }

    public String getNumber() {
        return number;
    }

    public void use(LinkedList<Subject> subjects) {
        char choice;
        while ((choice = readChoice()) != 'x') {
            switch (choice) {
                case 'v': viewActivities(); break;
                case 'e': enrolActivity(subjects); break;
                case 'w': selectActivityToWithdraw(); break;
                default : help(); break;
            }
        }
    }

    private char readChoice() {
        System.out.print("Choice (v/e/w/x): ");
        return In.nextChar();
    }

    public void viewActivities() {
        for (Activity activity : activities) {
            System.out.println(activity);
        }
    }

    public void enrolActivity(LinkedList<Subject> subjects) {
        System.out.println("Select a subject");
        for (Subject subject : subjects) {
            System.out.println(subject);
        }
        System.out.print("Subject number: ");
        int number = In.nextInt();
        boolean exists = false;
        boolean alreadyEnrolled = false;
        for (Subject subject : subjects) {
            if (subject.getNumber() == number) {
                exists = true;
                Activity enrolActivity = subject.enrolActivity();
                if (enrolActivity != null) {
                    for (Activity activity : activities) {
                        if (activity.equals(enrolActivity)) {
                            alreadyEnrolled = true;
                        }
                    } 
                    if (alreadyEnrolled == false) {
                        withdrawActivity(enrolActivity);
                        activities.add(enrolActivity);
                        enrolActivity.enrol();
                    }  
                }
            } 
        }
        if (exists == false) {
            System.out.println("No such subject");
        }
    }

    public void withdrawActivity(Activity activityWithdraw) {
        //boolean exists = false;
        for (Iterator<Activity> it = activities.iterator(); it.hasNext();) {
            Activity a = it.next();
            if ((a.getSubject().equals(activityWithdraw.getSubject()) && (a.getGroup().equals(activityWithdraw.getGroup())))) {
                a.unenrol();
                it.remove();
                //exists = true;
            }
        }
    }

    private void selectActivityToWithdraw() {
        System.out.print("Activity code (subject:group): ");
        String code = In.nextLine();
        Activity toRemove = null;
        for(Activity activity : activities){
            if(code.equals(activity.getNumGroup())){
                toRemove = activity;
            }
        }
        if(toRemove != null){
            withdrawActivity(toRemove);
        }else{
            System.out.println("Not enrolled in activity");
        }
    }
    
    public void withdrawAll() {
        for (Activity activity : activities) {
            activity.unenrol();
        }
    }

    private void help() {
        System.out.println("Student menu options");
        System.out.println("v = view my activities");
        System.out.println("e = enrol in an activity");
        System.out.println("w = withdraw from an activity");
        System.out.println("x = exit");
    }
}
