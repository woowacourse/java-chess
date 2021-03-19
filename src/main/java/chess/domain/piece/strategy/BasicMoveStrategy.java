package chess.domain.piece.strategy;

import chess.domain.board.Position;
import chess.domain.exceptions.InvalidMoveException;
import chess.domain.piece.Piece;

public abstract class BasicMoveStrategy implements MoveStrategy {

    @Override
    public void move(Position source, Position target) {
        checkValidMove(source, target);
    }

    protected void checkPositionsOnBoard(Position source, Position target) {
        if (source == null || target == null) {
            throw new InvalidMoveException(Piece.OUT_OF_BOUND_MESSAGE);
        }
    }

    protected boolean isStay(Position source, Position target) {
        return source.computeHorizontalDistance(target) == 0 &&
            source.computeVerticalDistance(target) == 0;
    } //TODO 나중에 안움직이는지 확인

    abstract void checkValidMove(Position source, Position target);
}
