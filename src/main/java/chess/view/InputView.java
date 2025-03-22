package chess.view;

import chess.Column;
import chess.Position;
import chess.Row;
import java.util.Scanner;
import java.util.function.Supplier;

public class InputView {

    private static final int ROW_INDEX = 0;
    private static final int COLUMN_INDEX = 1;

    public static Position readStartPosition() {
        System.out.println("시작 위치를 입력해주세요");
        return getPosition();
    }

    public static Position readEndPosition() {
        System.out.println("끝 위치를 입력해주세요");
        return getPosition();
    }

    private static Position getPosition() {
        final Scanner scanner = new Scanner(System.in);
        final String[] split = scanner.nextLine().split(",");
        final Row row = Row.parseToRowByNumber(parseToNumber(split[ROW_INDEX]));
        final Column column = Column.parseToColumnByNumber(parseToNumber(split[COLUMN_INDEX]));
        return new Position(row, column);
    }

    public static int parseToNumber(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("위치는 숫자만 가능 합니다.");
        }
    }
}
