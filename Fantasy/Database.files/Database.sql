DROP DATABASE IF EXISTS Fantasy_Database;

CREATE DATABASE Fantasy_Database;

USE Fantasy_Database;

CREATE TABLE Footballer(
    name VARCHAR (20) PRIMARY KEY ,
    club VARCHAR(20),
    position VARCHAR (10),
    cost DOUBLE (10),
    totalPoints DOUBLE(10),
    pointsThisWeek DOUBLE(10)
);
