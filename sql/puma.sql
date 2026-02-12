CREATE TABLE puma
(
    id        BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name      VARCHAR(100)  NOT NULL,
    age       INT           NOT NULL,
    weight    DECIMAL(5, 2) NOT NULL,
    dangerous BOOLEAN       NOT NULL
);
