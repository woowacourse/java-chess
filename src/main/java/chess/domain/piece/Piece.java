package chess.domain.piece;

import chess.domain.position.Position;
import java.util.List;

public interface Piece {
    String identifyType();

    boolean isBlack();

    boolean isSameColor(Color color);

    boolean canMove(Position source, Position target, Piece piece);

    List<Position> searchPath(Position source, Position target);
}
