package chess.service.dto;

public class PieceWithSquareDto {
    private final String square;
    private final String type;
    private final String color;

    public PieceWithSquareDto(String square, String type, String color) {
        this.square = square;
        this.type = type;
        this.color = color;
    }

    public String getSquare() {
        return square;
    }

    public String getType() {
        return type;
    }

    public String getColor() {
        return color;
    }
}
