package chess.piece;

import chess.position.Position;

import java.util.List;

public abstract class Piece {
    protected final Team team;
    protected final String initialCharacter;

    public Piece(Team team, String initialCharacter) {
        this.team = team;
        this.initialCharacter = team.isBlack() ? initialCharacter.toUpperCase() : initialCharacter.toLowerCase();
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

    public abstract List<Position> findTraceBetween(Position start, Position end);
}
