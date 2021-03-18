package chess.domain.piece.team;

public class White extends Team {

    public White(Symbol symbol) {
        super(symbol);
    }

    @Override
    public String getSymbol() {
        return symbol.getWhite();
    }
}
