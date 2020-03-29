package chess.domain.piece;

import chess.domain.position.Position;
import chess.domain.util.Direction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Bishop extends MultipleStep {
    private static final List<Direction> DIRECTIONS = new ArrayList<>(
            Arrays.asList(
                    Direction.NORTH_EAST,
                    Direction.SOUTH_EAST,
                    Direction.SOUTH_WEST,
                    Direction.NORTH_WEST
            )
    );

    public Bishop(char representation, Team team, Position position) {
        super(representation, team, position, PieceType.BISHOP);
    }

    @Override
    public Piece movePiecePosition(Position toPosition) {
        return new Bishop(representation, team, toPosition);
    }

    @Override
    protected List<Direction> getDirections() {
        return DIRECTIONS;
    }
}
