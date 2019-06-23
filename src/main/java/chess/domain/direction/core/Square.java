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

    int getX() {
        return x;
    }

    int getY() {
        return y;
    }

    public static Square of(int x, int y) {
        String key = String.format("(%d,%d)", x, y);
        if (!squareMap.containsKey(key)) {
            squareMap.put(key, new Square(x, y));
        }
        return squareMap.get(key);
    }

    Square multiply(int scalar) {
        return Square.of(x * scalar, y * scalar);
    }

    Square add(Square square) {
        return Square.of(x + square.x, y + square.y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Square)) return false;
        Square square = (Square) o;
        return getX() == square.getX() &&
                getY() == square.getY();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getX(), getY());
    }

    @Override
    public String toString() {
        return "Square{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}