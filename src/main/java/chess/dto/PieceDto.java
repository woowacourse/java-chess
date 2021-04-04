package chess.dto;

public class PieceDto {
    private final String color;
    private final String name;
    private final String position;

    public PieceDto(String color, String name, String position) {
        this.color = color;
        this.name = name;
        this.position = position;
    }

    public String getColor() {
        return color;
    }

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }
}
