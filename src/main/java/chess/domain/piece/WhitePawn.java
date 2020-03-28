package chess.domain.piece;

import chess.domain.position.Position;
import chess.domain.util.Direction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WhitePawn extends Pawn {
    private static final List<Direction> DIRECTIONS = new ArrayList<>(
            Arrays.asList(
                    Direction.NORTH,
                    Direction.NE,
                    Direction.NW
            )
    );

    public WhitePawn(char representation, Team team, Position position) {
        super(representation, team, position);
    }

    @Override
    protected List<Direction> getDirections() {
        return DIRECTIONS;
    }
}
