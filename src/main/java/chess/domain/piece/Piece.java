package chess.domain.piece;

import chess.domain.position.RelativePosition;

import static chess.domain.piece.Team.EMPTY;

public abstract class Piece {

    protected final PieceType pieceType;
    protected final Team team;

    protected Piece(final PieceType pieceType, final Team team) {
        this.pieceType = pieceType;
        this.team = team;
    }

    public abstract boolean isMobile(final RelativePosition relativePosition, final Piece target);

    public boolean isEmpty() {
        return this.team == EMPTY;
    }

    public boolean isPieceType(final PieceType pieceType) {
        return this.pieceType == pieceType;
    }

    public boolean isTeam(final Team team) {
        return this.team == team;
    }

    public boolean isSameTeam(final Piece target) {
        return this.team == target.team;
    }

    public PieceType getPieceType() {
        return pieceType;
    }

    public Team getTeam() {
        return team;
    }

    public abstract double getScore();
}
