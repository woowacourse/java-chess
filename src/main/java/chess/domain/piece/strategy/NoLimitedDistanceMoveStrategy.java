package chess.domain.piece.strategy;

import chess.domain.board.Board;
import chess.domain.board.Position;

public abstract class NoLimitedDistanceMoveStrategy extends NoJumpMoveStrategy {

    @Override
    protected void checkValidMove(Position source, Position target, Board board) {
        checkPositionsOnBoard(source, target);
        checkMoveType(source, target);
        MoveDirection moveDirection = MoveDirection.getDirection(source, target);
        checkIsNotSameTeam(source, target, board);
        checkClearPath(source, target, moveDirection, board);
    }

    protected abstract void checkMoveType(Position source, Position target);
}
