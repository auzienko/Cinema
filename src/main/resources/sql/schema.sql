CREATE TABLE IF NOT EXISTS administrators
(
    id               BIGSERIAL PRIMARY KEY,
    email            VARCHAR,
    password         VARCHAR
);

CREATE TABLE IF NOT EXISTS posters
(
    id               BIGSERIAL PRIMARY KEY,
    file_name        VARCHAR,
    size             BIGINT,
    mime             VARCHAR,
    file_name_UUID   VARCHAR,
    administrator_id BIGINT
);

CREATE TABLE IF NOT EXISTS movies
(
    id               BIGSERIAL PRIMARY KEY,
    title            VARCHAR,
    year_of_release  int,
    age_restriction  int,
    description      VARCHAR,
    poster_id        BIGINT,
    administrator_id BIGINT
);

CREATE TABLE IF NOT EXISTS movie_halls
(
    id               BIGSERIAL PRIMARY KEY,
    serial_number    INTEGER,
    seats            INTEGER,
    administrator_id BIGINT
);

CREATE TABLE IF NOT EXISTS sessions
(
    id               BIGSERIAL PRIMARY KEY,
    movie_id         BIGINT,
    date_time        TIMESTAMP,
    cost             INTEGER,
    movie_hall_id    BIGINT,
    administrator_id BIGINT
);

create table if not exists messages
(
    id              bigserial primary key,
    text            varchar,
    date            timestamp with time zone default current_timestamp,
    author_id       bigint references administrators(id),
    film_id         bigint references movies(id)
);