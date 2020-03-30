package chess.domain.piece;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import chess.domain.board.Position;

public class Moving {
	public static final int MIN_BOUNDARY = 1;
	public static final int MAX_BOUNDARY = 8;

	public static List<Position> goOneTimePawnPositions(List<Direction> directions, Position source,
		Map<Position, Piece> chessBoard) {
		List<Position> positions = new ArrayList<>();

		int locationOfX = source.getColumn();
		int locationOfY = source.getRank();

		for (Direction direction : directions) {
			int afterMoveOfX = locationOfX + direction.getX();
			int afterMoveOfY = locationOfY + direction.getY();

			if (isOutOfBoundary(afterMoveOfX) || isOutOfBoundary(afterMoveOfY)) {
				continue;
			}

			if (Objects.nonNull(chessBoard.get(Position.of(afterMoveOfX, afterMoveOfY)))) {
				continue;
			}

			positions.add(Position.of(afterMoveOfX, afterMoveOfY));
		}
		return positions;
	}

	public static List<Position> catchOneTimePawnPositions(List<Direction> directions, Position source,
		Map<Position, Piece> chessBoard) {
		List<Position> positions = new ArrayList<>();

		int locationOfX = source.getColumn();
		int locationOfY = source.getRank();

		for (Direction direction : directions) {
			int afterMoveOfX = locationOfX + direction.getX();
			int afterMoveOfY = locationOfY + direction.getY();

			if (isOutOfBoundary(afterMoveOfX) || isOutOfBoundary(afterMoveOfY)) {
				continue;
			}

			if (Objects.isNull(chessBoard.get(Position.of(afterMoveOfX, afterMoveOfY)))) {
				continue;
			}

			positions.add(Position.of(afterMoveOfX, afterMoveOfY));
		}
		return positions;
	}

	public static List<Position> goAndCatchOneTimePositions(List<Direction> directions, Position source) {
		List<Position> positions = new ArrayList<>();

		int locationOfX = source.getColumn();
		int locationOfY = source.getRank();

		for (Direction direction : directions) {
			int afterMoveOfX = locationOfX + direction.getX();
			int afterMoveOfY = locationOfY + direction.getY();

			if (isOutOfBoundary(afterMoveOfX) || isOutOfBoundary(afterMoveOfY)) {
				continue;
			}

			positions.add(Position.of(afterMoveOfX, afterMoveOfY));
		}
		return positions;
	}

	public static List<Position> goAndCatchManyTimesPositions(List<Direction> directions, Position source,
		Map<Position, Piece> pieces) {
		List<Position> positions = new ArrayList<>();

		int locationOfX = source.getColumn();
		int locationOfY = source.getRank();

		for (Direction direction : directions) {
			int afterMoveOfX = locationOfX + direction.getX();
			int afterMoveOfY = locationOfY + direction.getY();

			while (!isOutOfBoundary(afterMoveOfX) && !isOutOfBoundary(afterMoveOfY)) {

				if (Objects.nonNull(pieces.get(Position.of(afterMoveOfX, afterMoveOfY)))) {
					continue;
				}

				positions.add(Position.of(afterMoveOfX, afterMoveOfY));

				afterMoveOfX += direction.getX();
				afterMoveOfY += direction.getY();
			}
		}
		return positions;
	}

	private static boolean isOutOfBoundary(int location) {
		return location < MIN_BOUNDARY || location > MAX_BOUNDARY;
	}
}
