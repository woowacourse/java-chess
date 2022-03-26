package chess.domain.piece;

import chess.domain.board.Direction;
import chess.domain.board.Position;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Pawn extends Piece {

	public Pawn(final Team team) {
		super(team);
	}

	@Override
	protected String createSymbol(final Team team) {
		if (team.isBlack()) {
			return "P";
		}
		return "p";
	}

	@Override
	public void validateMovement(final Position source, final Position target) {
		List<Direction> directions = new ArrayList<>(Direction.getPawnByTeam(team));

		if (source.isDefaultRow(team)) {
			directions.add(Direction.getDefaultPawnByTeam(team));
		}

		for (Direction direction : directions) {
			Optional<Position> position = source.addDirection(direction);
			if (position.isEmpty()) {
				continue;
			}
			if (position.get() == target) {
				return;
			}
		}

		throw new IllegalArgumentException(MOVEMENT_ERROR);
	}

	@Override
	public void validateCatch(final Piece targetPiece, final Direction direction) {
		super.validateCatch(targetPiece, direction);
		if (!targetPiece.isBlank() && (direction == Direction.N | direction == Direction.NN | direction == Direction.S
				| direction == Direction.SS)) {
			throw new IllegalArgumentException("폰은 해당 위치의 기물을 잡을 수 없습니다.");
		}
	}

	@Override
	public boolean isBlank() {
		return false;
	}

	@Override
	public boolean isPawn() {
		return true;
	}

	@Override
	public boolean isKing() {
		return false;
	}

	@Override
	public double getScore() {
		return 1;
	}
}
