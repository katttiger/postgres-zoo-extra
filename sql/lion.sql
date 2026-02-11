CREATE TABLE lion
(
    id     BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name   VARCHAR(30),
    age    INT,
    weight NUMERIC(5, 2),
    kills  INT
);