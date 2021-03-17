package chess.domain.piece;

public class Rook extends Piece{
    private static final String SYMBOL = "Rr";

    private Rook(final String piece, final boolean isBlack) {
        super(piece, isBlack);
    }

    public static Rook from(final String piece){
        validate(piece);
        return new Rook(piece, isBlack(piece));
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
