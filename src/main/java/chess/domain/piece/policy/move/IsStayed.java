package chess.domain.piece.policy.move;

import chess.domain.piece.PiecesState;
import chess.domain.piece.position.Position;

public class IsStayed implements CanNotMoveStrategy {

    //포지션 받는 게 나음
    @Override
    public boolean canNotMove(Position from, Position to, PiecesState piecesState) {
        return from.equals(to);
    }
}
