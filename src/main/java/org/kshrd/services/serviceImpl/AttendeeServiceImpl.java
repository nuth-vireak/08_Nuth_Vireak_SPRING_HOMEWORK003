package org.kshrd.services.serviceImpl;

import org.kshrd.dto.AttendeeRequest;
import org.kshrd.models.Attendee;
import org.kshrd.repository.AttendeeRepository;
import org.kshrd.services.AttendeeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttendeeServiceImpl implements AttendeeService {

    private final AttendeeRepository attendeeRepository;

    public AttendeeServiceImpl(AttendeeRepository attendeeRepository) {
        this.attendeeRepository = attendeeRepository;
    }

    @Override
    public List<Attendee> getAttendees(Integer offSet, Integer limit) {
        return attendeeRepository.getAttendees(offSet, limit);
    }

    @Override
    public Attendee addNewAttendee(AttendeeRequest attendeeRequest) {
        return attendeeRepository.addNewAttendee(attendeeRequest);
    }

    @Override
    public Attendee getAttendeeById(Integer id) {
        return attendeeRepository.getAttendeeById(id);
    }

    @Override
    public Attendee deleteAttendeeById(Integer id) {
        return attendeeRepository.deleteAttendeeById(id);
    }

    @Override
    public Attendee updateAttendeeById(Integer id, AttendeeRequest attendeeRequest) {
        return attendeeRepository.updateAttendeeById(id, attendeeRequest);
    }
}
