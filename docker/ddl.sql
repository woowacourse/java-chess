CREATE TABLE chess_game
(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    name       TEXT                   NOT NULL,
    is_end     BOOLEAN  DEFAULT false NULL,
    created_at DATETIME DEFAULT NOW() NOT NULL
);

CREATE TABLE movement
(
    id              BIGINT AUTO_INCREMENT PRIMARY KEY,
    game_id         BIGINT                 NOT NULL,
    source_position VARCHAR(20)            NOT NULL,
    target_position VARCHAR(20)            NOT NULL,
    turn            ENUM ('BLACK','WHITE') NOT NULL,
    created_at      DATETIME DEFAULT NOW() NOT NULL,
    CONSTRAINT move_command_chess_game_id_fk
        FOREIGN KEY (game_id) REFERENCES chess_game (id)
            ON DELETE CASCADE
);
