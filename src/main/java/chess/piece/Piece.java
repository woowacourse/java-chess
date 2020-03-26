package chess.piece;

import java.util.List;

import chess.position.Position;

public abstract class Piece {
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
	}
}
