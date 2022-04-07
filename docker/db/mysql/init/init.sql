CREATE TABLE game
(
    id   VARCHAR(36) NOT NULL,
    turn ENUM('WHITE', 'BLACK'),
    PRIMARY KEY (id)
);

CREATE TABLE board
(
    game_id     VARCHAR(36) NOT NULL,
    x_axis      ENUM('1', '2', '3', '4', '5', '6', '7', '8'),
    y_axis      ENUM('1', '2', '3', '4', '5', '6', '7', '8'),
    piece_type  ENUM('PAWN', 'ROOK', 'KNIGHT', 'BISHOP', 'QUEEN', 'KING'),
    piece_color ENUM('WHITE', 'BLACK'),
    PRIMARY KEY (game_id, x_axis, y_axis),
    FOREIGN KEY (game_id) REFERENCES game (id) ON DELETE CASCADE
);
