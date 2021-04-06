package chess.controller.dto;

public class PieceResponseDto {

    private final String symbol;
    private final String position;

    public PieceResponseDto(String symbol, String position) {
        this.symbol = symbol;
        this.position = position;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getPosition() {
        return position;
    }
}
