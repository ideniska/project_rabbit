-- users
CREATE TABLE IF NOT EXISTS users (
    userId SERIAL PRIMARY KEY,
    email VARCHAR(100) NOT NULL,
    role VARCHAR(100) NOT NULL,
    createdAt TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);
