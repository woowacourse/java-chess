package chess.domain.piece;

import chess.controller.direction.Direction;
import chess.domain.board.position.Position;

import java.util.Arrays;
import java.util.List;

public class Knight extends Piece{

    private static final List<Direction> DIRECTIONS = Arrays.asList(
            Direction.KNIGHT_DOWN_LEFT,
            Direction.KNIGHT_DOWN_RIGHT,
            Direction.KNIGHT_LEFT_DOWN,
            Direction.KNIGHT_LEFT_UP,
            Direction.KNIGHT_UP_LEFT,
            Direction.KNIGHT_UP_RIGHT,
            Direction.KNIGHT_RIGHT_DOWN,
            Direction.KNIGHT_RIGHT_UP
    );

    private static final int ABLE_DISTANCE_TO_MOVE = 1;
    private static final Knight BLACK_KNIGHT = new Knight(Owner.BLACK);
    private static final Knight WHITE_KNIGHT = new Knight(Owner.WHITE);

    public static Knight getInstanceOf(Owner owner) {
        if (owner.equals(Owner.BLACK)) {
            return BLACK_KNIGHT;
        }

        if (owner.equals(Owner.WHITE)) {
            return WHITE_KNIGHT;
        }

        throw new IllegalArgumentException("Invalid Knight");
    }

    public Knight(Owner owner) {
        super(owner);
    }

    @Override
    public boolean validateMove(Position source, Position target, Piece targetPiece) {
        return true;
    }

    @Override
    public Score score() {
        return null;
    }

    @Override
    public String getSymbol() {
        return "N";
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
