package chess.domain.board;

import chess.domain.piece.Piece;

import java.util.List;

public class ChessBoard {
    private final List<Piece> whitePieces;
    private final List<Piece> blackPieces;

    public ChessBoard(List<Piece> whitePieces, List<Piece> blackPieces) {
        this.whitePieces = whitePieces;
        this.blackPieces = blackPieces;
    }
}
