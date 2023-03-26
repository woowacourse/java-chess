package domain.piece.pawn;

import domain.piece.move.Direction;
import domain.piece.move.Inclination;
import domain.piece.Color;

import java.util.List;

public final class BlackPawn extends Pawn {

    private static final int START_RANK = 6;

    private static final Direction DIRECTION = new Direction(List.of(
            Inclination.NEGATIVE_INFINITY
    ));
    private static final Direction ATTACK_DIRECTION = new Direction(List.of(
            Inclination.NEGATIVE_INFINITY, Inclination.ONE, Inclination.MINUS_ONE
    ));

    public BlackPawn(final Color color) {
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
