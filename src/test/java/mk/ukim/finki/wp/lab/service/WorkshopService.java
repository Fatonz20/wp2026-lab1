package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Workshop;

import java.util.List;
import java.util.Optional;

public interface WorkshopService {
    List<Workshop> listAll();
    List<Workshop> search(String text, Double maxPrice);
    Optional<Workshop> findById(Long id);
    Workshop save(Workshop workshop);
    void deleteById(Long id);
}
