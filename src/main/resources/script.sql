CREATE DATABASE event_ticketing_system_db;

-- table attendees
CREATE TABLE attendees
(
    attendee_id    SERIAL PRIMARY KEY,
    attendee_name  VARCHAR(100) NOT NULL,
    attendee_email VARCHAR(100) NOT NULL
);

-- table venues
CREATE TABLE venues
(
    venue_id       SERIAL PRIMARY KEY,
    venue_name     VARCHAR(100) NOT NULL,
    venue_location VARCHAR(100) NOT NULL
);

-- table events
CREATE TABLE events
(
    event_id   SERIAL PRIMARY KEY,
    event_name VARCHAR(100) NOT NULL,
    event_date DATE         NOT NULL,

    venue_id   INT          NOT NULL,
    FOREIGN KEY (venue_id) REFERENCES venues (venue_id)
);

-- table event_attendees
CREATE TABLE event_attendees
(
    event_attendee_id SERIAL PRIMARY KEY,
    event_id         INT NOT NULL,
    attendee_id      INT NOT NULL,

    FOREIGN KEY (event_id) REFERENCES events (event_id),
    FOREIGN KEY (attendee_id) REFERENCES attendees (attendee_id)
);