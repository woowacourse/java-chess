SET sql_safe_updates=0;

CREATE TABLE if not exists movement (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    source VARCHAR(8) NOT NULL,
    target VARCHAR(8) NOT NULL
)
