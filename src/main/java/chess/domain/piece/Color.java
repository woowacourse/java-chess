package chess.domain.piece;

public enum Color {
    WHITE("WHITE"),
    BLACK("BLACK");

    private final String color;

    Color(String color) {
        this.color = color;
    }


    @Override
    public String toString() {
        return color;
    }
}

