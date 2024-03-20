package chess.domain.piece;

import chess.domain.position.Position;
import java.util.List;

public interface Piece {
    String getType();

    boolean isBlack();

    boolean canMove(Position source, Position target, Color color);

    List<Position> searchPath(Position source, Position target);
}
