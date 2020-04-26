package chess.domain.piece.policy.move;

import chess.domain.piece.Piece;
import chess.domain.piece.PiecesState;
import chess.domain.piece.position.Direction;
import chess.domain.piece.position.Distance;
import chess.domain.piece.position.Position;

public class HasHindranceDiagonallyInBetween implements CanNotMoveStrategy {

    @Override
    public boolean canNotMove(Position from, Position to, PiecesState piecesState) {
        if (from.isNotStraightDiagonalDirection(to)) {
            return true;
        }

        return false;
    }

    private boolean hasHindranceDiagonallyInBetween(Position from, Position to, PiecesState piecesState) {
        Distance amount = from.calculateHorizontalDistance(to);
        Direction direction = from.calculateDirection(to);
        return HasHindranceInBetween.check(piecesState, amount, direction, from);
    }
}
