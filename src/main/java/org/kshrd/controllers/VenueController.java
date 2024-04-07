package org.kshrd.controllers;

import org.kshrd.dto.APIResponse;
import org.kshrd.dto.VenueRequest;
import org.kshrd.models.Venue;
import org.kshrd.services.VenueService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/venues")
public class VenueController {

    private static final Logger log = LoggerFactory.getLogger(VenueController.class);
    private final VenueService venueService;

    public VenueController(VenueService venueService) {
        this.venueService = venueService;
    }

    @GetMapping
    public ResponseEntity<APIResponse<List<Venue>>> getVenues(@RequestParam(defaultValue = "0") Integer offSet,
                                                              @RequestParam(defaultValue = "3") Integer limit) {
        List<Venue> venues = venueService.getVenues(offSet, limit);
        APIResponse<List<Venue>> venueApiResponse = APIResponse.<List<Venue>>builder()
                .message("All venues have been successfully fetched.")
                .payload(venues)
                .status(HttpStatus.OK)
                .time(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(venueApiResponse);
    }

    @PostMapping
    public ResponseEntity<APIResponse<Venue>> addNewVenue(@RequestBody VenueRequest venueRequest) {
        Venue venue = venueService.addNewVenue(venueRequest);
        APIResponse<Venue> venueApiResponse = APIResponse.<Venue>builder()
                .message("The venue has been successfully added.")
                .payload(venue)
                .status(HttpStatus.CREATED)
                .time(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(venueApiResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIResponse<Venue>> getVenueById(@PathVariable Integer id) {
        Venue venue = venueService.getVenueById(id);
        System.out.println(venue);
        APIResponse<Venue> venueApiResponse = APIResponse.<Venue>builder()
                .message("The venue has been successfully fetched.")
                .payload(venue)
                .status(HttpStatus.OK)
                .time(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(venueApiResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse<Venue>> deleteVenueById(@PathVariable Integer id) {
        Venue venue = venueService.deleteVenueById(id);
        APIResponse<Venue> venueApiResponse = APIResponse.<Venue>builder()
                .message("The venue has been successfully deleted.")
                .payload(venue)
                .status(HttpStatus.OK)
                .time(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(venueApiResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<APIResponse<Venue>> updateVenueById(@PathVariable Integer id, @RequestBody VenueRequest venueRequest) {
        Venue venue = venueService.updateVenueById(id, venueRequest);
        APIResponse<Venue> venueApiResponse = APIResponse.<Venue>builder()
                .message("The venue has been successfully updated.")
                .payload(venue)
                .status(HttpStatus.OK)
                .time(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(venueApiResponse);
    }
}
