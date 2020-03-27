package domain.board;

import domain.pieces.Piece;
import domain.point.Row;

import java.util.*;

public class Board {
    private final List<RowOfBoard> board;

    private Board(List<RowOfBoard> board) {
        this.board = board;
    }

    public static Board of(Set<Piece> pieces) {
        Board board = createEmptyBoard();
        board.putAllOnBoard(pieces);
        return board;
    }

    private static Board createEmptyBoard() {
        List<RowOfBoard> board = new ArrayList<>();

        for (Row row : Row.values()) {
            board.add(RowOfBoard.createEmpty());
        }

        return new Board(board);
    }

    public void putAllOnBoard(Set<Piece> pieces) {
        for (Piece piece : pieces) {
            putOnBoard(piece);
        }
    }

    private void putOnBoard(Piece piece) {
        putOnBoard(piece.getRowIndex(), piece.getColumnIndex(), piece.getInitial());
    }

    private void putOnBoard(int row, int column, String initial) {
        board.get(row).putOnColumn(column, initial);
    }

    public List<RowOfBoard> getBoard() {
        return Collections.unmodifiableList(board);
    }
}
