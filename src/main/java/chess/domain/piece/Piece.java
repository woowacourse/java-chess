package chess.domain.piece;

import chess.domain.Team;

public abstract class Piece {

    protected final PieceType pieceType;
    protected final Team team;

    public Piece(PieceType pieceType, Team team) {
        this.pieceType = pieceType;
        this.team = team;
    }

    public boolean isEmpty() {
        return this.team == Team.EMPTY;
    }

    protected boolean isSameTeam(Piece target) {
        return this.team == target.team;
    }

    public PieceType getPieceType() {
        return pieceType;
    }

    public Team getTeam() {
        return team;
    }
}
