package org.kshrd.services.serviceImpl;

import org.kshrd.dto.VenueRequest;
import org.kshrd.models.Venue;
import org.kshrd.repository.VenueRepository;
import org.kshrd.services.VenueService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VenueServiceImpl implements VenueService {

    private final VenueRepository venueRepository;

    public VenueServiceImpl(VenueRepository venueRepository) {
        this.venueRepository = venueRepository;
    }

    @Override
    public List<Venue> getVenues(Integer offSet, Integer limit) {
        return venueRepository.getVenues(offSet, limit);
    }

    @Override
    public Venue addNewVenue(VenueRequest venueRequest) {
        return venueRepository.addNewVenue(venueRequest);
    }
    @Override
    public Venue getVenueById(Integer id) {
        return venueRepository.getVenueById(id);
    }

    @Override
    public Venue deleteVenueById(Integer id) {
        return venueRepository.deleteVenueById(id);
    }

    @Override
    public Venue updateVenueById(Integer id, VenueRequest venueRequest) {
        return venueRepository.updateVenueById(id, venueRequest);
    }
}
