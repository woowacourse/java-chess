package chess.domain.piece.movable;

import chess.domain.board.Board;
import chess.domain.board.position.Column;
import chess.domain.board.position.Position;
import chess.domain.board.position.Row;

import java.util.HashSet;
import java.util.Set;

public class RookMovable implements Movable {

	@Override
	public Set<Position> move(Position position) {
		Set<Position> movablePositions = new HashSet<>();
		for (Row row : Row.values()) {
			movablePositions.add(Board.of(row, position.getColumn()));
		}
		for (Column column : Column.values()) {
			movablePositions.add(Board.of(position.getRow(), column));
		}
		movablePositions.remove(position);
		return movablePositions;
	}
}
