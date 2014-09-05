﻿CREATE TABLE PERSON (
PERSON_ID SERIAL PRIMARY KEY,
NAME VARCHAR(255),
SURNAME VARCHAR(255),
COM_NAME VARCHAR(255),
DESCRIPTION TEXT);

CREATE TABLE ADDRESS (
ADDRESS_ID SERIAL PRIMARY KEY,
PERSON_ID INTEGER REFERENCES PERSON,
STREET VARCHAR(255),
HOUSE_NO VARCHAR(20),
FLAT_NO VARCHAR(20),
CITY VARCHAR(255),
POST_CODE VARCHAR(20),
COUNTRY VARCHAR(255));

CREATE TABLE PHONE (
PHONE_ID SERIAL PRIMARY KEY,
PERSON_ID INTEGER REFERENCES PERSON,
NUMBER VARCHAR(30) NOT NULL);

CREATE TABLE EMAIL (
EMAIL_ID SERIAL PRIMARY KEY,
PERSON_ID INTEGER REFERENCES PERSON,
EMAIL VARCHAR(255) NOT NULL);

DROP TABLE ADDRESS CASCADE;
DROP TABLE PHONE CASCADE;
DROP TABLE EMAIL CASCADE;
DROP TABLE PERSON CASCADE;

