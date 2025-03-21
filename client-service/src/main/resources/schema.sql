CREATE TABLE IF NOT EXISTS clients (
    client_id SERIAL PRIMARY KEY,
    client_name VARCHAR(255),
    contact_person VARCHAR(255),
    contact_email VARCHAR(255)
);
