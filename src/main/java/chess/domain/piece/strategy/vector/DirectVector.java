package chess.domain.piece.strategy.vector;

import chess.domain.piece.Color;
import java.util.List;

public enum DirectVector {

    NORTH(0, 1),
    SOUTH(0, -1),
    WEST(-1, 0),
    EAST(1, 0),
    TWO_NORTH(0, 2),
    TWO_SOUTH(0, -2),
    NORTH_WEST(-1, 1),
    NORTH_EAST(1, 1),
    SOUTH_WEST(-1, -1),
    SOUTH_EAST(1, -1),
    TWO_NORTH_ONE_WEST(-1, 2),
    TWO_NORTH_ONE_EAST(1, 2),
    TWO_EAST_ONE_NORTH(2, 1),
    TWO_EAST_ONE_SOUTH(2, -1),
    TWO_SOUTH_ONE_EAST(1, -2),
    TWO_SOUTH_ONE_WEST(-1, -2),
    TWO_WEST_ONE_NORTH(-2, 1),
    TWO_WEST_ONE_SOUTH(-2, -1),
    ;

    private final int unitFile;
    private final int unitRank;

    DirectVector(final int unitFile, final int unitRank) {
        this.unitFile = unitFile;
        this.unitRank = unitRank;
    }

    public static List<DirectVector> ofPawnByColor(final Color color) {
        if (color.isBlack()) {
            return List.of(SOUTH, TWO_SOUTH, SOUTH_WEST, SOUTH_EAST);
        }
        return List.of(NORTH, TWO_NORTH, NORTH_WEST, NORTH_EAST);
    }

    public static List<DirectVector> ofKing() {
        return List.of(NORTH, SOUTH, WEST, EAST, NORTH_EAST, NORTH_WEST, SOUTH_EAST, SOUTH_WEST);
    }

    public static List<DirectVector> ofKnight() {
        return List.of(
                TWO_NORTH_ONE_WEST, TWO_NORTH_ONE_EAST, TWO_EAST_ONE_NORTH, TWO_EAST_ONE_SOUTH,
                TWO_SOUTH_ONE_EAST, TWO_SOUTH_ONE_WEST, TWO_WEST_ONE_NORTH, TWO_WEST_ONE_SOUTH
        );
    }

    public boolean isMovable(final int distanceFile, final int distanceRank) {
        return unitFile == distanceFile && unitRank == distanceRank;
    }
}
