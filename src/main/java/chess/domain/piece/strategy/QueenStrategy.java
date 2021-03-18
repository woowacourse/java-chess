package chess.domain.piece.strategy;

import chess.domain.Board;
import chess.domain.piece.Piece;
import java.util.List;

public class QueenStrategy implements MoveStrategy {

    @Override
    public boolean isMovable(Piece piece, Board board) {
        return false;
    }

    @Override
    public List<Direction> getDirections() {
        return Direction.everyDirection();
    }
}
