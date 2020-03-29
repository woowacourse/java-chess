package chess.domain.piece;

import java.util.Set;

import chess.domain.board.Board;
import chess.domain.position.Position;

public interface MovingStrategy {
	Set<Position> findMovablePositions(Position currentPosition, Board board);
}
