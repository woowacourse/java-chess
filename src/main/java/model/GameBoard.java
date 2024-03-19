package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import point.Column;
import point.Point;
import point.Row;

public class GameBoard {

    private static final Map<Column, BoardInfo> initPosition = new HashMap<>();

    static {
        initPosition.put(Column.FIRST, BoardInfo.ROOK);
        initPosition.put(Column.SECOND, BoardInfo.KNIGHT);
        initPosition.put(Column.THIRD, BoardInfo.BISHOP);
        initPosition.put(Column.FOURTH, BoardInfo.KING);
        initPosition.put(Column.FIFTH, BoardInfo.QUEEN);
        initPosition.put(Column.SIXTH, BoardInfo.BISHOP);
        initPosition.put(Column.SEVENTH, BoardInfo.KNIGHT);
        initPosition.put(Column.EIGHTH, BoardInfo.ROOK);
    }

    private final List<List<Square>> board;

    public GameBoard() {
        this.board = init();
    }

    private List<List<Square>> init() {
        List<List<Square>> board = new ArrayList<>();
        for (Column column : Column.values()) {
            List<Square> line = new ArrayList<>();
            for (Row row : Row.values()) {
                line.add(new Square(new Point(row, column), new SquareInfo(BoardInfo.BLANK, Camp.GRAY)));
            }
            board.add(line);
        }
        return board;
    }

    public void setting() {

        var first = settingExceptPawn(Camp.BLACK);
        var second = settingPawn(Camp.BLACK);
        var seventh = settingPawn(Camp.WHITE);
        var eighth = settingExceptPawn(Camp.WHITE);

        board.set(0, first);
        board.set(1, second);
        board.set(6, seventh);
        board.set(7, eighth);
    }

    private List<Square> settingExceptPawn(final Camp camp) {
        return Arrays.stream(Column.values())
                .map(column -> new Square(new Point(Row.EIGHTH, column),
                        new SquareInfo(initPosition.get(column), camp)))
                .toList();
    }

    private List<Square> settingPawn(final Camp camp) {
        return Arrays.stream(Column.values())
                .map(column -> new Square(new Point(Row.SEVENTH, column), new SquareInfo(BoardInfo.PAWN, camp)))
                .toList();
    }

    public List<List<Square>> getBoard() {
        return board;
    }
}
