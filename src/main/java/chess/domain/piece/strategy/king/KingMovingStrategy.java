package chess.domain.piece.strategy.king;

import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.List;

@FunctionalInterface
public interface KingMovingStrategy {

    boolean canMove(List<List<Piece>> board, Position source, Position target);
}
