package chess.piece;

public abstract class Piece {

    protected final Team team;

    public Piece(final Team team) {
        this.team = team;
    }

    public boolean isSameTeam(final Piece otherPiece) {
        return this.team.equals(otherPiece.team);
    }

}
