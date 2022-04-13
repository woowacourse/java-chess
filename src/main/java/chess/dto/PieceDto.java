package chess.dto;

public class PieceDto {

    private final String position;
    private final String color;
    private final String name;

    public PieceDto(String position, String color, String name) {
        this.position = position;
        this.color = color;
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public String getColor() {
        return color;
    }

    public String getName() {
        return name;
    }

}
