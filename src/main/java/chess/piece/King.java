package chess.piece;

public final class King extends Piece {
    private final static String BUG_MESSAGE_COLOR = "[BUG] 킹은 색상을 가져야합니다.";
    private static final String BLACK_KING = "♚";
    private static final String WHITE_KING = "♔";

    King (Color color) {
        super(color);
    }

    @Override
    public String getEmoji() {
        if(color==Color.NONE){
            throw new IllegalArgumentException(BUG_MESSAGE_COLOR);
        }

        if(color==Color.BLACK){
            return BLACK_KING;
        }

        return WHITE_KING;
    }
}
