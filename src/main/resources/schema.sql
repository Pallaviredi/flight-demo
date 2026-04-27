CREATE TABLE flight (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    origin VARCHAR(10),
    destination VARCHAR(10),
    flight_number INT,
    price INT,
    departure_time VARCHAR(20)
);
