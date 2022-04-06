package chess.dto;

public class BoardDto {

    private String position;
    private String symbol;
    private String color;

    public BoardDto(String position, String symbol, String color) {
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

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
