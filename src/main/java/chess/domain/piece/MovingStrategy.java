package chess.domain.piece;

import java.util.Set;
import java.util.function.Function;

import chess.domain.position.Position;

public interface MovingStrategy {
	Set<Position> findMovablePositions(Position currentPosition, Function<Position, Piece> pieceFinder);
}
