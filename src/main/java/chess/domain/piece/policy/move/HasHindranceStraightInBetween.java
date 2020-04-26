package chess.domain.piece.policy.move;

import chess.domain.piece.PiecesState;
import chess.domain.piece.position.Direction;
import chess.domain.piece.position.Distance;
import chess.domain.piece.position.Position;

public class HasHindranceStraightInBetween implements CanNotMoveStrategy {
    @Override
    public boolean canNotMove(Position from, Position to, PiecesState piecesState) {
        if (from.isDiagonalDirection(to)) {
            return hasHindranceDiagonallyInBetween(from, to, piecesState);
        }

        return hasHindrancePerpendicularlyInBetween(from, to, piecesState);
    }

    private boolean hasHindrancePerpendicularlyInBetween(Position from, Position to, PiecesState piecesState) {
        Distance amount = from.calculateDistance(to);
        Direction direction = from.calculateDirection(to);
        return HasHindranceInBetween.check(piecesState, amount, direction, from);
    }

    private boolean hasHindranceDiagonallyInBetween(Position from, Position to, PiecesState piecesState) {
        Distance amount = from.calculateHorizontalDistance(to);
        Direction direction = from.calculateDirection(to);
        return HasHindranceInBetween.check(piecesState, amount, direction, from);
    }
}
