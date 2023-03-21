package chess.position;

import java.util.List;
import java.util.Objects;

public class Position {

    private static final String POSITION_REGEX = "[a-h][1-8]";
    private static final String INCORRECT_MOVE_POSITION_ERROR = "[ERROR] 이동 방향이 체스판 형식에 맞지 않습니다.";
    private final List<Integer> position;

    private Position(List<Integer> position) {
        this.position = position;
    }

    public static Position of(String command) {
        validatePositionRegex(command);
        return PositionCache.findPosition(command);
    }

    public static Position initPosition(Integer x, Integer y) {
        return new Position(List.of(x, y));
    }

    public static void validatePositionRegex(String command) {
        if (!command.matches(POSITION_REGEX)) {
            throw new IllegalArgumentException(INCORRECT_MOVE_POSITION_ERROR);
        }
    }

    public List<Integer> getPosition() {
        return position;
    }

    public int getXPosition() {
        return position.get(0);
    }

    public int getYPosition() {
        return position.get(1);
    }

    @Override
    public String toString() {
        return "Position{" +
                "position=" + position +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position1 = (Position) o;
        return Objects.equals(position, position1.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position);
    }
}
