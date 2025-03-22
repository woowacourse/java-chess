package chess.piece;

import chess.Color;
import chess.Position;

import java.util.List;
import java.util.Map;

public interface ChessPiece {
    List<Position> getAvailableDestinations(Position startPosition, Map<Position, ChessPiece> positions);
    Color getColor();
}
