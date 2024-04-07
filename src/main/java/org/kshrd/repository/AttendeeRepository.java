package org.kshrd.repository;

import org.apache.ibatis.annotations.*;
import org.kshrd.dto.AttendeeRequest;
import org.kshrd.models.Attendee;

import java.util.List;

@Mapper
public interface AttendeeRepository {

    @Select("""
            SELECT * FROM attendees
            ORDER BY attendee_id
            LIMIT #{limit} OFFSET #{offSet}
            """)
    @Results(id = "AttendeeResultMap", value = {
            @Result(property = "attendeeId", column = "attendee_id"),
            @Result(property = "attendeeName", column = "attendee_name"),
            @Result(property = "email", column = "attendee_email")
    })
    List<Attendee> getAttendees(@Param("offSet") Integer offSet, @Param("limit") Integer limit);

    @Select("""
            INSERT INTO attendees(attendee_name, attendee_email)
            VALUES(#{attendee.attendeeName}, #{attendee.email})
            returning *
            """)
    @ResultMap("AttendeeResultMap")
    Attendee addNewAttendee(@Param("attendee") AttendeeRequest attendee);

    @Select("""
            SELECT * FROM attendees
            WHERE attendee_id = #{id}
            """)
    @ResultMap("AttendeeResultMap")
    Attendee getAttendeeById(@Param("id") Integer id);

    @Select("""
            DELETE FROM attendees
            WHERE attendee_id = #{id}
            """)
    @ResultMap("AttendeeResultMap")
    Attendee deleteAttendeeById(@Param("id") Integer id);

    @Select("""
            UPDATE attendees
            SET attendee_name = #{attendee.attendeeName},
                attendee_email = #{attendee.email}
            WHERE attendee_id = #{id}
            returning *
            """)
    @ResultMap("AttendeeResultMap")
    Attendee updateAttendeeById(@Param("id") Integer id, @Param("attendee") AttendeeRequest attendee);

    @Select("""
            SELECT a.* FROM attendees a
            INNER JOIN event_attendees ea ON a.attendee_id = ea.attendee_id
            WHERE ea.event_id = #{eventId}
            """)
    @ResultMap("AttendeeResultMap")
    List<Attendee> getAttendeesByEventId(Integer eventId);
}
