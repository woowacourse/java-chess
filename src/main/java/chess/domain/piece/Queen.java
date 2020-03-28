package chess.domain.piece;

import chess.domain.position.Position;
import chess.domain.util.Direction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Queen extends MultipleStep {
    private static final List<Direction> DIRECTIONS = new ArrayList<>(
            Arrays.asList(
                    Direction.NORTH,
                    Direction.NE,
                    Direction.EAST,
                    Direction.SE,
                    Direction.SOUTH,
                    Direction.SW,
                    Direction.WEST,
                    Direction.NW
            )
    );

    public Queen(char representation, Team team, Position position) {
        super(representation, team, position);
    }

    @Override
    protected List<Direction> getDirections() {
        return DIRECTIONS;
    }

}
