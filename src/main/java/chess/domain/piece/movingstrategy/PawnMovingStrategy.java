package chess.domain.piece.movingstrategy;

import chess.domain.board.Point;
import chess.domain.board.Row;
import chess.domain.piece.MoveVector;
import java.util.List;

public class PawnMovingStrategy implements MovingStrategy {

    private static final int LENGTH = 1;

    private final List<MoveVector> pawnsMoveVector = MoveVector.pawnVectors();

    @Override
    public MoveVector movableVector(Point source, Point destination) {
        int x = destination.XDifference(source);
        int y = destination.YDifference(source);

        MoveVector possibleMoveVector = findPawnsVectorByDifference(x, y);

        if (MoveVector.FIRST_PAWN_UP == possibleMoveVector && !source.isRow(Row.TWO)) {
            return null;
        }
        return possibleMoveVector;
    }

    private MoveVector findPawnsVectorByDifference(int x, int y) {
        MoveVector moveVector = MoveVector.foundMoveVector(x, y);

        return pawnsMoveVector.stream()
            .filter(vector -> vector.equals(moveVector))
            .findFirst()
            .orElse(null);
    }

    @Override
    public int movingLength() {
        return LENGTH;
    }
}
