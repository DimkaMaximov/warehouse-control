DROP TABLE IF EXISTS user_entity CASCADE ;
DROP SEQUENCE IF EXISTS user_entity_seq;

CREATE SEQUENCE user_entity_seq START WITH 1 INCREMENT 1;

CREATE TABLE user_entity
(
    id           BIGINT PRIMARY KEY DEFAULT nextval('user_entity_seq'),
    email        VARCHAR(128) UNIQUE NOT NULL,
    password     VARCHAR(250)        NOT NULL,
    first_name   VARCHAR(100),
    last_name    VARCHAR(100),
    phone_number VARCHAR(32)
);

CREATE TYPE user_role AS ENUM ('ROLE_ADMIN', 'ROLE_USER', 'ROLE_OWNER');
ALTER TABLE user_entity ADD COLUMN role user_role DEFAULT 'ROLE_USER' ;