package chess.domain;

public abstract class Piece {

    int xPosition;
    int yPosition;
    String team;
    String symbol;

    public Piece(int xPosition, int yPosition, String team, String symbol) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.team = team;
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }
}
