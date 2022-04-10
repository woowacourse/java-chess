package chess.dto;

public class BoardDto {

    private final String position;
    private final String symbol;
    private final String color;

    public BoardDto(final String position, final String symbol, final String color) {
        this.position = position;
        this.symbol = symbol;
        this.color = color;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getColor() {
        return color;
    }

    public String getPosition() {
        return position;
    }
}
