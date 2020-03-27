package chess.domain.piece.movable;

import chess.domain.position.PositionFactory;
import chess.domain.position.Column;
import chess.domain.position.Position;
import chess.domain.position.Row;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class PawnMovable implements Movable {
	@Override
	public Set<Position> createMovablePositions(Position position, List<Piece> pieces, Color color) {
		List<Direction> moveDirection = Direction.getPawnDirectionBy(color);
		Set<Position> movablePositions = new HashSet<>();

		if (position.getColumn().getValue() == 2 && color.isWhite()) {
			Position checkPosition = PositionFactory.of(position.getRow(), position.getColumn().calculate(1));
			if (!isPossessed(checkPosition, pieces)) {
				moveDirection = Direction.whiteInitialPawnDirection();
			}
		}

		if (position.getColumn().getValue() == 7 && color.isBlack()) {
			Position checkPosition = PositionFactory.of(position.getRow(), position.getColumn().calculate(-1));
			if (!isPossessed(checkPosition, pieces)) {
				moveDirection = Direction.blackInitialPawnDirection();
			}
		}

		for (Direction direction : moveDirection) {
			Optional<Position> optionalPosition = checkBoundary(position, direction);
			if (optionalPosition.isPresent()) {
				Position movablePosition = optionalPosition.get();
				if (movablePosition.getRow().getValue() == position.getRow().getValue()) {
					if (checkMovable(movablePosition, pieces, color) && !isPossessed(movablePosition, pieces)) {
						movablePositions.add(movablePosition);
					}
					continue;
				}
				if (checkMovable(movablePosition, pieces, color) && isPossessed(movablePosition, pieces)) {
					movablePositions.add(movablePosition);
				}
			}
		}
		return movablePositions;
	}

	private Optional<Position> checkBoundary(Position position, Direction direction) {
		Row row = position.getRow();
		Column column = position.getColumn();
		if (position.checkBound(direction)) {
			Row validRow = row.calculate(direction.getXDegree());
			Column validColumn = column.calculate(direction.getYDegree());
			return Optional.ofNullable(PositionFactory.of(validRow, validColumn));
		}
		return Optional.empty();
	}

	private boolean isPossessed(Position movablePosition, List<Piece> pieces) {
		for (Piece piece : pieces) {
			if (piece.isSamePosition(movablePosition)) {
				return true;
			}
		}
		return false;
	}

	private boolean checkMovable(Position position, List<Piece> pieces, Color color) {
		for (Piece piece : pieces) {
			if (piece.isSamePosition(position) && piece.isSameColor(color)) {
				return false;
			}
		}
		return true;
	}
}
