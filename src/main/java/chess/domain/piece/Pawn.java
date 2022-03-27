package chess.domain.piece;

import chess.domain.board.Direction;
import chess.domain.board.Position;
import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {

	private static final String BLACK_SYMBOL = "P";
	private static final String WHITE_SYMBOL = "p";
	private static final String CAN_NOT_CATCH_ERROR = "폰은 해당 위치의 기물을 잡을 수 없습니다.";

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
	public void validateMovement(final Position source, final Position target) {
		List<Direction> directions = new ArrayList<>(Direction.getPawnByTeam(team));
		if (source.isInitialPawnRow(team)) {
			directions.add(Direction.getDefaultPawnByTeam(team));
		}
		List<Position> movablePositions = source.calculateMovableByDirection(directions);
		if (!movablePositions.contains(target)) {
			throw new IllegalArgumentException(MOVEMENT_ERROR);
		}
	}

	@Override
	public void validateCatch(final Piece targetPiece, final Direction direction) {
		super.validateCatch(targetPiece, direction);
		if (isWrongCatchDirection(targetPiece, direction)) {
			throw new IllegalArgumentException(CAN_NOT_CATCH_ERROR);
		}
	}

	private boolean isWrongCatchDirection(final Piece targetPiece, final Direction direction) {
		return !targetPiece.isBlank() && (direction == Direction.N || direction == Direction.NN
				|| direction == Direction.S
				|| direction == Direction.SS);
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
