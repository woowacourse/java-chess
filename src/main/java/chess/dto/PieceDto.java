package chess.dto;

public class PieceDto {
    public String position;
    public String symbol;

    public PieceDto(String position, String symbol) {
        this.position = position;
        this.symbol = symbol;
    }

    public String getPosition() {
        return position;
    }

    public String getSymbol() {
        return symbol;
    }
}
