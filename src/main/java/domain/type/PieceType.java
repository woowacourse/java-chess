package domain.type;

public enum PieceType implements Type {

    KING,
    QUEEN,
    KNIGHT,
    BISHOP,
    ROOK,
    PAWN;

    @Override
    public boolean isSame(final Type type) {
        return this == type;
    }

    @Override
    public boolean isDifferent(final Type type) {
        return this != type;
    }

}
