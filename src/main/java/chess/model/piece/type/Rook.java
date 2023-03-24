package chess.model.piece.type;

import chess.model.piece.Camp;
import chess.model.piece.Direction;
import chess.model.piece.Piece;
import chess.model.position.Distance;
import java.util.List;

public class Rook extends Piece {

    private static final List<Direction> availableDirections = Direction.ORTHOGONAL;

    Rook(final Camp camp) {
        super(camp);
    }

    @Override
    public Piece pick() {
        return this;
    }

    @Override
    public boolean movable(final Distance distance, final Piece target) {
        return isAttackAble(target) && isAvailableDirection(distance);
    }

    private boolean isAttackAble(final Piece target) {
        return !target.isSameTeam(camp());
    }

    private boolean isAvailableDirection(final Distance distance) {
        return availableDirections.stream()
                .anyMatch(distance::matchByDirection);
    }

    @Override
    public boolean isNotPassable() {
        return true;
    }

    @Override
    public boolean isKing() {
        return false;
    }

    @Override
    public boolean isPawn() {
        return false;
    }
}
