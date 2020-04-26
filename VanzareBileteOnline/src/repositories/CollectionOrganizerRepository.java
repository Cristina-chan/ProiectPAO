package repositories;

import models.Client;
import models.Organizer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CollectionOrganizerRepository implements OrganizerRepository {

    private List<Organizer> organizers = new ArrayList<>();
    private int nrOrganizers;

    @Override
    public void addOrganizer(Organizer organizer) {
        organizers.add(organizer);
        nrOrganizers++;
    }

    @Override
    public Optional<Organizer> findOrganizerByUsername(String username) {
        for (Organizer o : organizers) {
            if (o.getUsername().equals(username)) {
                return Optional.of(o);
            }
        }

        return Optional.empty();
    }
}
