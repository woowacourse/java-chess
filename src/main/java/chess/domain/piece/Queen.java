package chess.domain.piece;

public class Queen extends Piece{
    private static final String SYMBOL = "Qq";

    private Queen(final String piece, final boolean isBlack) {
        super(piece, isBlack);
    }

    public static Queen from(final String piece){
        validate(piece);
        return new Queen(piece, isBlack(piece));
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
