package chess.domain.piece;

public enum PieceColor {

    WHITE("w"),
    BLACK("b");

    private final String value;

    PieceColor(String value) {
        this.value = value;
    }

    private void validate(String value) {

    }
}
