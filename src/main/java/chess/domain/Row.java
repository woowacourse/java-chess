package chess.domain;

import chess.domain.chesspiece.ChessPiece;

import java.util.List;

public class Row {
    private List<ChessPiece> chessPieces;

    public Row(List<ChessPiece> chessPieces) {
        this.chessPieces = chessPieces;
    }

    public ChessPiece get(int x) {
        return chessPieces.get(x);
    }
}
