package org.kshrd.controllers;

import org.kshrd.dto.APIResponse;
import org.kshrd.dto.AttendeeRequest;
import org.kshrd.models.Attendee;
import org.kshrd.services.AttendeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("api/v1/attendee")
public class AttendeeController {

    private final AttendeeService attendeeService;

    public AttendeeController(AttendeeService attendeeService){
        this.attendeeService = attendeeService;
    }

    @GetMapping
    public ResponseEntity<APIResponse<List<Attendee>>> getAttendees(@RequestParam(defaultValue = "0") Integer offSet,
                                                                    @RequestParam(defaultValue = "3") Integer limit){
        List<Attendee> attendees = attendeeService.getAttendees(offSet, limit);

        APIResponse<List<Attendee>> apiResponse = APIResponse.<List<Attendee>>builder()
                .message("All attendees have been successfully fetched.")
                .payload(attendees)
                .status(HttpStatus.OK)
                .time(LocalDateTime.now())
                .build();

        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping
    public ResponseEntity<APIResponse<Attendee>> addNewAttendee(@RequestBody AttendeeRequest attendeeRequest){
        Attendee attendee = attendeeService.addNewAttendee(attendeeRequest);

        APIResponse<Attendee> apiResponse = APIResponse.<Attendee>builder()
                .message("The attendee has been successfully added.")
                .payload(attendee)
                .status(HttpStatus.CREATED)
                .time(LocalDateTime.now())
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIResponse<Attendee>> getAttendeeById(@PathVariable Integer id){
        Attendee attendee = attendeeService.getAttendeeById(id);

        APIResponse<Attendee> apiResponse = APIResponse.<Attendee>builder()
                .message("The attendee has been successfully fetched.")
                .payload(attendee)
                .status(HttpStatus.OK)
                .time(LocalDateTime.now())
                .build();

        return ResponseEntity.ok(apiResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse<Attendee>> deleteAttendeeById(@PathVariable Integer id){
        Attendee attendee = attendeeService.deleteAttendeeById(id);

        APIResponse<Attendee> apiResponse = APIResponse.<Attendee>builder()
                .message("The attendee has been successfully deleted.")
                .payload(attendee)
                .status(HttpStatus.OK)
                .time(LocalDateTime.now())
                .build();

        return ResponseEntity.ok(apiResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<APIResponse<Attendee>> updateAttendeeById(@PathVariable Integer id, @RequestBody AttendeeRequest attendeeRequest){
        Attendee attendee = attendeeService.updateAttendeeById(id, attendeeRequest);

        APIResponse<Attendee> apiResponse = APIResponse.<Attendee>builder()
                .message("The attendee has been successfully updated.")
                .payload(attendee)
                .status(HttpStatus.OK)
                .time(LocalDateTime.now())
                .build();

        return ResponseEntity.ok(apiResponse);
    }
}
