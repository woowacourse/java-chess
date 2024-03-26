package dto;

import domain.piece.attribute.Color;

public record PieceDto(PointDto pointDto, String pieceSymbol, Color color) {

    public PieceDto convertCaseSensitive() {
        if (this.color == Color.BLACK) {
            return new PieceDto(pointDto, pieceSymbol.toUpperCase(), color);
        }
        return this;
    }
}
