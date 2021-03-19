package chess.domain.piece.movingstrategy;

import chess.domain.board.Point;
import chess.domain.board.Row;
import chess.domain.piece.MoveVector;
import java.util.List;

public class PawnMovingStrategy implements MovingStrategy {

    private static final int LENGTH = 1;

    private final List<MoveVector> pawnsMoveVector = MoveVector.pawnVectors();

    @Override
    public MoveVector findMovableVector(Point source, Point destination) {
        int x = destination.minusX(source);
        int y = destination.minusY(source);

        MoveVector possibleMoveVector = findPawnsVectorByDifference(x, y);

        if (MoveVector.FIRST_PAWN_UP == possibleMoveVector && !source.isRow(Row.TWO)) {
            return null;
        }
        return possibleMoveVector;
    }

    private MoveVector findPawnsVectorByDifference(int x, int y) {
        MoveVector moveVector = MoveVector.get(x, y);

        return pawnsMoveVector.stream()
            .filter(vector -> vector.equals(moveVector))
            .findFirst()
            .orElse(null);
    }

    @Override
    public int getMoveLength() {
        return LENGTH;
    }
}
