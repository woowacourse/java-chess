package chess.domain.board;

import chess.domain.position.Position;

interface Movable {
    Board move(Position from, Position to, Board board);
}
