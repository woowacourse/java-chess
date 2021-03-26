package chess.domain.piece.team;

public class Black extends Team {
    public Black(Symbol symbol) {
        super(symbol);
    }

    @Override
    public String getSymbol() {
        return symbol.getBlack();
    }
}
