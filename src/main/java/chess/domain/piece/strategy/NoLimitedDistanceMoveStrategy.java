package chess.domain.piece.strategy;

import chess.domain.board.Position;

public abstract class NoLimitedDistanceMoveStrategy extends NoJumpMoveStrategy {

    @Override
    protected void checkValidMove(Position source, Position target) {
        checkPositionsOnBoard(source, target);
        checkMoveType(source, target);
    }

    protected abstract void checkMoveType(Position source, Position target);
}
