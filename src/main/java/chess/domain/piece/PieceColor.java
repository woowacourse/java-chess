package chess.domain.piece;

public enum PieceColor {

    WHITE("백"),
    BLACK("흑");

    private final String color;

    PieceColor(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public PieceColor reversed() {
        if(this.equals(WHITE)){
            return BLACK;
        }
        return WHITE;
    }
}
