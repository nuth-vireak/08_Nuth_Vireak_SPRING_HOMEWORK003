package org.kshrd.services;

import org.kshrd.dto.AttendeeRequest;
import org.kshrd.models.Attendee;

import java.util.List;

public interface AttendeeService {
    List<Attendee> getAttendees(Integer offSet, Integer limit);
    Attendee addNewAttendee(AttendeeRequest attendeeRequest);
    Attendee getAttendeeById(Integer id);
    Attendee deleteAttendeeById(Integer id);
    Attendee updateAttendeeById(Integer id, AttendeeRequest attendeeRequest);
}
