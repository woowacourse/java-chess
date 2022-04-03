package chess.web.dto;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceSymbol;

public class PieceDto {

    private final String color;
    private final String symbol;

    public PieceDto(String color, String symbol) {
        this.color = color;
        this.symbol = symbol;
    }

    public static PieceDto of(Piece piece) {
        return new PieceDto(piece.getColor(), PieceSymbol.findWebSymbol(piece));
    }

    public String getColor() {
        return color;
    }

    public String getSymbol() {
        return symbol;
    }
}
