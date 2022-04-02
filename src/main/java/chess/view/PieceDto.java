package chess.view;

import chess.domain.pieces.Piece;

public class PieceDto {

    private final String symbol;
    private final String position;

    public PieceDto(String symbol, String position) {
        this.symbol = symbol;
        this.position = position;
    }

    public static PieceDto of(Piece piece, String position) {
        return new PieceDto(piece.symbol(), position);
    }

    public String getSymbol() {
        return symbol;
    }

    public String getPosition() {
        return position;
    }
}
