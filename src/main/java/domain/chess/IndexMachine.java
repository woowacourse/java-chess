package domain.chess;

import domain.chess.piece.Position;

import java.util.Arrays;

public enum IndexMachine {
    COLUMN_ZERO('a', 0),
    COLUMN_ONE('b', 1),
    COLUMN_TWO('c', 2),
    COLUMN_THREE('d', 3),
    COLUMN_FOUR('e', 4),
    COLUMN_FIVE('f', 5),
    COLUMN_SIX('g', 6),
    COLUMN_SEVEN('h', 7),
    ROW_ZERO('8', 0),
    ROW_ONE('7', 1),
    ROW_TWO('6', 2),
    ROW_THREE('5', 3),
    ROW_FOUR('4', 4),
    ROW_FIVE('3', 5),
    ROW_SIX('2', 6),
    ROW_SEVEN('1', 7);

    private char name;
    private int index;

    IndexMachine(char name, int index) {
        this.name = name;
        this.index = index;
    }

    public static Position convertPosition(String input) {
        char row = input.charAt(0);
        char column = input.charAt(1);
        int x = Arrays.stream(IndexMachine.values()).
                filter(value -> value.getName() == row)
                .findAny()
                .get()
                .getIndex();
        int y = Arrays.stream(IndexMachine.values()).
                filter(value -> value.getName() == column)
                .findAny()
                .get()
                .getIndex();
        return Position.Of(x, y);
    }

    private char getName() {
        return name;
    }

    private int getIndex() {
        return index;
    }
}
