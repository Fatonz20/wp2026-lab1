package mk.ukim.finki.wp.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Registration;
import mk.ukim.finki.wp.lab.model.Workshop;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<Workshop> workshops = null;
    public static List<Registration> registrations = null;

    @PostConstruct
    public void init() {
        workshops = new ArrayList<>();

        workshops.add(new Workshop("Algorithms and Data Structures", "Programming", 49.99, 20));
        workshops.add(new Workshop("Structural Programming", "Programming", 25.75, 0));
        workshops.add(new Workshop("Advanced Web Design", "Web", 35.0, 35));
        workshops.add(new Workshop("Cybersecurity", "Security", 41.99, 18));
        workshops.add(new Workshop("Advanced Programming", "Programming", 54.99, 25));
        workshops.add(new Workshop("Artificial Intelligence", "AI", 39.99, 27));
        workshops.add(new Workshop("Web Programming", "Programming", 19.99, 22));
        workshops.add(new Workshop("Database", "Databases", 30, 10));
        workshops.add(new Workshop("Computer Architecture and Organization", "Computer Engineer", 20, 10));
        workshops.add(new Workshop("Calculus 1", "Math", 18.99, 14));

        registrations = new ArrayList<>();

    }

}
