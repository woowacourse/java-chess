package chess.domain.piece.state;

import chess.domain.board.Board;
import chess.domain.piece.Piece;
import chess.domain.piece.team.Team;
import chess.domain.position.Position;

public abstract class Initialized extends Started {

    protected Initialized(String name, Position position, Team team) {
        super(name, position, team);
    }

    protected abstract boolean canNotMove(Position to, Board board);

    protected boolean isSameTeam(Piece piece) {
        return team.isSame(piece.getTeam());
    }

    @Override
    public boolean isNotBlank() {
        return true;
    }

    @Override
    public boolean isBlank() {
        return false;
    }

    public boolean isStayed(Position to) {
        return position.equals(to);
    }
}
