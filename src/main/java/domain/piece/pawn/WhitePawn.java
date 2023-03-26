package domain.piece.pawn;

import domain.piece.Color;
import domain.piece.move.Direction;
import domain.piece.move.Inclination;

import java.util.List;

public final class WhitePawn extends Pawn {

    public static final int START_RANK = 1;

    private static final Direction DIRECTION = new Direction(List.of(
            Inclination.POSITIVE_INFINITY
    ));
    private static final Direction ATTACK_DIRECTION = new Direction(List.of(
            Inclination.POSITIVE_INFINITY, Inclination.ONE, Inclination.MINUS_ONE
    ));

    public WhitePawn(final Color color) {
        super(color);
    }

    @Override
    public Direction direction() {
        return DIRECTION;
    }

    @Override
    public Direction attackDirection() {
        return ATTACK_DIRECTION;
    }

    @Override
    public int startRank() {
        return START_RANK;
    }
}
