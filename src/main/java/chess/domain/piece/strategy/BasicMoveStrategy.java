package chess.domain.piece.strategy;

import chess.domain.board.Position;

public abstract class BasicMoveStrategy implements MoveStrategy {

    @Override
    public void move(Position source, Position target) {
        checkValidMove(source, target);
    }

    abstract void checkValidMove(Position source, Position target);
}
