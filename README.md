Test README.md

SQL Scripts:

USE programme;

INSERT INTO customer (email, name, number, password) VALUES
('testcustomer1@example.com', 'Test Customer 1', '1234567890', 'password1'),
('testcustomer2@example.com', 'Test Customer 2', '0987654321', 'password2');



-- Insert into customer table with AUTO_INCREMENT for id
INSERT INTO customer (email, name, number, password) VALUES
('programmer1@example.com', 'Programmer One', '1234567890', 'password1'),
('programmer2@example.com', 'Programmer Two', '0987654321', 'password2');


-- Insert into programmer table
INSERT INTO programmer (id, description, skills) VALUES
((SELECT id FROM customer WHERE email = 'programmer1@example.com'), 'Experienced in Java and Spring Boot', 'Java, Spring Boot, MySQL'),
((SELECT id FROM customer WHERE email = 'programmer2@example.com'), 'Expert in Python and Django', 'Python, Django, PostgreSQL');


SELECT * FROM programmer;

-- Insert into post table
INSERT INTO post (title, description, price, date, programmer_id) VALUES
('Build a Spring Boot Application', 'I will build a complete Spring Boot application for you.', 500.00, '2023-01-01', 4),
('Create a Django Website', 'I will create a full-featured Django website.', 600.00, '2023-02-01', 5);

SELECT * FROM post;
