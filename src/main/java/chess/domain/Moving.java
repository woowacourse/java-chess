package chess.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import chess.domain.board.Position;
import chess.domain.piece.Direction;
import chess.domain.piece.Piece;

public class Moving {
	public static List<Position> goOneTimePositions(List<Direction> directions, Position source,
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

	public static List<Position> goManyTimesPositions(List<Direction> directions, Position source,
		Map<Position, Piece> chessBoard) {
		List<Position> positions = new ArrayList<>();

		int locationOfX = source.getColumn();
		int locationOfY = source.getRank();

		for (Direction direction : directions) {
			int afterMoveOfX = locationOfX + direction.getX();
			int afterMoveOfY = locationOfY + direction.getY();

			while (true) {
				if (isOutOfBoundary(afterMoveOfX) || isOutOfBoundary(afterMoveOfY)) {
					break;
				}

				if (Objects.nonNull(chessBoard.get(Position.of(afterMoveOfX, afterMoveOfY)))) {
					break;
				}

				positions.add(Position.of(afterMoveOfX, afterMoveOfY));

				afterMoveOfX += direction.getX();
				afterMoveOfY += direction.getY();
			}
		}
		return positions;
	}

	private static boolean isOutOfBoundary(int location) {
		if (location < 1 || location > 8) {
			return true;
		}
		return false;
	}
}
