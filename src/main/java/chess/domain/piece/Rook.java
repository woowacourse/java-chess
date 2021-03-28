package chess.domain.piece;

import chess.domain.piece.direction.*;
import chess.domain.position.Position;
import chess.domain.position.Target;

import java.util.List;

public class Rook extends Piece {
    private static final String SYMBOL = "Rr";
    private static final double SCORE = 5;

    private Rook(final String piece, final Color color, final Position position) {
        super(piece, color, new MoveStrategies(new East(), new West(), new North(), new South()), position);
    }

    public static Rook from(final String piece, final Position position) {
        validate(piece);
        if (isBlack(piece)) {
            return new Rook(piece, Color.BLACK, position);
        }
        return new Rook(piece, Color.WHITE, position);
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
    public double score(final List<Piece> pieces) {
        return SCORE;
    }

    @Override
    public boolean isKing() {
        return false;
    }

    @Override
    public boolean isPawn() {
        return false;
    }

    @Override
    public boolean isKnight() {
        return false;
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
