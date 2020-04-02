package chess.domains.piece;

public enum PieceColor {
    BLACK,
    WHITE,
    BLANK;

    public PieceColor changeTeam() {
        if (this == BLACK) {
            return WHITE;
        }
        return BLACK;
    }
}
