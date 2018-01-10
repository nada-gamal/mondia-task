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

DROP TABLE SERVICE;
DROP TABLE LO_OPERATOR;
DROP TABLE PRODUCT;