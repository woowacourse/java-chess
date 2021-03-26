package chess.domain.piece.team;

public class Neutral extends Team {
    public Neutral(Symbol symbol) {
        super(symbol);
    }

    @Override
    public String getSymbol() {
        return symbol.getWhite();
    }
}
