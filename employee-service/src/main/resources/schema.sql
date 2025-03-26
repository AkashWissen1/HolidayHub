CREATE TABLE IF NOT EXISTS employees (
	employee_id SERIAL PRIMARY KEY,
	employee_name VARCHAR(100) NOT NULL,
	designation VARCHAR(50) NOT NULL,
	email VARCHAR(100) UNIQUE NOT NULL,
	password VARCHAR(255) NOT NULL,
	client_id BIGINT
);

