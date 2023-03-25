package chess.domain.dto;

import chess.domain.piece.Piece;

public class SavePieceDto {
    private final String pieceColor;
    private final String pieceType;

    private SavePieceDto(String pieceType, String pieceColor) {
        this.pieceType = pieceType;
        this.pieceColor = pieceColor;
    }

    public static SavePieceDto from(Piece piece) {
        return new SavePieceDto(piece.getType().name(), piece.getColor().name());
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
