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
	public boolean isBlank() {
		return false;
	}
}
