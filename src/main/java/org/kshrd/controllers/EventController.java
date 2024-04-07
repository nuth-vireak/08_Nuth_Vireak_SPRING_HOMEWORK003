package org.kshrd.controllers;

import org.kshrd.dto.APIResponse;
import org.kshrd.dto.EventRequest;
import org.kshrd.models.Event;
import org.kshrd.models.Venue;
import org.kshrd.services.EventService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/events")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService){
        this.eventService = eventService;
    }

    @PostMapping
    public ResponseEntity<APIResponse<Event>> addNewEvent(@RequestBody EventRequest eventRequest){

        Event event = eventService.addNewEvent(eventRequest);

        APIResponse<Event> apiResponse = APIResponse.<Event>builder()
                .message("The event has been successfully added.")
                .payload(event)
                .status(HttpStatus.CREATED)
                .time(LocalDateTime.now())
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }

    @GetMapping
    public ResponseEntity<APIResponse<List<Event>>> getEvents(@RequestParam(defaultValue = "0") Integer offSet,
                                                              @RequestParam(defaultValue = "3") Integer limit){

        List<Event> events = eventService.getEvents(offSet, limit);

        APIResponse<List<Event>> apiResponse = APIResponse.<List<Event>>builder()
                .message("All events have been successfully fetched.")
                .payload(events)
                .status(HttpStatus.OK)
                .time(LocalDateTime.now())
                .build();

        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIResponse<Event>> getEventById(@PathVariable Integer id){
        Event event = eventService.getEventById(id);

        APIResponse<Event> apiResponse = APIResponse.<Event>builder()
                .message("The event has been successfully fetched.")
                .payload(event)
                .status(HttpStatus.OK)
                .time(LocalDateTime.now())
                .build();

        return ResponseEntity.ok(apiResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse<Event>> deleteEventById(@PathVariable Integer id){

        eventService.deleteEventById(id);

        APIResponse<Event> apiResponse = APIResponse.<Event>builder()
                .message("The event has been successfully deleted.")
                .status(HttpStatus.OK)
                .time(LocalDateTime.now())
                .build();

        return ResponseEntity.ok(apiResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<APIResponse<Event>> updateEventById(@PathVariable Integer id, @RequestBody EventRequest eventRequest){
        Event event = eventService.updateEventById(id, eventRequest);

        APIResponse<Event> apiResponse = APIResponse.<Event>builder()
                .message("The event has been successfully updated.")
                .payload(event)
                .status(HttpStatus.OK)
                .time(LocalDateTime.now())
                .build();

        return ResponseEntity.ok(apiResponse);
    }
}
