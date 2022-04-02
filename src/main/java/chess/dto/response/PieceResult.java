package chess.dto.response;

public class PieceResult {
    private final String name;
    private final String color;

    public PieceResult(final String name, final String color) {
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
