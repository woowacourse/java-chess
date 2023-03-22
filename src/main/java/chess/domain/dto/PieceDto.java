package chess.domain.dto;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;

public class PieceDto {

    private final String pieceType;
    private final String pieceColor;

    private PieceDto(String pieceType, String pieceColor) {
        this.pieceType = pieceType;
        this.pieceColor = pieceColor;
    }

    public static PieceDto from(Piece piece) {
        return new PieceDto(PieceType.getNameOf(piece), piece.getColor().name());
    }

    @Override
    public String toString() {
        return "PieceResponse{" +
                "pieceType='" + pieceType + '\'' +
                ", pieceColor='" + pieceColor + '\'' +
                '}';
    }

    public String getPieceType() {
        return pieceType;
    }

    public String getPieceColor() {
        return pieceColor;
    }
}
