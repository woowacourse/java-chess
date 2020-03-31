package chess.validator;

import chess.Board;
import chess.position.Position;

import java.util.List;

public class BishopMoveValidator extends MoveValidator {
    @Override
    protected boolean isNotPermittedMovement(Board board, Position source, Position target) {
        return source.isNotDiagonal(target);
    }

    @Override
    protected List<Position> movePathExceptSourceAndTarget(Position source, Position target) {
        return Position.findDiagonalTrace(source, target);
    }

    @Override
    protected boolean isKingKilledIfMoves(Board board, Position source, Position target) {
        return board.isKingKilledIfMoves(source, target);
    }
}
