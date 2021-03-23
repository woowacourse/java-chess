package chess.domain.position;

import chess.domain.piece.Direction;
import java.util.Arrays;

public enum Row {
    EIGHT("8"),
    SEVEN("7"),
    SIX("6"),
    FIVE("5"),
    FOUR("4"),
    THREE("3"),
    TWO("2"),
    ONE("1");

    private static final String INVALID_ROW_ERROR_MESSAGE = "존재하지 않는 행입니다.";

    private final String number;

    Row(String number) {
        this.number = number;
    }

    public static Row getRow(String value) {
        return Arrays.stream(values())
            .filter(row -> row.number.equals(value))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException(INVALID_ROW_ERROR_MESSAGE));
    }

    public Row move(Direction direction) {
        if (isBoundary(direction)) {
            return this;
        }
        return getRow(String.valueOf(Integer.parseInt(number) + direction.getCoordinates().get(1)));
    }

    public String getNumber() {
        return number;
    }

    public boolean isBoundary(Direction direction) {
        if (Direction.downDirection().contains(direction)) {
            return this.equals(ONE);
        }
        if (Direction.upDirection().contains(direction)) {
            return this.equals(EIGHT);
        }
        if (Direction.R_DD.equals(direction) || Direction.L_DD.equals(direction)) {
            return this.equals(TWO) || this.equals(ONE);
        }
        if (Direction.R_UU.equals(direction) || Direction.L_UU.equals(direction)) {
            return this.equals(SEVEN) || this.equals(EIGHT);
        }
        return false;
    }

    public int getValue() {
        return Integer.parseInt(number);
    }
}
