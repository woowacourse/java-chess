package chess.domain.piece;

public enum PieceColor {
    BLACK("B"), WHITE("W");

    private final String flag;

    PieceColor(String flag) {
        this.flag = flag;
    }

    public String getFlag() {
        return this.flag;
    }
}
