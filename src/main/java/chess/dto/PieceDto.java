package chess.dto;

import chess.domain.piece.Piece;
import chess.domain.position.Position;

public class PieceDto {
    private final String position;
    private final String symbol;

    private PieceDto(String position, String symbol) {
        this.position = position;
        this.symbol = symbol;
    }

    public static PieceDto of(Position position, Piece piece) {
        return new PieceDto(position.getName(), piece.getSignature());
    }

    public String getPosition() {
        return position;
    }

    public String getSymbol() {
        return symbol;
    }
}
