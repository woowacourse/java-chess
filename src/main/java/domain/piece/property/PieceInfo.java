package domain.piece.property;

public class PieceInfo {

    private Team team;
    private PieceSymbol pieceSymbol;

    public PieceInfo(final Team team, final PieceSymbol pieceSymbol) {
        this.team = team;
        this.pieceSymbol = pieceSymbol;
    }

    public boolean checkSameTeam(Team team) {
        return this.team == team;
    }

    public String getSymbolByTeam() {
        return pieceSymbol.symbol(team);
    }

    public String symbol() {
        return pieceSymbol.symbol();
    }
}
