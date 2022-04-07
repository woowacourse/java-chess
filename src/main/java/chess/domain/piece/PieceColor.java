package chess.domain.piece;

public enum PieceColor {

    WHITE("WHITE"),
    BLACK("BLACK"),
    NONE("NONE");

    private String name;

    PieceColor(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
