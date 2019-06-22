package chess.domain.direction.core;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Square {
    private static Map<String, Square> squareMap = new HashMap<>();
    private int x;
    private int y;

    private Square(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public static Square of(int x, int y) {
        String key = String.format("(%d,%d)", x, y);
        if (!squareMap.containsKey(key)) {
            squareMap.put(key, new Square(x, y));
        }
        return squareMap.get(key);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Square square = (Square) o;
        return x == square.x &&
                y == square.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    public Square multiply(int scalar) {
        return Square.of(x * scalar, y * scalar);
    }

    public Square add(Square square) {
        return Square.of(x + square.x, y + square.y);
    }
}