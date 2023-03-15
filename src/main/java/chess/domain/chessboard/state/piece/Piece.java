package chess.domain.chessboard.state.piece;

import chess.domain.chessboard.state.PieceState;
import chess.domain.chessboard.state.Team;

public abstract class Piece implements PieceState {

    private final Team team;

    protected Piece(final Team team) {
        this.team = team;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public Team getTeam() {
        return team;
    }

    @Override
    public boolean isSameTeam(final Piece piece) {
        return this.team.equals(piece.team);
    }
}
