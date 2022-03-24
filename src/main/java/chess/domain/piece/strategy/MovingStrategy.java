package chess.domain.piece.strategy;

import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.List;

@FunctionalInterface
public interface MovingStrategy {

    void validateMove(List<List<Piece>> board, Position sourcePosition, Position targetPosition);
}
