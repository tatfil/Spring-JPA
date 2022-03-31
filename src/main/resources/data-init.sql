CREATE TABLE employee
(id SERIAL,
 employee_name varchar (50) NOT NULL,
 PRIMARY KEY (id)
);

CREATE TABLE event
(id SERIAL,
 name varchar (50) NOT NULL,
 type varchar (50) NOT NULL,
 date started not null,
 date ended not null,
 name varchar (100),
 PRIMARY KEY (id)
);

CREATE TABLE position
(id SERIAL,
 title varchar (50) NOT NULL,
 description varchar (100),
 salary_range int,
 PRIMARY KEY (id)
);