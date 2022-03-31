DELETE FROM product_type;
DELETE FROM supplier;
DELETE FROM product_list;

ALTER SEQUENCE product_type_seq RESTART WITH 1;
ALTER SEQUENCE supplier_seq RESTART WITH 1;
ALTER SEQUENCE product_list_seq RESTART WITH 1;