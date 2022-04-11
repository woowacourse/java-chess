package chess.dto;

import chess.domain.piece.Piece;
import chess.domain.position.Square;

public class SquareAndPiece {
    private final String square;
    private final PieceDTO piece;

    private SquareAndPiece(String square, PieceDTO piece) {
        this.square = square;
        this.piece = piece;
    }

    public static SquareAndPiece of(Square square, Piece piece) {
        return new SquareAndPiece(square.getName(), PieceDTO.of(piece));
    }

    public String getSquare() {
        return square;
    }

    public PieceDTO getPiece() {
        return piece;
    }
}
