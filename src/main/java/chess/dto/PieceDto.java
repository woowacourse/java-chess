package chess.dto;

public class PieceDto {

    private final String symbol;
    private final String position;
    private final String background;

    public PieceDto(String symbol, String position, String background) {
        this.symbol = symbol;
        this.position = position;
        this.background = background;
    }

    public static PieceDto of(String symbol, String position, String background) {
        return new PieceDto(symbol, position, background);
    }

    public String getSymbol() {
        return symbol;
    }

    public String getPosition() {
        return position;
    }

    public String getBackground() {
        return background;
    }
}
