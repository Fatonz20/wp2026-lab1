package mk.ukim.finki.wp.lab.service.impl;


import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Registration;
import mk.ukim.finki.wp.lab.model.Workshop;
import mk.ukim.finki.wp.lab.repository.RegistrationRepository;
import mk.ukim.finki.wp.lab.repository.WorkshopRepository;
import mk.ukim.finki.wp.lab.service.RegistrationService;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.util.Optional;

@Service
public class RegistrationServiceImpl implements RegistrationService {
    private final RegistrationRepository registrationRepository;
    private final WorkshopRepository workshopRepository;

    public RegistrationServiceImpl(RegistrationRepository registrationRepository, WorkshopRepository workshopRepository) {
        this.registrationRepository = registrationRepository;
        this.workshopRepository = workshopRepository;
    }


    @Override
    public Registration register(String workshopTitle, String participantName, String participantEmail, int numberOfSeats) {
        if (workshopTitle == null || workshopTitle.isEmpty() || participantName == null || participantName.isEmpty() || participantEmail == null || participantEmail.isEmpty() || numberOfSeats < 1) {
            throw new IllegalArgumentException();
        }

        Optional<Workshop> optionalWorkshop = workshopRepository.findByTitle(workshopTitle);

        if (optionalWorkshop.isEmpty()) {
            throw new IllegalArgumentException();
        }

        Workshop workshop = optionalWorkshop.get();

        if (numberOfSeats > workshop.getCapacity()) {
            throw new IllegalArgumentException("Not enough free seats");
        }

        workshop.setCapacity(workshop.getCapacity() - numberOfSeats);

        Registration registration = new Registration(workshopTitle, participantName, participantEmail, numberOfSeats);

        return registrationRepository.save(registration);
    }
}
