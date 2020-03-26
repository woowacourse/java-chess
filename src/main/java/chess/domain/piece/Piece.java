package chess.domain.piece;

import java.util.Set;

import chess.domain.Board;
import chess.domain.position.Position;

public interface Piece {
	Set<Position> findMovablePositions(Position currentPosition, Board board);

	boolean isEnemy(Piece that);

	boolean isNotSameColor(Color color);

	Color getColor();

	String getName();
}
