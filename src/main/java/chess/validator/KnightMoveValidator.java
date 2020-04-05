package chess.validator;

import chess.Board;
import chess.position.Position;

import java.util.Collections;
import java.util.List;

public class KnightMoveValidator extends MoveValidator {
    private static final int MULTIPLICATION_OF_BETWEEN_FILE_DISTANCE_AND_RANK_DISTANCE = 2;

    @Override
    protected boolean isNotPermittedMovement(Board board, Position source, Position target) {
        return source.multiplicationOfDifferenceBetweenFileAndRank(target) != MULTIPLICATION_OF_BETWEEN_FILE_DISTANCE_AND_RANK_DISTANCE;
    }

    @Override
    protected List<Position> movePathExceptSourceAndTarget(Position source, Position target) {
        return Collections.emptyList();
    }

    @Override
    protected boolean isKingKilledIfMoves(Board board, Position source, Position target) {
        return board.isKingKilledIfMoves(source, target);
    }
}
