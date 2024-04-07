package org.kshrd.repository;

import org.apache.ibatis.annotations.*;
import org.kshrd.dto.EventRequest;
import org.kshrd.models.Event;

import java.util.List;

@Mapper
public interface EventRepository {

    @Select("""
            INSERT INTO events (event_name, event_date, venue_id)
            VALUES (#{eventRequest.eventName}, #{eventRequest.eventDate}, #{eventRequest.venueId})
            RETURNING *
            """)
    @Results(id = "EventResultMap", value = {
            @Result(property = "eventId", column = "event_id"),
            @Result(property = "eventName", column = "event_name"),
            @Result(property = "eventDate", column = "event_date"),
            @Result(property = "venue", column = "venue_id",
                    one = @One(select = "org.kshrd.repository.VenueRepository.getVenueById")),
            @Result(property = "attendees", column = "event_id",
                    many = @Many(select = "org.kshrd.repository.AttendeeRepository.getAttendeesByEventId"))
    })
    Event addNewEvent(@Param("eventRequest") EventRequest eventRequest);

    @Select("""
            INSERT INTO event_attendees (event_id, attendee_id)
            VALUES (#{eventId}, #{attendeeId})
            RETURNING attendee_id
            """)
    Integer addAttendeeToEvent(@Param("eventId") Integer eventId, @Param("attendeeId") Integer attendeeId);

    @Select("""
            SELECT * FROM events
            LIMIT #{limit} OFFSET #{offSet}
            """)
    @ResultMap("EventResultMap")
    List<Event> getEvents(@Param("offSet") Integer offSet, @Param("limit") Integer limit);

    @Select("""
            SELECT * FROM events
            WHERE event_id = #{id}
            """)
    @ResultMap("EventResultMap")
    Event getEventById(@Param("id") Integer id);

    @Select("""
            DELETE FROM events
            WHERE event_id = #{id}
            RETURNING *
            """)
    @ResultMap("EventResultMap")
    void deleteEventById(@Param("id") Integer id);

    @Select("""
            DELETE FROM event_attendees
            WHERE event_id = #{eventId}
            """)
    void deleteAttendeesByEventId(@Param("eventId") Integer eventId);

    @Select("""
            UPDATE events
            SET event_name = #{eventRequest.eventName},
                event_date = #{eventRequest.eventDate},
                venue_id = #{eventRequest.venueId}
            WHERE event_id = #{id}
            RETURNING *
            """)
    @ResultMap("EventResultMap")
    Event updateEventById(@Param("id") Integer id, @Param("eventRequest") EventRequest eventRequest);
}