package chess.piece;

public final class Bishop extends Piece {
    private final static String BUG_MESSAGE_COLOR = "[BUG] 비숍은 색상을 가져야합니다.";
    private static final String BLACK_BISHOP = "♝";
    private static final String WHITE_BISHOP = "♗";

    Bishop(Color color) {
        super(color);
    }

    @Override
    public String getEmoji() {
        if(color==Color.NONE){
            throw new IllegalArgumentException(BUG_MESSAGE_COLOR);
        }

        if(color==Color.BLACK){
            return BLACK_BISHOP;
        }

        return WHITE_BISHOP;
    }
}
