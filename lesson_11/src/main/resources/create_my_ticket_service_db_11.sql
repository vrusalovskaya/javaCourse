DROP DATABASE IF EXISTS my_ticket_service_db_11 WITH (FORCE);

CREATE DATABASE my_ticket_service_db_11;

\c my_ticket_service_db_11;

CREATE TYPE ticket_type AS ENUM ('DAY', 'WEEK', 'MONTH', 'YEAR');

CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    is_active BOOLEAN DEFAULT FALSE,
    creation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE tickets (
    id SERIAL PRIMARY KEY,
    user_id INTEGER REFERENCES users(id) ON DELETE CASCADE,
    ticket_type ticket_type NOT NULL,
    creation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
