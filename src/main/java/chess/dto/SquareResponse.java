package chess.dto;

import chess.domain.Position;
import chess.domain.Square;
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

    public static SquareResponse of(Square square) {
        String symbol = SquareMapper.map(square.getTeam(), square.getRole());
        Position position = square.getPosition();
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
