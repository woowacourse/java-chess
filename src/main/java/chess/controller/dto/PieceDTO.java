package chess.controller.dto;

public class PieceDTO {
    public String position;
    public String symbol;

    public PieceDTO(String position, String symbol) {
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
