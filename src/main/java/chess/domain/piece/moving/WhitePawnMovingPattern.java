package chess.domain.piece.moving;

import chess.domain.position.Position;
import chess.domain.position.Row;

import java.util.List;

public class WhitePawnMovingPattern extends PawnMovingPattern {

    private static final Row WHITE_FIRST_ROW = Row.RANK_2;

    private final List<Movement> movements;

    public WhitePawnMovingPattern(List<Movement> movements) {
        this.movements = movements;
    }

    @Override
    protected boolean isPossibleMoving(Movement movement) {
        return movements.contains(movement);
    }

    @Override
    protected boolean isCorrectTwoStepMovement(Position source) {
        return source.isSameRow(WHITE_FIRST_ROW);
    }
}
