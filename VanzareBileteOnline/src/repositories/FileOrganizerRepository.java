package repositories;

import models.Organizer;

import java.util.Optional;

public class FileOrganizerRepository implements OrganizerRepository {

    @Override
    public void addOrganizer(Organizer organizer) {

    }

    @Override
    public Optional<Organizer> findOrganizerByUsername(String username) {
        return Optional.empty();
    }
}
