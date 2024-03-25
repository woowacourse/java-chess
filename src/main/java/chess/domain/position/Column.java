package chess.domain.position;

import chess.domain.chesspiece.Team;

import java.util.Arrays;

public enum Column {
    ONE(0),
    TWO(1),
    THREE(2),
    FOUR(3),
    FIVE(4),
    SIX(5),
    SEVEN(6),
    EIGHT(7),
    ;
    private final int index;

    Column(int index) {
        this.index = index;
    }

    public static Column from(String value) {
        validate(value);
        return find(Integer.parseInt(value) - 1);
    }

    private static void validate(String value) {
        try {
            int number = Integer.parseInt(value);
            validateInRange(number);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("1~8까지 가능합니다.");
        }
    }

    private static void validateInRange(int value) {
        if (value < 1 || value > 8) {
            throw new IllegalArgumentException("1~8까지 가능합니다.");
        }
    }

    private static Column find(int index) {
        return Arrays.stream(values())
                .filter(column -> column.index == index)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("1~8까지 가능합니다."));
    }

    public Column update(int direction) {
        return Arrays.stream(values())
                .filter(column -> column.index == this.index + direction)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("1~8까지 가능합니다."));
    }

    public boolean isPawnStartPosition(Team team) {
        if (team.isWhite()) {
            return index == 1;
        }
        return index == 6;
    }

    public int subtractColumn(Column column) {
        return this.index - column.index;
    }

    public int getIndex() {
        return index;
    }

    public int compare(Column column) {
        return Integer.compare(column.index, index);
    }
}
