package repositories;

import models.Organizer;

import java.util.Optional;

public interface OrganizerRepository {

    void addOrganizer(Organizer organizer);
    Optional<Organizer> findOrganizerByUsername(String username);

    static OrganizerRepository build(OrganizerRepository.Type type) {
        switch (type) {
            case COLLECTION: return new CollectionOrganizerRepository();
            case FILE: return new FileOrganizerRepository();
        }

        throw new RuntimeException("No such type");
    }

    enum Type {
        COLLECTION, FILE
    }
}
