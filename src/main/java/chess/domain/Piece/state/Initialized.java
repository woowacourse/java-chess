package chess.domain.Piece.state;

import chess.domain.board.Board;
import chess.domain.Piece.Piece;
import chess.domain.Piece.team.Team;
import chess.domain.position.Position;

public abstract class Initialized extends Started {
    protected final Position position;

    protected Initialized(String name, Position position, Team team) {
        super(name, team);
        this.position = position;
    }

    protected abstract boolean canNotMove(Position to, Board board);

    protected boolean isNotSameTeam(Piece piece) {
        return team.isNotSame(piece.getTeam());
    }

    protected boolean isSameTeam(Piece piece) {
        return team.isSame(piece.getTeam());
    }
}
