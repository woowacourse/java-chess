package chess.domain.piece;

public enum Color {
    BLACK,
    WHITE,
    NONE,
    ;

    public boolean isNotSameTeam(final Piece piece) {
        return this != piece.color();
    }
}
