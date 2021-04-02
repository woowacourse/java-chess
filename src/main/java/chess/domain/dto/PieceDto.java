package chess.domain.dto;

public class PieceDto {
    private final String name;
    private final String color;

    public PieceDto(String name, String color) {
        this.name = name;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }
}
