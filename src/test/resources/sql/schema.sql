DROP TABLE IF EXISTS product;
DROP TABLE IF EXISTS discount_card;

CREATE SCHEMA clevertec_shop;

CREATE TABLE clevertec_shop.product
(
    id             SERIAL PRIMARY KEY,
    name           VARCHAR(50),
    price          DOUBLE PRECISION,
    is_promotional BOOLEAN
);

CREATE TABLE clevertec_shop.discount_card
(
    id       SERIAL PRIMARY KEY,
    discount DOUBLE PRECISION CHECK (discount >= 0 AND discount <= 1)
);