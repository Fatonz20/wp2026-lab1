package mk.ukim.finki.wp.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Instructor;
import mk.ukim.finki.wp.lab.model.Registration;
import mk.ukim.finki.wp.lab.model.Workshop;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<Workshop> workshops = null;
    public static List<Registration> registrations = null;
    public static List<Instructor> instructors = null;

    @PostConstruct
    public void init() {
        workshops = new ArrayList<>();
        instructors = new ArrayList<>();

        instructors.add(new Instructor("Gramatikov", "Sasho", "gramatikovsasho@gmail.com", "Web Programming"));
        instructors.add(new Instructor("Mirceva", "Georgina", "mircevageorgina@gmail.com", "Data Bases"));
        instructors.add(new Instructor("Goce", "Armenski", "gocearmenski@gmail.com", "Client side Internet Programming"));


        workshops.add(new Workshop("Algorithms and Data Structures", "Programming", 49.99, 20, instructors.get(0)));
        workshops.add(new Workshop("Structural Programming", "Programming", 25.75, 0, instructors.get(1)));
        workshops.add(new Workshop("Advanced Web Design", "Web", 35.0, 35, instructors.get(2)));
        workshops.add(new Workshop("Cybersecurity", "Security", 41.99, 18, instructors.get(2)));
        workshops.add(new Workshop("Advanced Programming", "Programming", 54.99, 25, instructors.get(1)));
        workshops.add(new Workshop("Artificial Intelligence", "AI", 39.99, 27, instructors.get(0)));
        workshops.add(new Workshop("Web Programming", "Programming", 19.99, 22, instructors.get(1)));
        workshops.add(new Workshop("Database", "Databases", 30, 10, instructors.get(0)));
        workshops.add(new Workshop("Computer Architecture and Organization", "Computer Engineer", 20, 10, instructors.get(1)));
        workshops.add(new Workshop("Calculus 1", "Math", 18.99, 14, instructors.get(2)));

        registrations = new ArrayList<>();

    }

}
