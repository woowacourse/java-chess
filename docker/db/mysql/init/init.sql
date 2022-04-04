CREATE TABLE board
(
    id VARCHAR(36) NOT NULL,
    PRIMARY KEY (id)
);

INSERT INTO board
VALUES ('b2c2256e-c30c-4f9d-8603-f439e47c4f03');
-- TODO: 제거 예정

CREATE TABLE piece
(
    board_id    VARCHAR(36) NOT NULL,
    x_axis      ENUM('1', '2', '3', '4', '5', '6', '7', '8'),
    y_axis      ENUM('1', '2', '3', '4', '5', '6', '7', '8'),
    piece_type  ENUM('PAWN', 'ROOK', 'KNIGHT', 'BISHOP', 'QUEEN', 'KING'),
    piece_color ENUM('WHITE', 'BLACK'),
    PRIMARY KEY (x_axis, y_axis, piece_type, piece_color),
    FOREIGN KEY (board_id) REFERENCES board (id)
);
