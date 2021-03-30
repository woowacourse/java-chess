package chess.dto;

import chess.domain.board.Square;

public class PieceDto {
    private final String piece;

    public PieceDto(Square square) {
        this.piece = square.pieceName();
    }

    public String getPiece() {
        if (piece.equals(".")) {
            return "chess-piece ";
        }
        return "chess-piece " + piece;
    }
}
