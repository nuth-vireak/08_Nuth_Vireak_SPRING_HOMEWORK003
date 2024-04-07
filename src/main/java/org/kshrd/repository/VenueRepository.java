package org.kshrd.repository;

import org.apache.ibatis.annotations.*;
import org.kshrd.dto.VenueRequest;
import org.kshrd.models.Venue;

import java.util.List;

@Mapper
public interface VenueRepository {

    @Select("""
            SELECT * FROM venues
            ORDER BY venue_id
            LIMIT #{limit} OFFSET #{offSet}
            """)
    @Results(id = "VenueResultMap", value = {
            @Result(property = "venueId", column = "venue_id"),
            @Result(property = "venueName", column = "venue_name"),
            @Result(property = "location", column = "venue_location")
    })
    List<Venue> getVenues(@Param("offSet") Integer offSet, @Param("limit") Integer limit);

    @Select("""
            INSERT INTO venues(venue_name, venue_location)
            VALUES(#{venue.venueName}, #{venue.location})
            returning *
            """)
    @ResultMap("VenueResultMap")
    Venue addNewVenue(@Param("venue") VenueRequest venue);

    @Select("""
            SELECT * FROM venues
            WHERE venue_id = #{id}
            """)
    @ResultMap("VenueResultMap")
    Venue getVenueById(@Param("id") Integer id);

    @Select("""
            DELETE FROM venues
            WHERE venue_id = #{id}
            """)
    @ResultMap("VenueResultMap")
    Venue deleteVenueById(@Param("id") Integer id);

    @Select("""
            UPDATE venues
            SET venue_name = #{venue.venueName},
                venue_location = #{venue.location}
            WHERE venue_id = #{id}
            returning *
            """)
    @ResultMap("VenueResultMap")
    Venue updateVenueById(@Param("id") Integer id, @Param("venue") VenueRequest venue);
}
