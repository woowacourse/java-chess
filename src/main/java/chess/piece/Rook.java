package chess.piece;

public final class Rook extends Piece {
    private final static String BUG_MESSAGE_COLOR = "[BUG] 룩은 색상을 가져야합니다.";
    private static final String BLACK_ROOK = "♜";
    private static final String WHITE_ROOK = "♖";

    Rook (Color color) {
        super(color);
    }

    @Override
    public String getEmoji() {
        if(color==Color.NONE){
            throw new IllegalArgumentException(BUG_MESSAGE_COLOR);
        }

        if(color==Color.BLACK){
            return BLACK_ROOK;
        }

        return WHITE_ROOK;
    }}
