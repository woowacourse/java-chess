package chess.domain.board;

import chess.domain.chesspiece.ChessPiece;

import java.util.List;

public class Row {
    private final List<ChessPiece> chessPieces;

    public Row(List<ChessPiece> chessPieces) {
        this.chessPieces = chessPieces;
    }

    public static Row of(List<ChessPiece> chessPieces) {
        return new Row(chessPieces);
    }

    public static Row of(Row row) {
        return new Row(row.getChessPieces());
    }

    public ChessPiece get(int x) {
        return chessPieces.get(x);
    }

    public void modifyRow(int index, ChessPiece chessPiece) {
        chessPieces.set(index, chessPiece);
    }

    public List<ChessPiece> getChessPieces() {
        return chessPieces;
    }
}
