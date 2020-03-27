package chess.domain.direction;

import chess.domain.position.Position;
import chess.domain.position.Positions;
import chess.domain.position.component.Column;
import chess.domain.position.component.Row;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;

// 중복 코드 개선하기
public enum Direction {
    LEFT((rowDiff, columnDiff) -> isPositive(rowDiff) && isZero(columnDiff),
            (from, to) -> {
                Row smallerRow = Row.getSmaller(from.getRow(), to.getRow());
                Row biggerRow = Row.getBigger(from.getRow(), to.getRow());
                List<Row> rows = Arrays.asList(Row.values())
                        .subList(smallerRow.ordinal() + 1, biggerRow.ordinal());
                List<Position> positions = rows.stream()
                        .map(row -> Positions.of(row, from.getColumn()))
                        .collect(Collectors.toList());
                return positions;
            }
    ),
    RIGHT((rowDiff, columnDiff) -> isNegative(rowDiff) && isZero(columnDiff),
            (from, to) -> {
                Row smallerRow = Row.getSmaller(from.getRow(), to.getRow());
                Row biggerRow = Row.getBigger(from.getRow(), to.getRow());
                List<Row> rows = Arrays.asList(Row.values())
                        .subList(smallerRow.ordinal() + 1, biggerRow.ordinal());
                List<Position> positions = rows.stream()
                        .map(row -> Positions.of(row, from.getColumn()))
                        .collect(Collectors.toList());
                return positions;
            }
    ),
    TOP((rowDiff, columnDiff) -> isZero(rowDiff) && isNegative(columnDiff),
            (from, to) -> {
                Column smallerColumn = Column.getSmaller(from.getColumn(), to.getColumn());
                Column biggerColumn = Column.getBigger(from.getColumn(), to.getColumn());
                List<Column> columns = Arrays.asList(Column.values())
                        .subList(smallerColumn.ordinal() + 1, biggerColumn.ordinal());
                List<Position> positions = columns.stream()
                        .map(column -> Positions.of(from.getRow(), column))
                        .collect(Collectors.toList());
                return positions;
            }),
    DOWN((rowDiff, columnDiff) -> isZero(rowDiff) && isPositive(columnDiff),
            (from, to) -> {
                Column smallerColumn = Column.getSmaller(from.getColumn(), to.getColumn());
                Column biggerColumn = Column.getBigger(from.getColumn(), to.getColumn());
                List<Column> columns = Arrays.asList(Column.values())
                        .subList(smallerColumn.ordinal() + 1, biggerColumn.ordinal());
                List<Position> positions = columns.stream()
                        .map(column -> Positions.of(from.getRow(), column))
                        .collect(Collectors.toList());
                return positions;
            }),
    DIAGONAL_TOP_LEFT((rowDiff, columnDiff) -> isPositive(rowDiff) && isNegative(columnDiff),
            (from, to) -> {
                Column smallerColumn = Column.getSmaller(from.getColumn(), to.getColumn());
                Column biggerColumn = Column.getBigger(from.getColumn(), to.getColumn());
                Row smallerRow = Row.getSmaller(from.getRow(), to.getRow());
                Row biggerRow = Row.getBigger(from.getRow(), to.getRow());

                List<Row> rows = Arrays.asList(Row.values())
                        .subList(smallerRow.ordinal() + 1, biggerRow.ordinal());
                List<Column> columns = Arrays.asList(Column.values())
                        .subList(smallerColumn.ordinal() + 1, biggerColumn.ordinal());
                List<Position> positions = new ArrayList<>();

                for (int i = 0; i < rows.size(); i++){
                    positions.add(Positions.of(rows.get(i), columns.get(i)));
                }
                return positions;
            }),
    DIAGONAL_TOP_RIGHT((rowDiff, columnDiff) -> isNegative(rowDiff) && isNegative(columnDiff),
            (from, to) -> {
                Column smallerColumn = Column.getSmaller(from.getColumn(), to.getColumn());
                Column biggerColumn = Column.getBigger(from.getColumn(), to.getColumn());
                Row smallerRow = Row.getSmaller(from.getRow(), to.getRow());
                Row biggerRow = Row.getBigger(from.getRow(), to.getRow());

                List<Row> rows = Arrays.asList(Row.values())
                        .subList(smallerRow.ordinal() + 1, biggerRow.ordinal());
                List<Column> columns = Arrays.asList(Column.values())
                        .subList(smallerColumn.ordinal() + 1, biggerColumn.ordinal());
                List<Position> positions = new ArrayList<>();

                for (int i = 0; i < rows.size(); i++){
                    positions.add(Positions.of(rows.get(i), columns.get(i)));
                }
                return positions;
            }),
    DIAGONAL_DOWN_LEFT((rowDiff, columnDiff) -> isPositive(rowDiff) && isPositive(columnDiff),
            (from, to) -> {
                Column smallerColumn = Column.getSmaller(from.getColumn(), to.getColumn());
                Column biggerColumn = Column.getBigger(from.getColumn(), to.getColumn());
                Row smallerRow = Row.getSmaller(from.getRow(), to.getRow());
                Row biggerRow = Row.getBigger(from.getRow(), to.getRow());

                List<Row> rows = Arrays.asList(Row.values())
                        .subList(smallerRow.ordinal() + 1, biggerRow.ordinal());
                List<Column> columns = Arrays.asList(Column.values())
                        .subList(smallerColumn.ordinal() + 1, biggerColumn.ordinal());
                List<Position> positions = new ArrayList<>();

                for (int i = 0; i < rows.size(); i++){
                    positions.add(Positions.of(rows.get(i), columns.get(i)));
                }
                return positions;
            }),
    DIAGONAL_DOWN_RIGHT((rowDiff, columnDiff) -> isNegative(rowDiff) && isPositive(columnDiff),
            (from, to) -> {
                Column smallerColumn = Column.getSmaller(from.getColumn(), to.getColumn());
                Column biggerColumn = Column.getBigger(from.getColumn(), to.getColumn());
                Row smallerRow = Row.getSmaller(from.getRow(), to.getRow());
                Row biggerRow = Row.getBigger(from.getRow(), to.getRow());

                List<Row> rows = Arrays.asList(Row.values())
                        .subList(smallerRow.ordinal() + 1, biggerRow.ordinal());
                List<Column> columns = Arrays.asList(Column.values())
                        .subList(smallerColumn.ordinal() + 1, biggerColumn.ordinal());
                List<Position> positions = new ArrayList<>();

                for (int i = 0; i < rows.size(); i++){
                    positions.add(Positions.of(rows.get(i), columns.get(i)));
                }
                return positions;
            });

    private final BiPredicate<Integer, Integer> judge;
    private final BiFunction<Position, Position, List<Position>> positionsBetween;

    Direction(BiPredicate<Integer, Integer> judge, BiFunction<Position, Position, List<Position>> positionsBetween) {
        this.judge = judge;
        this.positionsBetween = positionsBetween;
    }

    private static boolean isPositive(int number) {
        return number > 0;
    }

    private static boolean isZero(int number) {
        return number == 0;
    }

    private static boolean isNegative(int number) {
        return number < 0;
    }

    public boolean getJudge(Position from, Position to) {
        int rowDiff = Row.getDiff(from.getRow(), to.getRow());
        int columnDiff = Column.getDiff(from.getColumn(), to.getColumn());
        return judge.test(rowDiff, columnDiff);
    }

    public List<Position> getPositionsBetween(Position from, Position to) {
        return positionsBetween.apply(from, to);
    }

    public static Direction getDirection(Position from, Position to) {
       return  Arrays.stream(Direction.values())
                .filter(x -> x.getJudge(from, to))
                .findFirst()
               .orElseThrow(() -> new IllegalArgumentException("이동 방식을 찾을 수 없습니다."));
    }
}
