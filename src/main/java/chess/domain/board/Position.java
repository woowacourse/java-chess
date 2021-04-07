package chess.domain.board;

import chess.domain.piece.Direction;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
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

    public static List<Position> getPositions() {
        return Collections.unmodifiableList(CACHE);
    }

    public static Position convertStringToPosition(String input) {
        return Position.of(Horizontal.find(input.substring(0, 1)),
                Vertical.find(input.substring(1, 2)));
    }

    public boolean isSameVertical(Vertical vertical) {
        return this.vertical == vertical;
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

    public boolean isDeadLine() {
        Vertical vertical = getVertical();
        return vertical == Vertical.ONE || vertical == Vertical.EIGHT;
    }

    public String convertToString() {
        return horizontal.getValue() + vertical.getValue();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return horizontal == position.horizontal && vertical == position.vertical;
    }

    @Override
    public int hashCode() {
        return Objects.hash(horizontal, vertical);
    }
}