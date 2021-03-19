package chess.domain.piece;

import chess.domain.direction.Direction;
import chess.domain.board.position.Position;

import java.util.Arrays;
import java.util.List;

public class King extends Piece {
    private static final List<Direction> DIRECTIONS = Arrays.asList(
            Direction.LEFT,
            Direction.DOWN,
            Direction.UP,
            Direction.RIGHT,
            Direction.DOWN_LEFT,
            Direction.DOWN_RIGHT,
            Direction.UP_LEFT,
            Direction.UP_RIGHT
    );

    private static final int ABLE_DISTANCE_TO_MOVE = 1;

    private static final King BLACK_KING = new King(Owner.BLACK);
    private static final King WHITE_KING = new King(Owner.WHITE);

    public King(Owner owner) {
        super(owner);
    }

    public static King getInstanceOf(Owner owner) {
        if (owner.equals(Owner.BLACK)) {
            return BLACK_KING;
        }

        if (owner.equals(Owner.WHITE)) {
            return WHITE_KING;
        }

        throw new IllegalArgumentException("Invalid King");
    }

    @Override
    public boolean validateMove(Position source, Position target, Piece targetPiece) {
        return true;
    }

    @Override
    public Score score() {
        return new Score(0);
    }

    @Override
    public String getSymbol() {
        return "K";
    }

    @Override
    public List<Direction> getDirections() {
        return DIRECTIONS;
    }

    @Override
    public int getMaxDistance() {
        return ABLE_DISTANCE_TO_MOVE;
    }
}