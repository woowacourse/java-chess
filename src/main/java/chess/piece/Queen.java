package chess.piece;

public final class Queen extends Piece {
    private final static String BUG_MESSAGE_COLOR = "[BUG] 퀸은 색상을 가져야합니다.";
    private static final String BLACK_QUEEN = "♛";
    private static final String WHITE_QUEEN = "♕";

    Queen (Color color) {
        super(color);
    }

    @Override
    public String getEmoji() {
        if(color==Color.NONE){
            throw new IllegalArgumentException(BUG_MESSAGE_COLOR);
        }

        if(color==Color.BLACK){
            return BLACK_QUEEN;
        }

        return WHITE_QUEEN;
    }
}
