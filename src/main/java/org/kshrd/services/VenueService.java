package org.kshrd.services;

import org.kshrd.dto.VenueRequest;
import org.kshrd.models.Venue;

import java.util.List;

public interface VenueService {
    List<Venue> getVenues(Integer offSet, Integer limit);
    Venue addNewVenue(VenueRequest venueRequest);
    Venue getVenueById(Integer id);
    Venue deleteVenueById(Integer id);
    Venue updateVenueById(Integer id, VenueRequest venueRequest);
}
