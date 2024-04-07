package org.kshrd.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Event {
    private Integer eventId;
    private String eventName;
    private Date eventDate;
    private Venue venue;
    private List<Attendee> attendees;
}
