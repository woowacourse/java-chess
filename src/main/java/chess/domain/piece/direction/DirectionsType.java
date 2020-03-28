package chess.domain.piece.direction;

import java.util.Arrays;
import java.util.List;

import static chess.domain.piece.direction.Direction.*;

public enum DirectionsType {
    LINEAR(Arrays.asList(NORTH, EAST, SOUTH, WEST)),
    DIAGONAL(Arrays.asList(NORTHEAST, SOUTHEAST, SOUTHWEST, NORTHWEST)),
    EVERY(Arrays.asList(NORTH, EAST, SOUTH, WEST, NORTHEAST, SOUTHEAST, SOUTHWEST, NORTHWEST)),
    KNIGHT(Arrays.asList(NNE, NNW, SSE, SSW, EEN, EES, WWN, WWS)),
    WHITE_PAWN(Arrays.asList(NORTH, NORTHEAST, NORTHWEST)),
    BLACK_PAWN(Arrays.asList(SOUTH, SOUTHEAST, SOUTHWEST));

    private List<Direction> directions;

    DirectionsType(List<Direction> directions) {
        this.directions = directions;
    }

    public boolean contains(Direction direction) {
        return this.directions.contains(direction);
    }
}
