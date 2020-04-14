package view.board;

import domain.pieces.Piece;
import domain.coordinate.Row;

import java.util.*;

public class Board {
    private final List<RowOfBoard> board;

    private Board(List<RowOfBoard> board) {
        this.board = board;
    }

    public static Board of(Set<Piece> pieces) {
        Board board = createEmptyBoard();
        board.putPiecesOnBoard(pieces);
        return board;
    }

    private static Board createEmptyBoard() {
        List<RowOfBoard> board = new ArrayList<>();

        for (Row row : Row.values()) {
            board.add(RowOfBoard.createEmpty());
        }

        return new Board(board);
    }

    public void putPiecesOnBoard(Set<Piece> pieces) {
        for (Piece piece : pieces) {
            putPieceOnBoard(piece);
        }
    }

    private void putPieceOnBoard(Piece piece) {
        putInitialOnBoard(piece.getRowIndex(), piece.getColumnIndex(), piece.getInitial());
    }

    private void putInitialOnBoard(int row, int column, String initialOfPiece) {
        getRowOfBoard(row).putOnColumn(column, initialOfPiece);
    }

    private RowOfBoard getRowOfBoard(int row) {
        return board.get(row);
    }

    public List<RowOfBoard> getBoard() {
        return Collections.unmodifiableList(board);
    }

    public List<List<String>> getLists() {
        List<List<String>> lists = new ArrayList<>();
        for (RowOfBoard rowOfBoard : getBoard()) {
            lists.add(new ArrayList<>(rowOfBoard.getRowOfBoard()));
        }

        return lists;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Board board1 = (Board) o;
        return Objects.equals(board, board1.board);
    }

    @Override
    public int hashCode() {
        return Objects.hash(board);
    }

    @Override
    public String toString() {
        return "Board{" +
                "board=" + board +
                '}';
    }
}
