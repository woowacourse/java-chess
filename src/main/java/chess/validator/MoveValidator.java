package chess.validator;

import chess.Board;
import chess.exception.*;
import chess.position.Position;

import java.util.List;

public abstract class MoveValidator {
    public void validate(Board board, Position source, Position target) {
        validateThisTurn(board, source, target);
        validateCheck(board, source, target);
    }

    public void validateThisTurn(Board board, Position source, Position target) {
        validateTurn(board, source);
        validateDestination(board, source, target);
        validatePath(board, source, target);
        validateTeamKill(board, source, target);
    }

    private void validateTurn(Board board, Position source) {
        if (board.isNotTurnOf(source)) {
            throw new InvalidTurnException(board.getTurn());
        }
    }

    private void validateDestination(Board board, Position source, Position target) {
        if (isNotPermittedMovement(board, source, target)) {
            throw new InvalidDestinationException();
        }
    }

    private void validatePath(Board board, Position source, Position target) {
        List<Position> movePath = movePathExceptSourceAndTarget(source, target);
        if (board.isExistAnyPieceAt(movePath)) {
            throw new BlockedMovePathException();
        }
    }

    private void validateTeamKill(Board board, Position source, Position target) {
        if (board.isExistAt(target) && board.isSameTeamBetween(source, target)) {
            throw new TeamKillException();
        }
    }

    private void validateCheck(Board board, Position source, Position target) {
        if (isKingKilledIfMoves(board, source, target)) {
            throw new CheckException();
        }
    }

    protected abstract boolean isNotPermittedMovement(Board board, Position source, Position target);

    protected abstract List<Position> movePathExceptSourceAndTarget(Position source, Position target);

    protected abstract boolean isKingKilledIfMoves(Board board, Position source, Position target);
}
