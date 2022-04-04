CREATE DATABASE IF NOT EXISTS chess;

-- piece
CREATE TABLE piece (
   id INT NOT NULL AUTO_INCREMENT,
   symbol VARCHAR(1) NOT NULL,
   score DOUBLE NOT NULL,
   color VARCHAR(10) NOT NULL,
   image_url VARCHAR(100) NOT NULL,
   PRIMARY KEY (id));

INSERT INTO piece(symbol, score, color, image_url) VALUES('q', 9, 'BLACK', '/images/pieces/black/black-queen.svg');
INSERT INTO piece(symbol, score, color, image_url) VALUES('r', 5, 'BLACK', '/images/pieces/black/black-rook.svg');
INSERT INTO piece(symbol, score, color, image_url) VALUES('b', 3, 'BLACK', '/images/pieces/black/black-bishop.svg');
INSERT INTO piece(symbol, score, color, image_url) VALUES('n', 2.5, 'BLACK', '/images/pieces/black-knight.svg');
INSERT INTO piece(symbol, score, color, image_url) VALUES('p', 1, 'BLACK', '/images/pieces/black/black-pawn.svg');
INSERT INTO piece(symbol, score, color, image_url) VALUES('k', 0, 'BLACK', '/images/pieces/black/black-king.svg');

INSERT INTO piece(symbol, score, color, image_url) VALUES('q', 9, 'WHITE', '/images/pieces/white/white-queen.svg');
INSERT INTO piece(symbol, score, color, image_url) VALUES('r', 5, 'WHITE', '/images/pieces/white/white-rook.svg');
INSERT INTO piece(symbol, score, color, image_url) VALUES('b', 3, 'WHITE', '/images/pieces/white/white-bishop.svg');
INSERT INTO piece(symbol, score, color, image_url) VALUES('n', 2.5, 'WHITE', '/images/pieces/white/white-knight.svg');
INSERT INTO piece(symbol, score, color, image_url) VALUES('p', 1, 'WHITE', '/images/pieces/white/white-pawn.svg');
INSERT INTO piece(symbol, score, color, image_url) VALUES('k', 0, 'WHITE', '/images/pieces/white/white-king.svg');

INSERT INTO piece(symbol, score, color, image_url) VALUE('.', 0, 'NONE', '');

-- board
CREATE TABLE board(
    id INT NOT NULL AUTO_INCREMENT,
    game_status VARCHAR(15) NOT NULL,
    PRIMARY KEY (id));


-- pieces
CREATE TABLE pieces (
    id INT NOT NULL AUTO_INCREMENT,
    position VARCHAR(2),
    piece_id INT NOT NULL,
    board_id INT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (piece_id) REFERENCES piece(id),
    FOREIGN KEY (board_id) REFERENCES board(id)
);

-- user
CREATE TABLE user (
   id INT NOT NULL AUTO_INCREMENT,
   name VARCHAR(20) NOT NULL,
   board_id INT NOT NULL,
   PRIMARY KEY (id),
   INDEX name_idx (name ASC),
   UNIQUE INDEX name_UNIQUE (name ASC),
   FOREIGN KEY (board_id) REFERENCES board(id));








