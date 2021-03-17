package chess.domain.piece;

import chess.domain.position.Position;

public class Rook extends Piece {
    private static final String SYMBOL = "Rr";

    private Rook(final String piece, final boolean isBlack, final Position position) {
        super(piece, isBlack, position);
    }

    public static Rook from(final String piece, final Position position) {
        validate(piece);
        return new Rook(piece, isBlack(piece), position);
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
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
