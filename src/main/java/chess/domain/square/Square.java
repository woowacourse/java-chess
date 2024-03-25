package chess.domain.square;

import chess.domain.position.PathFinder;
import chess.domain.position.Position;

import chess.domain.square.piece.Color;
import java.util.Map;

public interface Square {
    boolean canArrive(PathFinder pathFinder, Map<Position, Square> board);

    boolean isColor(Color color);
}
