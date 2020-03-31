package chess.domain.piece.movable;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static chess.domain.piece.movable.Direction.*;

public enum Directions {
    LINEAR(Arrays.asList(NORTH, EAST, SOUTH, WEST)),
    DIAGONAL(Arrays.asList(NORTHEAST, SOUTHEAST, SOUTHWEST, NORTHWEST)),
    EVERY(Arrays.asList(NORTH, EAST, SOUTH, WEST, NORTHEAST, SOUTHEAST, SOUTHWEST, NORTHWEST)),
    KNIGHT(Arrays.asList(NNE, NNW, SSE, SSW, EEN, EES, WWN, WWS)),
    WHITE_PAWN(Arrays.asList(NORTH, NORTHEAST, NORTHWEST)),
    BLACK_PAWN(Arrays.asList(SOUTH, SOUTHEAST, SOUTHWEST)),
    NONE(Collections.emptyList());

    private List<Direction> directions;

    Directions(List<Direction> directions) {
        this.directions = directions;
    }

    public List<Direction> getDirections() {
        return directions;
    }
}
