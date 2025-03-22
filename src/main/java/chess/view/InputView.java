package chess.view;

import chess.domain.Column;
import chess.domain.Position;
import chess.domain.Row;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);

    public List<Position> getMoveInput() {
        System.out.println("움직일 말의 위치와 도착지를 입력하세요.(ex. a1 b2)");

        try {
            String positions = scanner.nextLine();
            Position origin = parseToPosition(String.valueOf(positions.charAt(0)), String.valueOf(positions.charAt(1)));
            Position destination = parseToPosition(String.valueOf(positions.charAt(3)), String.valueOf(positions.charAt(4)));
            return List.of(origin, destination);
        } catch (Exception e) {
            throw new IllegalArgumentException("올바르지 않은 위치 입력입니다.");
        }
    }

    private Position parseToPosition(String column, String row) {
        return new Position(parseToRow(row), parseToColumn(column));
    }

    private Column parseToColumn(String columnRaw) {
        if (columnRaw.equals("a")) {
            return Column.A;
        }
        if (columnRaw.equals("b")) {
            return Column.B;
        }
        if (columnRaw.equals("c")) {
            return Column.C;
        }
        if (columnRaw.equals("d")) {
            return Column.D;
        }
        if (columnRaw.equals("e")) {
            return Column.E;
        }
        if (columnRaw.equals("f")) {
            return Column.F;
        }
        if (columnRaw.equals("g")) {
            return Column.G;
        }
        if (columnRaw.equals("h")) {
            return Column.H;
        }
        throw new IllegalArgumentException("존재하지 않는 열입니다.");
    }

    private Row parseToRow(String rowRaw) {
        if (rowRaw.equals("1")) {
            return Row.ONE;
        }
        if (rowRaw.equals("2")) {
            return Row.TWO;
        }
        if (rowRaw.equals("3")) {
            return Row.THREE;
        }
        if (rowRaw.equals("4")) {
            return Row.FOUR;
        }
        if (rowRaw.equals("5")) {
            return Row.FIVE;
        }
        if (rowRaw.equals("6")) {
            return Row.SIX;
        }
        if (rowRaw.equals("7")) {
            return Row.SEVEN;
        }
        if (rowRaw.equals("8")) {
            return Row.EIGHT;
        }
        throw new IllegalArgumentException("존재하지 않는 행입니다.");
    }
}
