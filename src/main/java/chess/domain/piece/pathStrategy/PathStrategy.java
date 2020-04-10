package chess.domain.piece.pathStrategy;

import chess.domain.board.Position;

import java.util.List;

public interface PathStrategy {
    void validateDistance(Position sourcePosition, Position targetPosition);

    List<Position> createPath(Position sourcePosition, Position targetPosition);
}
