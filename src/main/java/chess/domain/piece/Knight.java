package chess.domain.piece;

import chess.domain.board.Direction;
import chess.domain.board.Position;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Knight extends Piece {

	private static final String BLACK_SYMBOL = "N";
	private static final String WHITE_SYMBOL = "n";
	public static final double KNIGHT_SCORE = 2.5;

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
	public void checkReachable(final Position source, final Position target) {
		List<Direction> directions = Direction.getKnightDirection();
		if (!canMove(source, target, directions)) {
			throw new IllegalArgumentException(MOVEMENT_ERROR);
		}
	}

	private boolean canMove(final Position source, final Position target, final List<Direction> directions) {
		return directions.stream()
				.map(source::addDirection)
				.filter(Optional::isPresent)
				.map(Optional::get)
				.anyMatch(position -> position.equals(target));
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
		List<Direction> directions = Direction.getKnightDirection();
		if (canMove(source, target, directions)) {
			return Direction.find(target.subtractRow(source), target.subtractColumn(source));
		}

		throw new IllegalArgumentException(MOVEMENT_ERROR);
	}

	@Override
	public double getScore() {
		return KNIGHT_SCORE;
	}
}
