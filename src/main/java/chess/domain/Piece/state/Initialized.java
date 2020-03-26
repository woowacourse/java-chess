package chess.domain.Piece.state;

import chess.domain.board.Board;
import chess.domain.Piece.Piece;
import chess.domain.Piece.team.Team;
import chess.domain.position.Position;

public abstract class Initialized implements Piece {
    protected final String name;
    protected final Position position;
    protected final Team team;

    protected Initialized(String name, Position position, Team team) {
        this.name = name;
        this.position = position;
        this.team = team;
    }

    protected abstract boolean canNotMove(Position to, Board board);

    protected boolean isNotSameTeam(Piece piece) {
        return team.isNotSame(piece.getTeam());
    }

    protected boolean isSameTeam(Piece piece) {
        return team.isSame(piece.getTeam());
    }

    @Override
    public Team getTeam() {
        return team;
    }

    @Override
    public boolean isNotBlank() {
        return true;
    }

    @Override
    public boolean isBlank() {
        return false;
    }
}
