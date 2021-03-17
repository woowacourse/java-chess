package chess.domain.piece;

public class Bishop extends Piece{
    private static final String SYMBOL = "Bb";

    private Bishop(final String piece, final boolean isBlack) {
        super(piece, isBlack);
    }

    public static Bishop from(final String piece){
        validate(piece);
        return new Bishop(piece, isBlack(piece));
    }

    private static void validate(final String piece) {
        if (!SYMBOL.contains(piece)) {
            throw new IllegalArgumentException(String.format("옳지 않은 기물입니다! 입력 값: %s", piece));
        }
        if (piece.length() > 1) {
            throw new IllegalArgumentException(String.format("옳지 않은 기물입니다! 입력 값: %s", piece));
        }
    }
}
