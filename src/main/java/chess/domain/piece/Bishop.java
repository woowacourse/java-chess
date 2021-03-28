package chess.domain.piece;

import chess.domain.piece.direction.*;
import chess.domain.position.Position;

import java.util.List;

public class Bishop extends Piece {
    private static final String SYMBOL = "Bb";
    private static final double SCORE = 3;

    private Bishop(final String piece, final Color color, final Position position) {
        super(piece, color, new MoveStrategies(new Northeast(), new Northwest(), new Southeast(), new Southwest()), position);
    }

    public static Bishop from(final String piece, final Position position) {
        validate(piece);
        if (isBlack(piece)) {
            return new Bishop(piece, Color.BLACK, position);
        }
        return new Bishop(piece, Color.WHITE, position);
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
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
