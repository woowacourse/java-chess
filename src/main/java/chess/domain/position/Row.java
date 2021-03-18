package chess.domain.position;

import chess.domain.piece.strategy.Direction;
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

    private final String number;

    Row(String number) {
        this.number = number;
    }

    public static Row getRow(String value) {
        return Arrays.stream(values())
                .filter(row -> row.number.equals(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 행입니다."));
    }

    public Row move(Direction direction) {
        return getRow(String.valueOf(Integer.parseInt(number) + direction.getCoordinates().get(1)));
    }

    public int getValue(){
        return Integer.parseInt(number);
    }
}
