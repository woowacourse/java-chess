package chess.view.resposne;

import chess.domain.piece.Piece;

public class PieceResponse {

    private final String piece;

    private PieceResponse(String piece) {
        this.piece = piece;
    }

    public static PieceResponse from(Piece piece) {
        return new PieceResponse(PieceMapper.getPieceName(piece));
    }

    public String getPiece() {
        return piece;
    }
}
