package chess.domain.piece.movable;

import chess.domain.Direction;
import chess.domain.board.Board;
import chess.domain.board.position.Column;
import chess.domain.board.position.Position;
import chess.domain.board.position.Row;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PawnMovable implements Movable {
	private final List<Direction> moveDirection;

	public PawnMovable(List<Direction> moveDirection) {
		this.moveDirection = moveDirection;
	}

	@Override
	public Set<Position> move(Position position) {
		Set<Position> movablePositions = new HashSet<>();
		for (Direction direction : moveDirection) {
			addMovable(position, movablePositions, direction);
		}
		return movablePositions;
	}

	private void addMovable(Position position, Set<Position> movablePositions, Direction direction) {
		Row row = position.getRow();
		Column column = position.getColumn();
		if (Board.checkBound(position, direction)) {
			Row validRow = row.calculate(direction.getXDegree());
			Column validColumn = column.calculate(direction.getYDegree());
			movablePositions.add(Board.of(validRow, validColumn));
		}
	}
}
