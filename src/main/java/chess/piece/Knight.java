package chess.piece;

import chess.Color;
import chess.Position;

import java.util.List;
import java.util.Map;

public class Knight implements ChessPiece {
    private final Color color;

    public Knight(Color color) {
        this.color = color;
    }

    @Override
    public List<Position> getAvailableDestinations(Position startPosition, Map<Position, ChessPiece> positions) {
        return null;
    }

    @Override
    public Color getColor() {
        return color;
    }
}
