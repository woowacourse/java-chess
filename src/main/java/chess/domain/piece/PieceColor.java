package chess.domain.piece;

public enum PieceColor {
    BLACK("흑"),
    WHITE("백"),
    VOID("공허");

    private String name;

    PieceColor(String name) {
        this.name = name;
    }

    public PieceColor oppositeColor() {
        if (this == BLACK) {
            return WHITE;
        }
        return BLACK;
    }

    public boolean isSameColor(PieceColor pieceColor) {
        return this == pieceColor;
    }

    public String getName() {
        return name;
    }
}
