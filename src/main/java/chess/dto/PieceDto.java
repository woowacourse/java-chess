package chess.dto;

public class PieceDto {

    private final String type;
    private final String color;

    public PieceDto(final String type, final String color) {
        this.type = type;
        this.color = color;
    }

    public String getType() {
        return type;
    }

    public String getColor() {
        return color;
    }
}
