package chess.domain.piece;

import chess.domain.board.Square;

public abstract class Piece {
    protected final Team team;
    protected final PieceType pieceType;

    public Piece(Team team, PieceType pieceType) {
        this.team = team;
        this.pieceType = pieceType;
    }

    public abstract void validateMovableRange(Square source, Square target);

    public boolean isSameRole(PieceType pieceType) {
        return this.pieceType == pieceType;
    }

    public boolean isSameTeam(Team team) {
        return this.team == team;
    }

    public boolean isAnotherTeam(Team team) {
        return this.team != team;
    }

    public boolean isEmpty() {
        return false;
    }

    public Team getTeam() {
        return team;
    }

    public PieceType getRole() {
        return pieceType;
    }
}
