package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import piece.Bishop;
import piece.Blank;
import piece.King;
import piece.Knight;
import piece.Pawn;
import piece.Piece;
import piece.Queen;
import piece.Rook;
import point.Column;
import point.Position;
import point.Row;

public class GameBoard {

    private static final Map<Column, BiFunction<Camp, Position, Piece>> initPosition = new HashMap<>();

    static {
        initPosition.put(Column.FIRST, Rook::new);
        initPosition.put(Column.SECOND, Knight::new);
        initPosition.put(Column.THIRD, Bishop::new);
        initPosition.put(Column.FOURTH, Queen::new);
        initPosition.put(Column.FIFTH, King::new);
        initPosition.put(Column.SIXTH, Bishop::new);
        initPosition.put(Column.SEVENTH, Knight::new);
        initPosition.put(Column.EIGHTH, Rook::new);
    }

    private final List<List<Piece>> board;

    public GameBoard() {
        this.board = init();
    }

    private List<List<Piece>> init() {
        List<List<Piece>> board = new ArrayList<>();
        for (Column column : Column.values()) { //TODO 굳이 row, column 안 타도 됨
            List<Piece> line = new ArrayList<>();
            for (Row row : Row.values()) {
                line.add(new Blank());
            }
            board.add(line);
        }
        return board;
    }

    public void setting() {

        var first = settingExceptPawn(Camp.BLACK, Row.EIGHTH);
        var second = settingPawn(Camp.BLACK, Column.SEVENTH);
        var seventh = settingPawn(Camp.WHITE, Column.SECOND);
        var eighth = settingExceptPawn(Camp.WHITE, Row.FIFTH);

        board.set(0, first);
        board.set(1, second);
        board.set(6, seventh);
        board.set(7, eighth);
    }

    private List<Piece> settingExceptPawn(final Camp camp, Row row) {
        return Arrays.stream(Column.values())
                .map(column -> initPosition.get(column).apply(camp, new Position(row, column)))
                .toList();
    }

    public Piece findByPosition(Position position) {
        int rowIndex = position.getRow().getIndex();
        int colIndex = position.getColumn().getIndex();

        for (List<Piece> pieces : board) {
            System.out.println(pieces.toString());
        }
        System.out.println("====");

        System.out.println("position" + position);
        System.out.println("(" + rowIndex + ", " + colIndex + ")");
        return board.get(rowIndex).get(colIndex);
    }

    private List<Piece> settingPawn(final Camp camp, final Column column) {
        return Arrays.stream(Row.values())
                .map(row -> (Piece) new Pawn(camp, new Position(row, column)))
                .toList();
    }

    public List<List<Piece>> getBoard() {
        return board;
    }
}
