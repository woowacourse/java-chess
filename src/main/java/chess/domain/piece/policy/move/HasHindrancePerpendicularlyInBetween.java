package chess.domain.piece.policy.move;

import chess.domain.piece.PiecesState;
import chess.domain.piece.position.Direction;
import chess.domain.piece.position.Distance;
import chess.domain.piece.position.Position;

public class HasHindrancePerpendicularlyInBetween implements CanNotMoveStrategy {
    @Override
    public boolean canNotMove(Position from, Position to, PiecesState piecesState) {
        //todo: refac
        if (from.isDiagonalDirection(to)) {
            return false;
        }

        Distance amount = from.calculateDistance(to);
        Direction direction = from.calculateDirection(to);
        return piecesState.hasHindranceInBetween(amount, direction, from);
    }
}
