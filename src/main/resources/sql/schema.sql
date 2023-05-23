CREATE SCHEMA IF NOT EXISTS clevertec_shop;

DROP TABLE IF EXISTS clevertec_shop.product;
DROP TABLE IF EXISTS clevertec_shop.discount_card;

CREATE TABLE IF NOT EXISTS clevertec_shop.product
(
    id             SERIAL PRIMARY KEY,
    name           VARCHAR(50)      NOT NULL,
    price          DOUBLE PRECISION NOT NULL,
    is_promotional BOOLEAN          NOT NULL
);

CREATE TABLE IF NOT EXISTS clevertec_shop.discount_card
(
    id       SERIAL PRIMARY KEY,
    discount DOUBLE PRECISION CHECK (discount >= 0 AND discount <= 1) NOT NULL
);