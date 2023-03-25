package chess.domain.dto;

import chess.domain.piece.Piece;

public class PieceDto {

    private final String pieceColor;
    private final String pieceName;

    private PieceDto(String pieceName, String pieceColor) {
        this.pieceName = pieceName;
        this.pieceColor = pieceColor;
    }

    public static PieceDto from(Piece piece) {
        return new PieceDto(piece.getType().getName(), piece.getColor().name());
    }

    @Override
    public String toString() {
        return "PieceResponse{" +
                "pieceType='" + pieceName + '\'' +
                ", pieceColor='" + pieceColor + '\'' +
                '}';
    }

    public String getPieceName() {
        return pieceName;
    }

    public String getPieceColor() {
        return pieceColor;
    }
}
