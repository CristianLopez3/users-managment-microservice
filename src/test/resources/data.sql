-- Insert data into companies
INSERT INTO companies (name, address, operation_city)
VALUES ('Company One', '123 Main St, Cityville', 'Cityville'),
       ('Company Two', '456 Elm St, Townsville', 'Townsville');

-- Insert data into departments
INSERT INTO departments (name, description, company_id)
VALUES ('HR', 'Human Resources Department', 1),
       ('IT', 'Information Technology Department', 2);

-- Insert data into users
INSERT INTO users (name, lastname, position, address, telephone, residence_city, state, company_id, department_id)
VALUES ('John', 'Doe', 'Manager', '789 Maple St, Villageville', '123-456-7890', 'Villageville', 1, 1, 1),
       ('Jane', 'Smith', 'Engineer', '321 Oak St, Hamletville', '098-765-4321', 'Hamletville', 1, 2, 2);