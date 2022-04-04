CREATE DATABASE IF NOT EXISTS chess;

-- game status
CREATE TABLE game_status(
    id INT NOT NULL AUTO_INCREMENT,
    game_status VARCHAR(15) NOT NULL,
    PRIMARY KEY (id),
    INDEX game_status_index (game_status ASC));

INSERT INTO game_status(game_status) VALUES('READY');
INSERT INTO game_status(game_status) VALUES('WHITE_ROOM');
INSERT INTO game_status(game_status) VALUES('BLACK_ROOM');
INSERT INTO game_status(game_status) VALUES('END');

-- user
CREATE TABLE user (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(20) NOT NULL,
    game_status_id INT NOT NULL DEFAULT 1,
    PRIMARY KEY (id),
    INDEX name_idx (name ASC),
    UNIQUE INDEX name_UNIQUE (name ASC),
    FOREIGN KEY (game_status_id)
    REFERENCES game_status (id));

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
    piece_id INT NOT NULL,
    position VARCHAR(2) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE INDEX positino_UNIQUE (position ASC),
    FOREIGN KEY (piece_id)
    REFERENCES piece(id));


