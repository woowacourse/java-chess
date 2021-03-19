package chess.domain.piece.strategy;

import chess.domain.board.Position;

public abstract class JumpMoveStrategy extends BasicMoveStrategy {

    @Override
    protected void checkValidMove(Position source, Position target) {
        checkPositionsOnBoard(source, target);
        checkValidTargetPosition(source, target);
    }

    protected abstract void checkValidTargetPosition(Position source, Position target);
}
