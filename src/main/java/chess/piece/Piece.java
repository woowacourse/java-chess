package chess.piece;

import chess.position.Position;

import java.util.List;

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

	public void updateHasMoved() {
	}

	public boolean isSameTeam(Piece piece) {
		return this.team == piece.team;
	}

    protected abstract String getInitialCharacter();

    public abstract List<Position> findReachablePositions(Position start, Position end);
}
