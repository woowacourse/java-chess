package chess.domain.piece.strategy;

import java.util.Map;

import chess.domain.board.Position;
import chess.domain.piece.Path;
import chess.domain.piece.Piece;

@FunctionalInterface
public interface MoveStrategy {
	Path findMovablePositions(Path path, Map<Position, Piece> pieces);
}
