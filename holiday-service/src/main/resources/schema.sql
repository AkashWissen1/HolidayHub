CREATE TABLE IF NOT EXISTS holidays (
    holiday_id SERIAL PRIMARY KEY,
    holiday_date DATE,
    holiday_name VARCHAR(255),
    client_id BIGINT
);
