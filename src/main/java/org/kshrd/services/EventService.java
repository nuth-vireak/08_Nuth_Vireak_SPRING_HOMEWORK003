package org.kshrd.services;

import org.kshrd.dto.EventRequest;
import org.kshrd.models.Event;

import java.util.List;

public interface EventService {

    Event addNewEvent(EventRequest eventRequest);
    List<Event> getEvents(Integer offSet, Integer limit);
    Event getEventById(Integer id);
    void deleteEventById(Integer id);
    Event updateEventById(Integer id, EventRequest eventRequest);
}
