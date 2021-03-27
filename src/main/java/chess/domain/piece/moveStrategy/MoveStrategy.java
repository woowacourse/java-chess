package chess.domain.piece.moveStrategy;

import chess.domain.board.Position;

import java.util.List;

public interface MoveStrategy {
    List<Position> searchMovablePositions(Position target);
}
