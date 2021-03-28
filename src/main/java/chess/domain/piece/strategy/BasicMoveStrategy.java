package chess.domain.piece.strategy;

import chess.domain.board.Position;

public abstract class BasicMoveStrategy implements MoveStrategy {

    @Override
    public void move(final Position source, final Position target) {
        checkValidMove(source, target);
    }

    abstract void checkValidMove(final Position source, final Position target);
}
