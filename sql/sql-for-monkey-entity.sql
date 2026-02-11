CREATE TABLE monkey
(
    id                BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    monkey_name       VARCHAR(255),
    type_of_monkey    VARCHAR(255),
    country_of_origin VARCHAR(255),
    current_habitat   VARCHAR(255)
);
