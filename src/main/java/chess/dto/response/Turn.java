package chess.dto.response;

public class Turn {

    private final String color;

    public Turn(final String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }
}
