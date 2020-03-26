package chess.domain.piece;

import java.util.Set;

import chess.domain.board.Board;
import chess.domain.position.Position;

public interface Piece {
	Set<Position> findMovablePositions(Position currentPosition, Board board);

	boolean isEnemy(Piece that);

	boolean isSameColor(Color color);

	boolean isNotSameColor(Color color);

	Color getColor();

	double getScore();

	String getName();
}
