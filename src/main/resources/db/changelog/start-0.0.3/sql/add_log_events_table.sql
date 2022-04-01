CREATE SEQUENCE log_events_seq START WITH 1;
CREATE TABLE log_events
(
    id BIGINT PRIMARY KEY DEFAULT nextval('log_events_seq'),
    event_time      TIMESTAMP,
    method_name     VARCHAR(100),
    class_name      VARCHAR(200),
    args            VARCHAR(200)
);