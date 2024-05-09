CREATE DATABASE dreamcabana;

USE dreamcabana;

CREATE TABLE Customer (
                          customer_id varchar(255)  PRIMARY KEY,
                          name varchar(255) ,
                          address varchar(255),
                          contact varchar(255),
                          Date date
);

CREATE TABLE Room (
                      room_id varchar(255) primary key,
                      type varchar(255) ,
                      Date date,
                      customer_id varchar(255),
                      FOREIGN KEY (customer_id) REFERENCES Customer (customer_id)
);

CREATE TABLE Supplier (
                          supplier_id varchar(255) primary key,
                          name varchar(255) ,
                          address varchar(255),
                          contact varchar(255) ,
                          Date date
);

CREATE TABLE Employee (
                          employee_id varchar(255) primary key,
                          name varchar(255) ,
                          address varchar(255) ,
                          contact varchar(255),
                          Job_role varchar(255)
);

CREATE TABLE Kitchen (
                         kitchen_id varchar(255) primary key,
                         location varchar(255)
);

CREATE TABLE Duty_Details (
                              duty_id varchar(255),
                              room_id varchar(255) ,
                              employee_id varchar(255) ,
                              FOREIGN KEY (room_id) REFERENCES Room (room_id),
                              FOREIGN KEY (employee_id) REFERENCES Employee (employee_id)
);





CREATE TABLE Food_Item (
                           food_item_id varchar(255) primary key,
                           name varchar(255) ,
                           price decimal(10,2)
);

CREATE TABLE Packages (
                          package_id varchar(255) primary key,
                          name varchar(255) ,
                          Date date,
                          customer_id varchar(255),
                          FOREIGN KEY (customer_id) REFERENCES Customer (customer_id)
);

CREATE TABLE Payment (
                         payment_id varchar(255) primary key,
                         amount decimal(10,2) ,
                         payment_date datetime ,
                         customer_id varchar(255),
                         FOREIGN KEY (customer_id) REFERENCES Customer (customer_id)
);


CREATE TABLE Food_Details (
                              food_detail_id varchar(255) ,
                              package_id varchar(255) ,
                              food_item_id varchar(255) ,
                              FOREIGN KEY (package_id) REFERENCES Packages (package_id),
                              FOREIGN KEY (food_item_id) REFERENCES Food_Item (food_item_id)
);