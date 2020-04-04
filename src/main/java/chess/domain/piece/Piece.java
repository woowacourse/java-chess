package chess.domain.piece;

public abstract class Piece implements MoveStrategy {
    protected final PieceType pieceType;
    protected final Team team;

    public Piece(final PieceType pieceType, final Team team) {
        this.pieceType = pieceType;
        this.team = team;
    }

    public boolean isEnemy(final Piece targetPiece) {
        return !this.team.isSameTeamWith(targetPiece.getTeam());
    }

    public boolean isEnemy(final Team team) {
        return !this.team.isSameTeamWith(team);
    }

    public boolean isWhiteKing() {
        return this.pieceType.isKing() && this.team.isWhite();
    }

    public boolean isBlackKing() {
        return this.pieceType.isKing() && !this.team.isWhite();
    }

    public boolean isPawn() {
        return this.pieceType.isPawn();
    }

    public String toSymbol() {
        return this.team.symbolize(this.pieceType.getSymbol());
    }

    public Team getTeam() {
        return this.team;
    }

    public double getScore() {
        return this.pieceType.getScore();
    }
}
