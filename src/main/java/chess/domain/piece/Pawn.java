package chess.domain.piece;

import chess.domain.board.Direction;
import chess.domain.board.Position;
import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {

	private static final String BLACK_SYMBOL = "P";
	private static final String WHITE_SYMBOL = "p";

	public Pawn(final Team team) {
		super(team);
	}

	@Override
	protected String createSymbol(final Team team) {
		if (team.isBlack()) {
			return BLACK_SYMBOL;
		}
		return WHITE_SYMBOL;
	}

	@Override
	protected void validateDirection(final Position source, final Position target, final Piece targetPiece) {
		boolean isAttack = !targetPiece.isBlank();
		List<Direction> directions = new ArrayList<>(Direction.getPawnDirection(team, isAttack));
		if (source.isInitialPawnRow(team)) {
			directions.add(Direction.getDefaultPawnByTeam(team));
		}

		List<Position> movablePositions = source.calculateMovableByDirection(directions);
		if (!movablePositions.contains(target)) {
			throw new IllegalArgumentException(MOVEMENT_ERROR);
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
