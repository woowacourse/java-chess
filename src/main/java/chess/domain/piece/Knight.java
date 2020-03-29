package chess.domain.piece;

import chess.domain.position.Position;
import chess.domain.util.Direction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Knight extends SingleStep {
    private static final List<Direction> DIRECTIONS = new ArrayList<>(
            Arrays.asList(
                    Direction.NORTH_NORTH_EAST,
                    Direction.EAST_EAST_NORTH,
                    Direction.EAST_EAST_SOUTH,
                    Direction.SOUTH_SOUTH_EAST,
                    Direction.SOUTH_SOUTH_WEST,
                    Direction.WEST_WEST_SOUTH,
                    Direction.WEST_WEST_NORTH,
                    Direction.NORTH_NORTH_WEST
            )
    );

    public Knight(char representation, Team team, Position position) {
        super(representation, team, position);
    }

    @Override
    public Piece movePiecePosition(Position toPosition) {
        return new Knight(representation, team, toPosition);
    }

    @Override
    protected List<Direction> getDirections() {
        return DIRECTIONS;
    }

}
