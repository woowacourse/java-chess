package chess.dto.response;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceColor;
import chess.domain.piece.PieceType;

public class PieceDto {
    private final PieceType pieceType;
    private final PieceColor pieceColor;

    private PieceDto(PieceType pieceType, PieceColor pieceColor) {
        this.pieceType = pieceType;
        this.pieceColor = pieceColor;
    }

    public static PieceDto from(Piece piece) {
        return new PieceDto(piece.getPieceType(), piece.getPieceColor());
    }

    public static PieceDto of(PieceType pieceType, PieceColor pieceColor) {
        return new PieceDto(pieceType, pieceColor);
    }

    public PieceType getPieceType() {
        return pieceType;
    }

    public PieceColor getPieceColor() {
        return pieceColor;
    }

    public Piece toPiece() {
        return new Piece(pieceType, pieceColor);
    }

    @Override
    public String toString() {
        return "PieceDto{" +
                "pieceType=" + pieceType +
                ", pieceColor=" + pieceColor +
                '}';
    }
}
