package chess.domain.piece.property;

public final class PieceInfo {

    private final Team team;
    private final PieceFeature pieceFeature;

    public PieceInfo(final Team team, final PieceFeature pieceFeature) {
        this.team = team;
        this.pieceFeature = pieceFeature;
    }

    public boolean checkSameTeam(final Team team) {
        return this.team == team;
    }

    public String getSymbolByTeam() {
        return pieceFeature.symbol(team);
    }

    public String symbol() {
        return pieceFeature.symbol();
    }

    @Override
    public String toString() {
        return "PieceInfo{" +
                "team=" + team +
                ", pieceFeature=" + pieceFeature +
                '}';
    }
}
