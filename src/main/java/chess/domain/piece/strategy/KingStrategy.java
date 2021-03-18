package chess.domain.piece.strategy;

import chess.domain.Board;
import chess.domain.piece.Piece;

public class KingStrategy implements MoveStrategy {

    public boolean isMovable(Piece piece, Board board) {
        return false;
    }
}
