-- user
CREATE TABLE user
(
    user_id   BIGINT(20) NOT NULL AUTO_INCREMENT,
    user_name varchar(255) UNIQUE NOT NULL,
    PRIMARY KEY (user_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
