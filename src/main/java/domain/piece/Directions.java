package domain.piece;

import static domain.piece.Direction.BOTTOM;
import static domain.piece.Direction.BOTTOM_BOTTOM;
import static domain.piece.Direction.BOTTOM_BOTTOM_LEFT;
import static domain.piece.Direction.BOTTOM_BOTTOM_RIGHT;
import static domain.piece.Direction.BOTTOM_LEFT;
import static domain.piece.Direction.BOTTOM_RIGHT;
import static domain.piece.Direction.LEFT;
import static domain.piece.Direction.LEFT_LEFT_BOTTOM;
import static domain.piece.Direction.LEFT_LEFT_TOP;
import static domain.piece.Direction.RIGHT;
import static domain.piece.Direction.RIGHT_RIGHT_BOTTOM;
import static domain.piece.Direction.RIGHT_RIGHT_TOP;
import static domain.piece.Direction.TOP;
import static domain.piece.Direction.TOP_LEFT;
import static domain.piece.Direction.TOP_RIGHT;
import static domain.piece.Direction.TOP_TOP;
import static domain.piece.Direction.TOP_TOP_LEFT;
import static domain.piece.Direction.TOP_TOP_RIGHT;

import java.util.Set;

public enum Directions {

    Linear(Set.of(TOP, RIGHT, BOTTOM, LEFT)),
    Diagonal(Set.of(TOP_RIGHT, BOTTOM_RIGHT, BOTTOM_LEFT, TOP_LEFT)),
    Every(Set.of(TOP, RIGHT, BOTTOM, LEFT,TOP_RIGHT, BOTTOM_RIGHT, BOTTOM_LEFT, TOP_LEFT)),
    Knight(Set.of(TOP_TOP_RIGHT, TOP_TOP_LEFT, RIGHT_RIGHT_TOP, RIGHT_RIGHT_BOTTOM,
                BOTTOM_BOTTOM_RIGHT, BOTTOM_BOTTOM_LEFT, LEFT_LEFT_BOTTOM, LEFT_LEFT_TOP)),
    InitBlackPawn(Set.of(BOTTOM, BOTTOM_BOTTOM, BOTTOM_RIGHT, BOTTOM_LEFT)),
    InitWhitePawn(Set.of(TOP, TOP_TOP, TOP_RIGHT, TOP_LEFT)),
    BlackPawn(Set.of(BOTTOM, BOTTOM_RIGHT, BOTTOM_LEFT)),
    WhitePawn(Set.of(TOP, TOP_RIGHT, TOP_LEFT));

    private final Set<Direction> directions;

    Directions(Set<Direction> directions) {
        this.directions = directions;
    }

    public Direction findDirection(Direction other) {
        return directions.stream()
            .filter(direction -> direction.isSameDirection(other))
            .findAny()
            .orElseThrow(() -> new IllegalArgumentException("해당 방향으로 갈 수 없습니다."));
    }

    public void validateContains(Direction vector) {
        if (contains(vector)) {
            return;
        }
        throw new IllegalArgumentException("해당 목적지로 갈 수 없습니다.");
    }

    public boolean contains(Direction vector) {
        return directions.contains(vector);
    }
}
