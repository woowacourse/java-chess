package chess.domain.piece.movable;

import chess.domain.board.Board;
import chess.domain.board.position.Column;
import chess.domain.board.position.Position;
import chess.domain.board.position.Row;

import java.util.HashSet;
import java.util.Set;

public class BishopMovable implements Movable{

	@Override
	public Set<Position> move(Position position) {
		Set<Position> movablePositions = new HashSet<>();

		for (Row row : Row.values()) {
			createByRow(position, movablePositions, row); // TODO: 2020/03/25 네이밍
		}
		movablePositions.remove(position);
		return movablePositions;
	}

	private void createByRow(Position position, Set<Position> movablePositions, Row row) {
		for (Column column : Column.values()) {
			addAvailable(position, movablePositions, row, column);
		}
	}

	private void addAvailable(Position position, Set<Position> movablePositions, Row row, Column column) {
		if(position.isSameDiagonal(Board.of(row,column))) {
			movablePositions.add(Board.of(row, column));
		}
	}
}
