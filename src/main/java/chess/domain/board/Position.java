package chess.domain.board;

import chess.domain.piece.Direction;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Position {
    private static final List<Position> CACHE;

    static {
        CACHE = Arrays.stream(Vertical.values())
                .flatMap(vertical -> Arrays.stream(Horizontal.values())
                        .map(horizontal -> new Position(horizontal, vertical)))
                .collect(Collectors.toList());
    }

    private final Horizontal horizontal;
    private final Vertical vertical;

    private Position(Horizontal horizontal, Vertical vertical) {
        this.horizontal = horizontal;
        this.vertical = vertical;
    }

    public static Position of(Horizontal horizontal, Vertical vertical) {
        return CACHE.stream()
                .filter(position -> horizontal.equals(position.horizontal)
                        && vertical.equals(position.vertical))
                .findAny()
                .orElseThrow(RuntimeException::new);
    }

    public int movedHorizontalWeight(int value) {
        return this.horizontal.getWeight() + value;
    }

    public int movedVerticalWeight(int value) {
        return this.vertical.getWeight() + value;
    }

    public boolean isSameVertical(Vertical vertical) {
        return this.vertical == vertical;
    }

    public boolean isDifferentVertical(Vertical vertical) {
        return this.vertical != vertical;
    }

    public static List<Position> getPositions() {
        return Collections.unmodifiableList(CACHE);
    }

    public int getHorizontalWeight() {
        return horizontal.getWeight();
    }

    public int getVerticalWeight() {
        return vertical.getWeight();
    }

    public Horizontal getHorizontal() {
        return horizontal;
    }

    public Vertical getVertical() {
        return vertical;
    }

    public Direction directionToDestination(Position destination) {
        int horizontalWeight = destination.getHorizontalWeight() - getHorizontalWeight();
        int verticalWeight = destination.getVerticalWeight() - getVerticalWeight();

        return Direction.getDirectionFromWeight(horizontalWeight, verticalWeight);
    }

    public Position moveTowardDirection(Direction direction) {
        return Position.of(Horizontal.findFromWeight(getHorizontalWeight() + direction.getX()),
                Vertical.findFromWeight(getVerticalWeight() + direction.getY()));
    }

    public String convertToString() {
        return horizontal.getValue() + vertical.getValue();
    }
}