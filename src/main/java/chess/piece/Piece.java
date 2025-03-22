package chess.piece;

import chess.Color;
import chess.Position;
import java.util.Map;

public interface Piece {
    boolean isMovableTo(Position position, final Map<Position, Piece> positions);
    Piece moveTo(Position position);
    String getName();
    Color getColor();
    Position getPosition();
}
