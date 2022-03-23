package chess.piece;

public class Bishop extends Piece {
    protected Bishop(Color color) {
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
