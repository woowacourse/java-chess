package domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public enum Position {
    A1(1, 1), A2(2, 1), A3(3, 1), A4(4, 1), A5(5, 1), A6(6, 1), A7(7, 1), A8(8, 1),
    B1(1, 2), B2(2, 2), B3(3, 2), B4(4, 2), B5(5, 2), B6(6, 2), B7(7, 2), B8(8, 2),
    C1(1, 3), C2(2, 3), C3(3, 3), C4(4, 3), C5(5, 3), C6(6, 3), C7(7, 3), C8(8, 3),
    D1(1, 4), D2(2, 4), D3(3, 4), D4(4, 4), D5(5, 4), D6(6, 4), D7(7, 4), D8(8, 4),
    E1(1, 5), E2(2, 5), E3(3, 5), E4(4, 5), E5(5, 5), E6(6, 5), E7(7, 5), E8(8, 5),
    F1(1, 6), F2(2, 6), F3(3, 6), F4(4, 6), F5(5, 6), F6(6, 6), F7(7, 6), F8(8, 6),
    G1(1, 7), G2(2, 7), G3(3, 7), G4(4, 7), G5(5, 7), G6(6, 7), G7(7, 7), G8(8, 7),
    H1(1, 8), H2(2, 8), H3(3, 8), H4(4, 8), H5(5, 8), H6(6, 8), H7(7, 8), H8(8, 8);

    private final int row;
    private final int column;

    Position(int row, int column) {
        this.row = row;
        this.column = column;
    }

    static Position getInstance(int row, int column) {
        return Arrays.stream(values())
                .filter(position -> position.row == row)
                .filter(position -> position.column == column)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 좌표입니다."));
    }

    public List<Position> route(Position targetPosition) {
        validateIsNotSelf(targetPosition);
        int absRowDistance = Math.abs(rowDistance(targetPosition));
        int absColDistance = Math.abs(columnDistance(targetPosition));
        if (isDiagonalRoute(absRowDistance, absColDistance)) {
            return getDiagonalRoute(targetPosition);
        }
        if (isColumnRoute(absRowDistance)) {
            return getColumnRoute(targetPosition);
        }
        if (isRowRoute(absColDistance)) {
            return getRowRoute(targetPosition);
        }
        throw new RuntimeException();
    }

    private boolean isDiagonalRoute(int absRowDistance, int absColDistance) {
        return absRowDistance == absColDistance;
    }

    private List<Position> getDiagonalRoute(Position targetPosition) {
        List<Integer> rows = calculateBetween(row, targetPosition.row);
        List<Integer> columns = calculateBetween(column, targetPosition.column);
        List<Position> result = new ArrayList<>();
        for (int i = 0; i < rows.size(); i++) {
            result.add(Position.getInstance(rows.get(i), columns.get(i)));
        }
        return Collections.unmodifiableList(result);
    }

    private List<Integer> calculateBetween(int from, int to) {
        int distance = to - from;
        int delta = distance / Math.abs(distance);
        return IntStream.range(1, Math.abs(distance))
                .mapToObj(value -> value * delta + from)
                .toList();
    }

    private boolean isColumnRoute(int absRowDistance) {
        return absRowDistance == 0;
    }

    private List<Position> getColumnRoute(Position targetPosition) {
        List<Integer> between = calculateBetween(column, targetPosition.column);
        List<Position> result = new ArrayList<>();
        for (Integer integer : between) {
            result.add(Position.getInstance(row, integer));
        }
        return Collections.unmodifiableList(result);
    }

    private boolean isRowRoute(int absColDistance) {
        return absColDistance == 0;
    }

    private List<Position> getRowRoute(Position targetPosition) {
        List<Integer> between = calculateBetween(row, targetPosition.row);
        List<Position> result = new ArrayList<>();
        for (Integer integer : between) {
            result.add(Position.getInstance(integer, column));
        }
        return Collections.unmodifiableList(result);
    }

    private void validateIsNotSelf(Position targetPosition) {
        if (this.equals(targetPosition)) {
            throw new IllegalArgumentException("출발지와 도착지가 같을 수 없습니다.");
        }
    }

    public int rowDistance(Position targetPosition) {
        return targetPosition.row - row;
    }

    public int columnDistance(Position targetPosition) {
        return targetPosition.column - column;
    }

    public boolean isOtherRow(Position targetPosition) {
        return targetPosition.row != row;
    }

    public boolean isSameColumn(Position targetPosition) {
        return targetPosition.column == column;
    }

    public boolean isOtherColumn(Position targetPosition) {
        return targetPosition.column != column;
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }
}
