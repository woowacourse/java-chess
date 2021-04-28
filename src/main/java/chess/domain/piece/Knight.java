package chess.domain.piece;

import chess.domain.piece.direction.MoveStrategies;
import chess.domain.position.Position;

import java.util.List;

public class Knight extends Piece {
    private static final String SYMBOL = "Nn";
    private static final String BLACK = "black";
    private static final double SCORE = 2.5;

    private Knight(final String piece, final Color color, final Position position) {
        super(piece, color, MoveStrategies.knightMoveStrategies(), position);
    }

    public static Knight from(final String piece, final Position position) {
        validate(piece);
        if (isBlack(piece)) {
            return new Knight(BLACK + piece, Color.BLACK, position);
        }
        return new Knight(piece, Color.WHITE, position);
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
        return true;
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
