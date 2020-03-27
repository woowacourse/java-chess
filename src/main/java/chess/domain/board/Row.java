package chess.domain.board;

import chess.domain.chesspiece.ChessPiece;

import java.util.List;

public class Row {
    private final List<ChessPiece> chessPieces;

    private Row(List<ChessPiece> chessPieces) {
        this.chessPieces = chessPieces;
    }

    public static Row of(List<ChessPiece> chessPieces) {
        return new Row(chessPieces);
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
