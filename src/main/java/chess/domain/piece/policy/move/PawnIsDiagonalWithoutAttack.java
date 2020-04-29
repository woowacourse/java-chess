package chess.domain.piece.policy.move;

import chess.domain.piece.PiecesState;
import chess.domain.piece.Square;
import chess.domain.piece.position.Position;

public class PawnIsDiagonalWithoutAttack implements CanNotMoveStrategy {
    @Override
    public boolean canNotMove(Position from, Position to, PiecesState piecesState) {
        return from.isDiagonalDirection(to) && (piecesState.isSameTeam(from, to)|| piecesState.isNotFilled(to));
    }
}
