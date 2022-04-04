SET @black_queen = (SELECT id
                    FROM piece
                    WHERE symbol = 'q'
                      AND color = 'BLACK');
SET @black_bishop = (SELECT id
                     FROM piece
                     WHERE symbol = 'b'
                       AND color = 'BLACK');
SET @black_knight = (SELECT id
                     FROM piece
                     WHERE symbol = 'n'
                       AND color = 'BLACK');
SET @black_rook = (SELECT id
                   FROM piece
                   WHERE symbol = 'r'
                     AND color = 'BLACK');
SET @black_king = (SELECT id
                   FROM piece
                   WHERE symbol = 'k'
                     AND color = 'BLACK');
SET @black_pawn = (SELECT id
                   FROM piece
                   WHERE symbol = 'p'
                     AND color = 'BLACK');

SET @white_queen = (SELECT id
                    FROM piece
                    WHERE symbol = 'q'
                      AND color = 'WHITE');
SET @white_bishop = (SELECT id
                     FROM piece
                     WHERE symbol = 'b'
                       AND color = 'WHITE');
SET @white_knight = (SELECT id
                     FROM piece
                     WHERE symbol = 'n'
                       AND color = 'WHITE');
SET @white_rook = (SELECT id
                   FROM piece
                   WHERE symbol = 'r'
                     AND color = 'WHITE');
SET @white_king = (SELECT id
                   FROM piece
                   WHERE symbol = 'k'
                     AND color = 'WHITE');
SET @white_pawn = (SELECT id
                   FROM piece
                   WHERE symbol = 'p'
                     AND color = 'WHITE');

INSERT INTO board(game_status)
VALUES ('READY');

SET @board_id = (SELECT id
                 FROM board
                 ORDER BY id DESC
                 LIMIT 1);

INSERT INTO pieces(piece_id, position, board_id)
VALUES (@white_rook, 'a1', @board_id);
INSERT INTO pieces(piece_id, position, board_id)
VALUES (@white_knight, 'b1', @board_id);
INSERT INTO pieces(piece_id, position, board_id)
VALUES (@white_bishop, 'c1', @board_id);
INSERT INTO pieces(piece_id, position, board_id)
VALUES (@white_queen, 'd1', @board_id);
INSERT INTO pieces(piece_id, position, board_id)
VALUES (@white_king, 'e1', @board_id);
INSERT INTO pieces(piece_id, position, board_id)
VALUES (@white_bishop, 'f1', @board_id);
INSERT INTO pieces(piece_id, position, board_id)
VALUES (@white_knight, 'g1', @board_id);
INSERT INTO pieces(piece_id, position, board_id)
VALUES (@white_rook, 'h1', @board_id);
INSERT INTO pieces(piece_id, position, board_id)
VALUES (@white_pawn, 'a2', @board_id);
INSERT INTO pieces(piece_id, position, board_id)
VALUES (@white_pawn, 'b2', @board_id);
INSERT INTO pieces(piece_id, position, board_id)
VALUES (@white_pawn, 'c2', @board_id);
INSERT INTO pieces(piece_id, position, board_id)
VALUES (@white_pawn, 'd2', @board_id);
INSERT INTO pieces(piece_id, position, board_id)
VALUES (@white_pawn, 'e2', @board_id);
INSERT INTO pieces(piece_id, position, board_id)
VALUES (@white_pawn, 'f2', @board_id);
INSERT INTO pieces(piece_id, position, board_id)
VALUES (@white_pawn, 'g2', @board_id);
INSERT INTO pieces(piece_id, position, board_id)
VALUES (@white_pawn, 'h2', @board_id);

INSERT INTO pieces(piece_id, position, board_id)
VALUES (@black_rook, 'a8', @board_id);
INSERT INTO pieces(piece_id, position, board_id)
VALUES (@black_knight, 'b8', @board_id);
INSERT INTO pieces(piece_id, position, board_id)
VALUES (@black_bishop, 'c8', @board_id);
INSERT INTO pieces(piece_id, position, board_id)
VALUES (@black_queen, 'd8', @board_id);
INSERT INTO pieces(piece_id, position, board_id)
VALUES (@black_king, 'e8', @board_id);
INSERT INTO pieces(piece_id, position, board_id)
VALUES (@black_bishop, 'f8', @board_id);
INSERT INTO pieces(piece_id, position, board_id)
VALUES (@black_knight, 'g8', @board_id);
INSERT INTO pieces(piece_id, position, board_id)
VALUES (@black_rook, 'h8', @board_id);
INSERT INTO pieces(piece_id, position, board_id)
VALUES (@black_pawn, 'a7', @board_id);
INSERT INTO pieces(piece_id, position, board_id)
VALUES (@black_pawn, 'b7', @board_id);
INSERT INTO pieces(piece_id, position, board_id)
VALUES (@black_pawn, 'c7', @board_id);
INSERT INTO pieces(piece_id, position, board_id)
VALUES (@black_pawn, 'd7', @board_id);
INSERT INTO pieces(piece_id, position, board_id)
VALUES (@black_pawn, 'e7', @board_id);
INSERT INTO pieces(piece_id, position, board_id)
VALUES (@black_pawn, 'f7', @board_id);
INSERT INTO pieces(piece_id, position, board_id)
VALUES (@black_pawn, 'g7', @board_id);
INSERT INTO pieces(piece_id, position, board_id)
VALUES (@black_pawn, 'h7', @board_id);
