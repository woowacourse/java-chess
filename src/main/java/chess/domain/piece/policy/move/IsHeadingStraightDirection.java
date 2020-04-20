package chess.domain.piece.policy.move;

import chess.domain.piece.PiecesState;
import chess.domain.piece.position.Position;

public class IsHeadingStraightDirection implements CanNotMoveStrategy {
    //포지션이 나음
    @Override
    public boolean canNotMove(Position from, Position to, PiecesState piecesState) {
        return from.isStraightDirection(to);
    }
}
