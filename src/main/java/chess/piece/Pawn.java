package chess.piece;

public final class Pawn extends Piece {
    private final static String BUG_MESSAGE_COLOR = "[BUG] 폰은 색상을 가져야합니다.";
    private static final String BLACK_PAWN = "♟";
    private static final String WHITE_PAWN = "♙";

    Pawn (Color color) {
        super(color);
    }

    @Override
    public String getEmoji() {
        if(color==Color.NONE){
            throw new IllegalArgumentException(BUG_MESSAGE_COLOR);
        }

        if(color==Color.BLACK){
            return BLACK_PAWN;
        }

        return WHITE_PAWN;
    }
}
