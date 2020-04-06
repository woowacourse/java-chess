package chess.domain.piece.strategy;

import java.util.List;
import java.util.Map;

import chess.domain.Color;
import chess.domain.board.Position;
import chess.domain.piece.Direction;
import chess.domain.piece.Path;
import chess.domain.piece.Piece;

public class PawnMoveStrategy implements MoveStrategy {
	List<Direction> moveDirections;
	List<Direction> attackDirections;

	public PawnMoveStrategy(Color color) {
		createMoveDirections(color);
		createAttackDirections(color);
	}

	private void createMoveDirections(Color color) {
		if (color == Color.WHITE) {
			moveDirections = Direction.whitePawnGoDirection();
		}

		if (color == Color.BLACK) {
			moveDirections = Direction.blackPawnGoDirection();
		}
	}

	private void createAttackDirections(Color color) {
		if (color == Color.WHITE) {
			attackDirections = Direction.whitePawnCatchDirection();
		}

		if (color == Color.BLACK) {
			attackDirections = Direction.blackPawnCatchDirection();
		}
	}

	@Override
	public Path findMovablePositions(Path path, Map<Position, Piece> pieces) {
		path.findPathPawnByDirections(moveDirections, attackDirections, pieces);
		return path;
	}
}
