package domain;

import com.google.common.collect.Lists;
import domain.piece.*;
import domain.position.Column;
import domain.position.Position;
import domain.position.Row;

import java.util.*;
import java.util.function.Supplier;

public class Board {
    private final Map<Position, Piece> board = new HashMap<>();

    public Board() {
        setUpEmpty();
        setUpPawn();
        setUpGeneral();
    }

    private void setUpGeneral() {
        setUpGeneralByColor(Color.BLACK, Row.EIGHT);
        setUpGeneralByColor(Color.WHITE, Row.ONE);
    }

    private void setUpGeneralByColor(Color color, Row row) {
        board.put(Position.of(Column.A, row), new Rook(color));
        board.put(Position.of(Column.B, row), new Knight(color));
        board.put(Position.of(Column.C, row), new Bishop(color));
        board.put(Position.of(Column.D, row), new Queen(color));
        board.put(Position.of(Column.E, row), new King(color));
        board.put(Position.of(Column.F, row), new Bishop(color));
        board.put(Position.of(Column.G, row), new Knight(color));
        board.put(Position.of(Column.H, row), new Rook(color));
    }

    public void setUpEmpty() {
        List<Row> emptyRows = Arrays.asList(Row.THREE, Row.FOUR, Row.FIVE, Row.SIX);
        for (Row row : emptyRows){
            setUpRow(row, Empty::new);
        }
    }

    public void setUpPawn() {
        setUpRow(Row.SEVEN, () -> new Pawn(Color.BLACK));
        setUpRow(Row.TWO, () -> new Pawn(Color.WHITE));
    }

    private void setUpRow(Row row, Supplier<Piece> function) {
        for (Column column:Column.values()){
            board.put(Position.of(column, row), function.get());
        }
    }

    public Piece pieceOf(Column column, Row row){
        return board.get(Position.of(column, row));
    }

    public void display() {
        List<Row> rows = Arrays.asList(Row.values());
        Collections.reverse(rows);
        for (Row row : rows) {
            for (Column column : Column.values()) {
                System.out.print(board.get(Position.of(column, row)).display());
            }
            System.out.println();
        }
    }
}
