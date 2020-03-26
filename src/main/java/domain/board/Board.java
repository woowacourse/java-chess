package domain.board;

import domain.pieces.Piece;
import domain.point.Column;
import domain.point.Point;
import domain.point.Row;

import java.util.*;

public class Board {
    private static final String EMPTY = ".";

    private final List<List<String>> board;

    public Board(List<List<String>> board) {
        this.board = new ArrayList<>(board);
    }

    public static Board of(Set<Piece> pieces) {
        Board board = createEmptyBoard();
        board.setAll(pieces);
        return board;
    }

    private static Board createEmptyBoard() {
        List<List<String>> board = new ArrayList<>();

        for (Row row : Row.values()) {
            board.add(new ArrayList<>());
            for (Column column : Column.values()) {
                board.get(row.getIndex()).add(EMPTY);
            }
        }

        return new Board(board);
    }

    public void setAll(Set<Piece> pieces) {
        for (Piece piece : pieces) {
            set(piece);
        }
    }

    private void setEmpty(Point point) {
        set(point.getRowIndex(), point.getColumnIndex(), EMPTY);
    }

    private void set(Piece piece) {
        set(piece.getRowIndex(), piece.getColumnIndex(), piece.getInitial());
    }

    private void set(int i, int j, String initial) {
        board.get(i).set(j, initial);
    }

    public List<List<String>> getBoard() {
        return Collections.unmodifiableList(board);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Board board = (Board) o;
        return Objects.equals(this.board, board.board);
    }

    @Override
    public int hashCode() {
        return Objects.hash(board);
    }
}
