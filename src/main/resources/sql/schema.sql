DROP schema if exists cinema cascade;
CREATE SCHEMA IF NOT EXISTS cinema;

CREATE TABLE IF NOT EXISTS cinema.posters
(
    id               BIGSERIAL PRIMARY KEY,
    type             INTEGER,
    file_name        VARCHAR,
    size             BIGINT,
    mime             VARCHAR,
    file_name_UUID   VARCHAR,
    administrator_id BIGINT
);

CREATE TABLE IF NOT EXISTS cinema.administrators
(
    id               BIGSERIAL PRIMARY KEY,
    email            VARCHAR,
    password         VARCHAR,
    name             VARCHAR,
    avatar_id        BIGINT references cinema.posters(id)
);

CREATE TABLE IF NOT EXISTS cinema.movies
(
    id               BIGSERIAL PRIMARY KEY,
    title            VARCHAR,
    year_of_release  int,
    age_restriction  int,
    description      VARCHAR,
    poster_id        BIGINT,
    administrator_id BIGINT
);

CREATE TABLE IF NOT EXISTS cinema.movie_halls
(
    id               BIGSERIAL PRIMARY KEY,
    serial_number    INTEGER,
    seats            INTEGER,
    administrator_id BIGINT
);

CREATE TABLE IF NOT EXISTS cinema.sessions
(
    id               BIGSERIAL PRIMARY KEY,
    movie_id         BIGINT,
    date_time        TIMESTAMP,
    cost             INTEGER,
    movie_hall_id    BIGINT,
    administrator_id BIGINT
);

create table if not exists cinema.messages
(
    id              bigserial primary key,
    text            varchar,
    date            timestamp,
    author_id       bigint references cinema.administrators(id),
    film_id         bigint references cinema.movies(id)
);

create table if not exists  cinema.user_auth_history
(
    id        BIGSERIAL PRIMARY KEY,
    date_time TIMESTAMP,
    ip        VARCHAR,
    user_id   BIGINT references cinema.administrators(id)
);