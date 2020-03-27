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
        board.get(row).putOnColumn(column, initialOfPiece);
    }

    public List<RowOfBoard> getBoard() {
        return Collections.unmodifiableList(board);
    }
}
