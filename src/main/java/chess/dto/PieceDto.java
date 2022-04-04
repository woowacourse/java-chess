package chess.dto;

public class PieceDto {
    private String position;
    private String symbol;

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
