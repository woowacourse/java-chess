CREATE TABLE game
(
    id   VARCHAR(36) NOT NULL,
    turn ENUM('WHITE', 'BLACK'),
    PRIMARY KEY (id)
);

INSERT INTO game
VALUES ('b2c2256e-c30c-4f9d-8603-f439e47c4f03', null);
-- TODO: 제거 예정

CREATE TABLE board
(
    game_id     VARCHAR(36) NOT NULL,
    x_axis      ENUM('1', '2', '3', '4', '5', '6', '7', '8'),
    y_axis      ENUM('1', '2', '3', '4', '5', '6', '7', '8'),
    piece_type  ENUM('PAWN', 'ROOK', 'KNIGHT', 'BISHOP', 'QUEEN', 'KING'),
    piece_color ENUM('WHITE', 'BLACK'),
    PRIMARY KEY (x_axis, y_axis),
    FOREIGN KEY (game_id) REFERENCES game (id) ON DELETE CASCADE
);
