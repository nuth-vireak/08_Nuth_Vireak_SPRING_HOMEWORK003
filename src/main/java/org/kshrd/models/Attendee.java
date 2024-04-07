package org.kshrd.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Attendee {
    private Integer attendeeId;
    private String attendeeName;
    private String email;
}
