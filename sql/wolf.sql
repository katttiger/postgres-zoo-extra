CREATE TABLE wolf
(
    id        BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    age       INT,
    name      VARCHAR(255),
    fur_color VARCHAR(255),
    howl_key  VARCHAR(255)
)