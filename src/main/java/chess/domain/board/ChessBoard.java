package chess.domain.board;

import chess.domain.piece.Piece;

import java.util.List;

public class ChessBoard {

    private List<Piece> pieces;

    public ChessBoard(List<Piece> pieces) {
        this.pieces = pieces;
    }

    public List<Piece> getPieces() {
        return pieces;
    }
}
