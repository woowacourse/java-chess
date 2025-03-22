package chess.piece;

import chess.Color;
import chess.Position;

import java.util.List;
import java.util.Map;

public class Pawn implements ChessPiece {
    private final Color color;
    private boolean isFirstMove;

    public Pawn(Color color) {
        this.color = color;
        isFirstMove = true;
    }

    @Override
    public List<Position> getAvailableDestinations(Position startPosition, Map<Position, ChessPiece> positions) {
        return null;
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public Position move(Position from, Position to, Map<Position, ChessPiece> positions) {
        isFirstMove = false;
        return null;
    }
}
