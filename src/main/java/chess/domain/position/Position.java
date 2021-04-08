package chess.domain.position;

import java.util.*;
import java.util.stream.Collectors;

public class Position {
    public static final int CHANGE_LINE_POINT = 8;
    public static final Map<String, Position> CACHE;
    public static final List<Character> Xs = Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h');
    private static final List<Character> Ys = Arrays.asList('8', '7', '6', '5', '4', '3', '2', '1');

    static {
        CACHE = Ys.stream().flatMap(y -> Xs.stream()
                .map(x -> x + String.valueOf(y)))
                .collect(Collectors.toMap(
                        xy -> xy,
                        Position::new,
                        (xy1, xy2) -> xy1,
                        LinkedHashMap::new));
    }

    private final char x;
    private final char y;

    private Position(String xy) {
        this(xy.charAt(0), xy.charAt(1));
    }

    private Position(char x, char y) {
        this.x = x;
        this.y = y;
    }

    public static Position of(String xy) {
        validatePosition(xy);
        return CACHE.get(xy);
    }

    private static void validatePosition(String xy) {
        if (!(Xs.contains(xy.charAt(0)) && Ys.contains(xy.charAt(1)))) {
            throw new IllegalArgumentException("[ERROR] 올바른 체스판 범위가 아닙니다.");
        }
    }

    public char getX() {
        return x;
    }

    public char getY() {
        return y;
    }

    public String key() {
        return x + String.valueOf(y);
    }

    public int yDistance(Position anotherPosition) {
        return this.y - anotherPosition.y;
    }

    public int xDistance(Position anotherPosition) {
        return this.x - anotherPosition.x;
    }

    public boolean sameX(char anotherX) {
        return x == anotherX;
    }

    public boolean sameY(char anotherY) {
        return y == anotherY;
    }

    public boolean largeY(Position anotherPosition) {
        return this.y > anotherPosition.y;
    }

    public boolean largeX(Position anotherPosition) {
        return this.x > anotherPosition.x;
    }

    public boolean isDiagonal(Position target) {
        return Math.abs(xDistance(target)) == Math.abs(yDistance(target));
    }

    public boolean isCross(Position target) {
        return (this.x == target.x) || (this.y == target.y);
    }

    public Position movedByNumber(int xNumber, int yNumber) {
        return Position.of((char) (x + xNumber) + String.valueOf((char) (y + yNumber)));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return x == position.x &&
                y == position.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
