package org.kshrd.services.serviceImpl;

import org.kshrd.dto.EventRequest;
import org.kshrd.models.Attendee;
import org.kshrd.models.Event;
import org.kshrd.repository.AttendeeRepository;
import org.kshrd.repository.EventRepository;
import org.kshrd.services.EventService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final AttendeeRepository attendeeRepository;

    public EventServiceImpl(EventRepository eventRepository, AttendeeRepository attendeeRepository) {
        this.eventRepository = eventRepository;
        this.attendeeRepository = attendeeRepository;
    }

    @Override
    public Event addNewEvent(EventRequest eventRequest) {
        System.out.println("Event Request: " + eventRequest);
        Event event = eventRepository.addNewEvent(eventRequest);
        List<Attendee> attendees = new ArrayList<>();
        for (Integer attendeeId : eventRequest.getAttendeeIds()) {
            Integer i = eventRepository.addAttendeeToEvent(event.getEventId(), attendeeId);
            Attendee attendee = attendeeRepository.getAttendeeById(i);
            attendees.add(attendee);
        }
        event.setAttendees(attendees);
        return event;
    }

    @Override
    public List<Event> getEvents(Integer offSet, Integer limit) {
        return eventRepository.getEvents(offSet, limit);
    }

    @Override
    public Event getEventById(Integer id) {
        return eventRepository.getEventById(id);
    }

    @Override
    public void deleteEventById(Integer id) {
        eventRepository.deleteAttendeesByEventId(id);
        eventRepository.deleteEventById(id);
    }

    @Override
    public Event updateEventById(Integer id, EventRequest eventRequest) {

        Event eventById = eventRepository.getEventById(id);

        if (eventById == null) {
            return null;
        }

        Event event = eventRepository.updateEventById(id, eventRequest);
        List<Attendee> attendees = new ArrayList<>();
        for (Integer attendeeId : eventRequest.getAttendeeIds()) {
            Integer i = eventRepository.addAttendeeToEvent(event.getEventId(), attendeeId);
            Attendee attendee = attendeeRepository.getAttendeeById(i);
            attendees.add(attendee);
        }
        event.setAttendees(attendees);
        return event;
    }
}
