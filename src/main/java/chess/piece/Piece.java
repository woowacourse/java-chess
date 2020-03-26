package chess.piece;

import java.util.List;

import chess.position.Position;

public abstract class Piece {
	private static final String UNSUPPORTED_UPDATE_FEAT_EXCEPTION_MESSAGE = "지원하지 않는 연산입니다.";

	protected final Team team;

	public Piece(Team team) {
		this.team = team;
	}

	public String getSymbol() {
		if (team.isBlack()) {
			return getInitialCharacter();
		}
		return getInitialCharacter().toLowerCase();
	}

	protected abstract String getInitialCharacter();

	public abstract List<Position> findReachablePositions(Position start, Position end);

	public void updateHasMoved() {
		throw new UnsupportedOperationException(UNSUPPORTED_UPDATE_FEAT_EXCEPTION_MESSAGE);
	}
}
