package chess.domain.piece.policy.move;

import chess.domain.piece.CanNotMoveStrategy;
import chess.domain.piece.state.Pieces;
import chess.domain.position.Position;

public class IsNotHeadingStraightDirection implements CanNotMoveStrategy {
    @Override
    public boolean canNotMove(Position from, Position to, Pieces pieces) {
        return from.isNotStraightDirection(to);
    }
}
