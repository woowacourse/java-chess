package chess.domain.piece.moving;

import chess.domain.position.Position;
import chess.domain.position.Row;

import java.util.List;

public class BlackPawnMovingPattern extends PawnMovingPattern {

    private static final Row BLACK_FIRST_ROW = Row.RANK_7;

    private final List<Movement> movements;

    public BlackPawnMovingPattern(List<Movement> movements) {
        this.movements = movements;
    }

    @Override
    protected boolean isPossibleMoving(Movement movement) {
        return movements.contains(movement);
    }

    @Override
    protected boolean isCorrectTwoStepMovement(Position source) {
        return source.isSameRow(BLACK_FIRST_ROW);
    }
}
