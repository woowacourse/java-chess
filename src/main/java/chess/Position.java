package chess;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Position {

    public static final String POSITION_REGEX = "[a-h][1-8]";
    private final List<Integer> position;

    private Position(List<Integer> position) {
        this.position = position;
    }

    public static Position of(String command) {
        validatePositionRegex(command);
        List<Integer> convertPosition = convertCommand(command);
        return new Position(convertPosition);
    }

    public static Position initPosition(Integer x, Integer y) {
        return new Position(List.of(x, y));
    }

    public static void validatePositionRegex(String command) {
        if (!command.matches(POSITION_REGEX) || !command.matches(POSITION_REGEX)) {
            throw new IllegalArgumentException("[ERROR] 이동 방향이 체스판 형식에 맞지 않습니다.");
        }
    }

    public static List<Integer> convertCommand(String command) {
        List<Integer> convertCommand = new ArrayList<>();
        convertCommand.add(command.charAt(0) - 96);
        convertCommand.add(Character.getNumericValue(command.charAt(1)));
        return convertCommand;
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
