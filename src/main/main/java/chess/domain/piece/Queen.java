package chess.domain.piece;

import chess.domain.piece.rule.Direction;
import chess.domain.board.position.Position;

import java.util.Arrays;
import java.util.List;

public class Queen extends Piece {
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

    private static final int ABLE_DISTANCE_TO_MOVE = 7;
    private static final Queen BLACK_QUEEN = new Queen(Owner.BLACK);
    private static final Queen WHITE_QUEEN = new Queen(Owner.WHITE);

    public Queen(Owner owner) {
        super(owner);
    }

    public static Queen getInstanceOf(Owner owner) {
        if (owner.equals(Owner.BLACK)) {
            return BLACK_QUEEN;
        }

        if (owner.equals(Owner.WHITE)) {
            return WHITE_QUEEN;
        }

        throw new IllegalArgumentException("Invalid Queen");
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
        return "Q";
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
