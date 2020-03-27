package chess.domain.piece.pawn;

import chess.domain.piece.MoveStrategy;
import chess.domain.position.MovableAreaFactory;
import chess.domain.position.Position;
import chess.domain.position.Row;

import java.util.List;

public class PawnMoveStrategy implements MoveStrategy {
    @Override
    public boolean isNotMovableTo(Position start, Position destination) {
        List<Position> movable = MovableAreaFactory.pawnOf(start);
        if (isInitialPawnRow(start.getRow())) {
            movable.add(Position.of(start.getColumn(), Row.FOUR));
        }
        return !movable.contains(destination);
    }

    private boolean isInitialPawnRow(Row row) {
        return row.equals(Row.TWO);
    }
}
