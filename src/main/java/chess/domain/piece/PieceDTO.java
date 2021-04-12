package chess.domain.piece;

public class PieceDTO {
    private final String position;
    private final String color;
    private final String name;

    public PieceDTO(String position, String color, String name) {
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
