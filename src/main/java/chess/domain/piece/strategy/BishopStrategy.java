package chess.domain.piece.strategy;

import chess.domain.Board;
import chess.domain.piece.Piece;

public class BishopStrategy implements MoveStrategy {

    @Override
    public boolean isMovable(Piece piece, Board board) {
        return false;
    }
}
