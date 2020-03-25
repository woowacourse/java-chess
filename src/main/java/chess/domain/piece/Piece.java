package chess.domain.piece;

import java.util.Set;

import chess.domain.Board;
import chess.domain.position.Position;

public interface Piece {
	Set<Position> findMovablePositions(Position currentPosition, Board board);

	boolean isEnemy(Piece that);

	Color getColor();

	String getName();
}
