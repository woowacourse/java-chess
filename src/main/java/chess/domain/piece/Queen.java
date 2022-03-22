package chess.domain.piece;

public class Queen extends Piece {

    private static final char WHITE_SIGNATURE = 'q';
    private static final char BLACK_SIGNATURE = 'Q';

    private Queen(Position position, char signature) {
        super(position, signature);
    }

    public static Queen createWhite(Position position) {
        return new Queen(position, WHITE_SIGNATURE);
    }

    public static Queen createBlack(Position position) {
        return new Queen(position, BLACK_SIGNATURE);
    }
}
