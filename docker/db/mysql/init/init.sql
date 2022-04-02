USE chess;

CREATE TABLE ChessBoard
(
    Position   VARCHAR(10) NOT NULL,
    ChessPiece VARCHAR(10) NOT NULL,
    Color      VARCHAR(10) NOT NULL,
    PRIMARY KEY (Position)
);
