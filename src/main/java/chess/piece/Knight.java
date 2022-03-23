package chess.piece;

public final class Knight extends Piece {
    private final static String BUG_MESSAGE_COLOR = "[BUG] 나이트는 색상을 가져야합니다.";
    private static final String BLACK_KNIGHT = "♞";
    private static final String WHITE_KNIGHT = "♘";

    Knight (Color color) {
        super(color);
    }

    @Override
    public String getEmoji() {
        if(color==Color.NONE){
            throw new IllegalArgumentException(BUG_MESSAGE_COLOR);
        }

        if(color==Color.BLACK){
            return BLACK_KNIGHT;
        }

        return WHITE_KNIGHT;
    }
}
