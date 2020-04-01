package chess.validator;

import chess.Board;
import chess.position.Position;

import java.util.List;

public abstract class MoveValidator {
    public boolean isMovable(Board board, Position source, Position target) {
        try {
            throwExceptionIfNotMovable(board, source, target);
        } catch (IllegalArgumentException e) {
            return false;
        }
        return true;
    }

    public void throwExceptionIfNotMovable(Board board, Position source, Position target) {
        throwExceptionIfNotMovableWithoutConsideringKingCouldBeKilledNextTurn(board, source, target);
        if (isKingKilledIfMoves(board, source, target)) {
            throw new IllegalArgumentException("왕을 방어하세요.");
        }
    }

    public void throwExceptionIfNotMovableWithoutConsideringKingCouldBeKilledNextTurn(Board board, Position source, Position target) {
        if (board.isNotTurnOf(source)) {
            throw new IllegalArgumentException("해당 말의 차례가 아닙니다.");
        }
        if (isNotPermittedMovement(board, source, target)) {
            throw new IllegalArgumentException("해당 말의 규칙 상 이동할 수 없는 목적지입니다.");
        }
        List<Position> PositionsWherePiecesShouldNeverBeIncluded = movePathExceptSourceAndTarget(source, target);
        if (board.isExistAnyPieceAt(PositionsWherePiecesShouldNeverBeIncluded)) {
            throw new IllegalArgumentException("진로가 막혀있어 해당 위치로 이동할 수 없습니다.");
        }
        if (board.isExistAt(target) && board.isSameTeamBetween(source, target)) {
            throw new IllegalArgumentException("본인의 말은 잡을 수 없습니다.");
        }
    }

    protected abstract boolean isNotPermittedMovement(Board board, Position source, Position target);

    protected abstract List<Position> movePathExceptSourceAndTarget(Position source, Position target);

    protected abstract boolean isKingKilledIfMoves(Board board, Position source, Position target);
}
