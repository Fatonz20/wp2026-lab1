package mk.ukim.finki.wp.lab.service.impl;

import lombok.AllArgsConstructor;
import mk.ukim.finki.wp.lab.model.Instructor;
import mk.ukim.finki.wp.lab.model.Workshop;
import mk.ukim.finki.wp.lab.repository.InstructorRepository;
import mk.ukim.finki.wp.lab.repository.WorkshopRepository;
import mk.ukim.finki.wp.lab.service.WorkshopService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class WorkshopServiceImpl implements WorkshopService {

    private final WorkshopRepository workshopRepository;
    private final InstructorRepository instructorRepository;

    @Override
    public List<Workshop> listAll() {
        return workshopRepository.findAll();
    }

    @Override
    public List<Workshop> search(String text, Double maxPrice) {
        return workshopRepository.search(text, maxPrice);
    }

    @Override
    public Optional<Workshop> findById(Long id) {
        return workshopRepository.findById(id);
    }

    @Override
    public Workshop create(String title, String category, Double price,
                           Integer capacity, Long instructorId) {

        validate(title, category, price, capacity);

        Instructor instructor = instructorRepository.findById(instructorId)
                .orElseThrow(() -> new IllegalArgumentException("InstructorNotFound"));

        Workshop workshop = new Workshop(
                title, category, price, capacity, instructor);

        return workshopRepository.save(workshop);
    }

    @Override
    public Workshop update(Long id, String title, String category,
                           Double price, Integer capacity, Long instructorId) {

        validate(title, category, price, capacity);

        Workshop workshop = workshopRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("WorkshopNotFound"));

        Instructor instructor = instructorRepository.findById(instructorId)
                .orElseThrow(() -> new IllegalArgumentException("InstructorNotFound"));

        workshop.setTitle(title);
        workshop.setCategory(category);
        workshop.setPrice(price);
        workshop.setCapacity(capacity);
        workshop.setInstructor(instructor);

        return workshopRepository.save(workshop);
    }

    private void validate(String title, String category,
                          Double price, Integer capacity) {

        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Title is required");
        }

        if (category == null || category.trim().isEmpty()) {
            throw new IllegalArgumentException("Category is required");
        }

        if (price == null || price < 0) {
            throw new IllegalArgumentException("Price must be non-negative");
        }

        if (capacity == null || capacity < 0) {
            throw new IllegalArgumentException("Capacity must be non-negative");
        }
    }

    @Override
    public void deleteById(Long id) {
        workshopRepository.deleteById(id);
    }

    @Override
    public void saveRating(Long id, double rating) {
        Workshop workshop = workshopRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("WorkshopNotFound"));

        int oldCount = workshop.getNumberOfRatings();
        double newAverage = (workshop.getRating() * oldCount + rating) / (oldCount + 1);

        workshop.setNumberOfRatings(++oldCount);

        workshop.setRating(newAverage);

        workshopRepository.save(workshop);
    }
}