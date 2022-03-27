package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.position.Position;

import java.util.Locale;
import java.util.Objects;

public abstract class Piece {

    private final String name;
    private final double score;
    protected final Color color;

    protected Piece(final Color color, final String name, final double score) {
        this.color = color;
        this.name = name;
        this.score = score;
    }

    //추후에 수정하기 view 를 위한 로직
    public final String getName() {
        if (color.equals(Color.BLACK)) {
            return name.toUpperCase(Locale.ROOT);
        }
        return name;
    }

    public boolean isSameColor(final Color other) {
        return color == other;
    }

    public abstract void checkPieceMoveRange(final Board board, final Position from, final Position to);

    public final double getScore() {
        return score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Piece piece = (Piece) o;
        return Objects.equals(name, piece.name) && color == piece.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, color);
    }
}
