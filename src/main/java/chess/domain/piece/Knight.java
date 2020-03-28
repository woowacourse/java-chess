package chess.domain.piece;

import chess.domain.position.Position;
import chess.domain.util.Direction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Knight extends SingleStep {
    private static final List<Direction> DIRECTIONS = new ArrayList<>(
            Arrays.asList(
                    Direction.NNE,
                    Direction.EEN,
                    Direction.EES,
                    Direction.SSE,
                    Direction.SSW,
                    Direction.WWS,
                    Direction.WWN,
                    Direction.NNW
            )
    );

    public Knight(char representation, Team team, Position position) {
        super(representation, team, position);
    }

    @Override
    protected List<Direction> getDirections() {
        return DIRECTIONS;
    }

}
