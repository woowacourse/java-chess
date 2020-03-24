package chess.domain;

import chess.domain.chesspiece.ChessPiece;

import java.util.List;

public class ChessBoard {
    private List<ChessPiece> blackChessPieces;
    private List<ChessPiece> whiteChessPieces;

    public ChessBoard(List<ChessPiece> blackChessPieces, List<ChessPiece> whiteChessPieces) {
        this.blackChessPieces = blackChessPieces;
        this.whiteChessPieces = whiteChessPieces;
    }
}
