package mk.ukim.finki.wp.lab.model;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class Workshop {
    private Long id;
    private String title;
    private String category;
    private double price;
    private int capacity;   // number of free seats
    private Instructor instructor;

    public Workshop(String title, String category, double price, int capacity, Instructor instructor) {
        this.id = (long) (Math.random() * 1000);
        this.title = title;
        this.category = category;
        this.price = price;
        this.capacity = capacity;
        this.instructor = instructor;
    }


}
