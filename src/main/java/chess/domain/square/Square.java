package chess.domain.square;

import chess.domain.position.Path;
import chess.domain.position.Position;

import java.util.Map;

public interface Square {
    boolean canMove(Path path, Map<Position, Square> board);

    boolean canAttack(Path path, Map<Position, Square> board);

    void move();
}
