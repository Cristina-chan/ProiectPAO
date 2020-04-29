package repositories.organizerRepositories;

import models.Organizer;
import models.Type;

import java.util.Optional;

public interface OrganizerRepository {

    void addOrganizer(Organizer organizer);
    Optional<Organizer> findOrganizerByUsername(String username);

    static OrganizerRepository build(Type type) {
        switch (type) {
            case COLLECTION: return new CollectionOrganizerRepository();
            case FILE: return new FileOrganizerRepository();
        }

        throw new RuntimeException("No such type");
    }
}
