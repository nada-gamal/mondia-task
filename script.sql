create table PRODUCT (
id number primary key,
name VARCHAR2(300) not null,
description VARCHAR2(1000),
min_price float,
max_price float
);

create table LO_OPERATOR (
id number primary key,
name VARCHAR2(300) not null,
country VARCHAR2(300) not null,
service_id CHAR,
package_id CHAR
);

create table SERVICE (
id number primary key,
name VARCHAR2(300) not null,
type VARCHAR2(300),
operator_id number,
operator_service_id number,
operator_package_id number,
product_id number,
CONSTRAINT check_service_type
  CHECK (type IN ('Subscription','Alacarte')),
CONSTRAINT fk_lo_operator
    FOREIGN KEY (operator_id)
    REFERENCES LO_OPERATOR(id)
);
ALTER TABLE SERVICE ADD CONSTRAINT FK_PRODUCT FOREIGN KEY (PRODUCT_ID) REFERENCES PRODUCT(ID);

INSERT INTO LO_OPERATOR VALUES (1, 'Orange', 'Egypt', 'M', 'O');
INSERT INTO LO_OPERATOR VALUES (2, 'Etisalat', 'Egypt', 'O', 'M');
INSERT INTO LO_OPERATOR VALUES (3, 'Vodafone', 'Egypt', 'M', 'M');

CREATE SEQUENCE product_seq
  MINVALUE 1
  MAXVALUE 999999999999999999999999999
  START WITH 3
  INCREMENT BY 1
  CACHE 20;
  
  CREATE SEQUENCE operator_seq
  MINVALUE 1
  MAXVALUE 999999999999999999999999999
  START WITH 4
  INCREMENT BY 1
  CACHE 20;
  
  CREATE SEQUENCE service_seq
  MINVALUE 1
  MAXVALUE 999999999999999999999999999
  START WITH 2
  INCREMENT BY 1
  CACHE 20;

DROP TABLE SERVICE;
DROP TABLE LO_OPERATOR;
DROP TABLE PRODUCT;
DROP sequence operator_seq;
DROP sequence product_seq;
DROP sequence service_seq;