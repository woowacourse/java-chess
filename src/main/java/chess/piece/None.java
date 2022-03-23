package chess.piece;

public final class None extends Piece {
    private static final String NONE = "·";

    None (Color color) {
        super(color);
    }

    @Override
    public String getEmoji() {
        return NONE;
    }
}
