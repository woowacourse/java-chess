package chess.domain.piece.strategy.pawn;

import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.List;

@FunctionalInterface
public interface PawnMovingStrategy {

    boolean canMove(List<List<Piece>> board, Position source, Position target);
}
