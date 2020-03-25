package chess.domain.moverules;

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
public enum MoveRule {
    LEFT((rowDiff, columnDiff) -> isNegative(rowDiff) && isZero(columnDiff),
            (source, target) -> {
                Row smallerRow = Row.getSmaller(source.getRow(), target.getRow());
                Row biggerRow = Row.getBigger(source.getRow(), target.getRow());
                System.out.println("small row "+smallerRow.ordinal());
                System.out.println("big row "+biggerRow.ordinal());
                List<Row> rows = Arrays.asList(Row.values())
                        .subList(smallerRow.ordinal() + 1, biggerRow.ordinal());
                List<Position> positions = rows.stream()
                        .map(row -> Positions.of(row, source.getColumn()))
                        .collect(Collectors.toList());
                return positions;
            }
    ),
    RIGHT((rowDiff, columnDiff) -> isPositive(rowDiff) && isZero(columnDiff),
            (source, target) -> {
                Row smallerRow = Row.getSmaller(source.getRow(), target.getRow());
                Row biggerRow = Row.getBigger(source.getRow(), target.getRow());
                List<Row> rows = Arrays.asList(Row.values())
                        .subList(smallerRow.ordinal() + 1, biggerRow.ordinal());
                List<Position> positions = rows.stream()
                        .map(row -> Positions.of(row, source.getColumn()))
                        .collect(Collectors.toList());
                return positions;
            }
    ),
    TOP((rowDiff, columnDiff) -> isZero(rowDiff) && isPositive(columnDiff),
            (source, target) -> {
                Column smallerColumn = Column.getSmaller(source.getColumn(), target.getColumn());
                Column biggerColumn = Column.getBigger(source.getColumn(), target.getColumn());
                List<Column> columns = Arrays.asList(Column.values())
                        .subList(smallerColumn.ordinal() + 1, biggerColumn.ordinal());
                List<Position> positions = columns.stream()
                        .map(column -> Positions.of(source.getRow(), column))
                        .collect(Collectors.toList());
                return positions;
            }),
    DOWN((rowDiff, columnDiff) -> isZero(rowDiff) && isNegative(columnDiff),
            (source, target) -> {
                Column smallerColumn = Column.getSmaller(source.getColumn(), target.getColumn());
                Column biggerColumn = Column.getBigger(source.getColumn(), target.getColumn());
                List<Column> columns = Arrays.asList(Column.values())
                        .subList(smallerColumn.ordinal() + 1, biggerColumn.ordinal());
                List<Position> positions = columns.stream()
                        .map(column -> Positions.of(source.getRow(), column))
                        .collect(Collectors.toList());
                return positions;
            }),
    DIAGONAL_TOP_LEFT((rowDiff, columnDiff) -> isNegative(rowDiff) && isNegative(columnDiff),
            (source, target) -> {
                Column smallerColumn = Column.getSmaller(source.getColumn(), target.getColumn());
                Column biggerColumn = Column.getBigger(source.getColumn(), target.getColumn());
                Row smallerRow = Row.getSmaller(source.getRow(), target.getRow());
                Row biggerRow = Row.getBigger(source.getRow(), target.getRow());

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
    DIAGONAL_TOP_RIGHT((rowDiff, columnDiff) -> isPositive(rowDiff) && isNegative(columnDiff),
            (source, target) -> {
                Column smallerColumn = Column.getSmaller(source.getColumn(), target.getColumn());
                Column biggerColumn = Column.getBigger(source.getColumn(), target.getColumn());
                Row smallerRow = Row.getSmaller(source.getRow(), target.getRow());
                Row biggerRow = Row.getBigger(source.getRow(), target.getRow());

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
    DIAGONAL_DOWN_LEFT((rowDiff, columnDiff) -> isNegative(rowDiff) && isPositive(columnDiff),
            (source, target) -> {
                Column smallerColumn = Column.getSmaller(source.getColumn(), target.getColumn());
                Column biggerColumn = Column.getBigger(source.getColumn(), target.getColumn());
                Row smallerRow = Row.getSmaller(source.getRow(), target.getRow());
                Row biggerRow = Row.getBigger(source.getRow(), target.getRow());

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
            (source, target) -> {
                Column smallerColumn = Column.getSmaller(source.getColumn(), target.getColumn());
                Column biggerColumn = Column.getBigger(source.getColumn(), target.getColumn());
                Row smallerRow = Row.getSmaller(source.getRow(), target.getRow());
                Row biggerRow = Row.getBigger(source.getRow(), target.getRow());

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

    MoveRule(BiPredicate<Integer, Integer> judge, BiFunction<Position, Position, List<Position>> positionsBetween) {
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

    public boolean getJudge(int rowDiff, int columnDiff) {
        return judge.test(rowDiff, columnDiff);
    }

    public List<Position> getPositionsBetween(Position source, Position target) {
        return positionsBetween.apply(source, target);
    }
}
