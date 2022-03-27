package chess.domain.piece;

import chess.domain.board.Direction;
import chess.domain.board.Position;
import java.util.List;

public class Knight extends Piece {

	private static final String BLACK_SYMBOL = "N";
	private static final String WHITE_SYMBOL = "n";

	public Knight(final Team team) {
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
	public void validateMovement(final Position source, final Position target) {
		List<Direction> directions = Direction.getKnightDirection();
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
		return false;
	}

	@Override
	public boolean isKing() {
		return false;
	}

	@Override
	public Direction getDirection(final Position source, final Position target) {
		int rowDifference = target.subtractRow(source);
		int columnDifference = target.subtractColumn(source);
		return Direction.find(rowDifference, columnDifference);
	}

	@Override
	public double getScore() {
		return 2.5;
	}
}
