DROP TABLE IF EXISTS supplier CASCADE;
DROP TABLE IF EXISTS product_type CASCADE;
DROP TABLE IF EXISTS product_list CASCADE;

DROP SEQUENCE IF EXISTS supplier_seq;
DROP SEQUENCE IF EXISTS product_type_seq;
DROP SEQUENCE IF EXISTS product_list_seq;

CREATE SEQUENCE product_list_seq START WITH 1 INCREMENT 1;
CREATE SEQUENCE supplier_seq START WITH 1;
CREATE SEQUENCE product_type_seq START WITH 1;

CREATE TABLE product_list
(
    id  BIGINT PRIMARY KEY DEFAULT nextval('product_list_seq'),
    product_name        VARCHAR(50),
    product_type        BIGINT      NOT NULL,
    product_cod         VARCHAR(32) NOT NULL,
    country             VARCHAR(50),
    supplier_id         BIGINT      NOT NULL,
    unit                VARCHAR(5),
    amount              BIGINT      NOT NULL,
    price               NUMERIC(100, 2)
);

CREATE TABLE product_type
(
    id BIGINT PRIMARY KEY DEFAULT nextval('product_type_seq'),
    product_id          BIGINT      NOT NULL,
    product_type        VARCHAR (50) UNIQUE NOT NULL,
    FOREIGN KEY (product_id) REFERENCES product_list(id) ON DELETE CASCADE
);

CREATE TABLE supplier
(
    id BIGINT PRIMARY KEY DEFAULT nextval('supplier_seq'),
    product_id      BIGINT         NOT NULL,
    supplier_name   VARCHAR (50)   UNIQUE NOT NULL ,
    city            VARCHAR(100),
    phone_number    VARCHAR(32),
    FOREIGN KEY (product_id) REFERENCES product_list(id) ON DELETE CASCADE
);