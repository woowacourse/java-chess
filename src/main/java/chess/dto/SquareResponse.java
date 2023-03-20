package chess.dto;

import chess.domain.Position;
import chess.domain.piece.Piece;
import chess.view.SquareMapper;

public class SquareResponse {
    private final String symbol;
    private final int x;
    private final int y;

    private SquareResponse(String symbol, int x, int y) {
        this.symbol = symbol;
        this.x = x;
        this.y = y;
    }

    public static SquareResponse of(Position position, Piece piece) {
        String symbol = SquareMapper.map(piece.getTeam(), piece.getRole());
        return new SquareResponse(symbol, position.getX(), position.getY());
    }

    public String getSymbol() {
        return symbol;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
