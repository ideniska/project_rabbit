-- users
CREATE TABLE IF NOT EXISTS users (
    userId SERIAL PRIMARY KEY,
    first_name VARCHAR(100) NULL,
    last_name VARCHAR(100) NULL,
    email VARCHAR(100) NOT NULL,
    role VARCHAR(100) NOT NULL,
    createdAt TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);