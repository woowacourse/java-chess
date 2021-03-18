package chess.domain.piece.strategy;

import chess.domain.Board;
import chess.domain.piece.Piece;
import chess.domain.position.Position;

public class KnightStrategy implements MoveStrategy {

    @Override
    public boolean isMovable(Piece piece, Board board) {
        return false;
    }
}
