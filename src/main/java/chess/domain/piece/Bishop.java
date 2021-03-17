package chess.domain.piece;

import chess.domain.position.Position;

public class Bishop extends Piece {
    private static final String SYMBOL = "Bb";

    private Bishop(final String piece, final boolean isBlack, final Position position) {
        super(piece, isBlack, position);
    }

    public static Bishop from(final String piece, final Position position) {
        validate(piece);
        return new Bishop(piece, isBlack(piece), position);
    }

    private static void validate(final String piece) {
        if (!SYMBOL.contains(piece)) {
            throw new IllegalArgumentException(String.format("옳지 않은 기물입니다! 입력 값: %s", piece));
        }
        if (piece.length() > 1) {
            throw new IllegalArgumentException(String.format("옳지 않은 기물입니다! 입력 값: %s", piece));
        }
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
