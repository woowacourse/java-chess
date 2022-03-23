package chess.domain.piece;

import chess.domain.board.Direction;
import chess.domain.board.Position;
import java.util.List;

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
		List<Direction> directions = Direction.getPawnByTeam(team);

		for (Direction direction : directions) {
			Position position = source.addDirection(direction);
			if (position == target) {
				return;
			}
		}

		throw new IllegalArgumentException(MOVEMENT_ERROR);
	}

	@Override
	public boolean isBlank() {
		return false;
	}
}
