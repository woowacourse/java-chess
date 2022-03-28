package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.position.CoordinateX;
import chess.domain.position.Position;
import chess.domain.position.CoordinateY;

import java.util.Locale;
import java.util.Objects;

public abstract class Piece {

    private static final int ZERO_STEP = 0;
    public static final int ONE_STEP = 1;
    public static final int TWO_STEP = 2;

    protected final Color color;
    private final String name;
    private final double score;

    protected Piece(final Color color, final String name, final double score) {
        this.color = color;
        this.name = name;
        this.score = score;
    }

    public abstract void checkPieceMoveRange(final Board board, final Position from, final Position to);

    public abstract boolean isPawn();

    public abstract boolean isKing();

    public void checkAnyPiece(final Board board, final Position from, final Position to) {
        if (board.hasPieceInXAxis(from, to) || board.hasPieceInYAxis(from, to)) {
            throw new IllegalArgumentException("이동 경로에 기물이 존재합니다.");
        }
    }

    public boolean isVertical(final Position from, final Position to) {
        return CoordinateX.difference(from.getCoordinateX(), to.getCoordinateX()) == ZERO_STEP;
    }

    public boolean isHorizontal(final Position from, final Position to) {
        return CoordinateY.difference(from.getCoordinateY(), to.getCoordinateY()) == ZERO_STEP;
    }

    public boolean isDiagonal(final Position from, final Position to) {
        return CoordinateX.difference(from.getCoordinateX(), to.getCoordinateX()) == CoordinateY.difference(from.getCoordinateY(), to.getCoordinateY());
    }

    public boolean isBothSidesOneStep(final Position from, final Position to) {
        return CoordinateX.difference(from.getCoordinateX(), to.getCoordinateX()) == ZERO_STEP && CoordinateY.difference(from.getCoordinateY(), to.getCoordinateY()) == ONE_STEP;
    }

    public boolean isUpAndDownOneStep(final Position from, final Position to) {
        return CoordinateY.difference(from.getCoordinateY(), to.getCoordinateY()) == ZERO_STEP && CoordinateX.difference(from.getCoordinateX(), to.getCoordinateX()) == ONE_STEP;
    }

    public boolean isDiagonalOneStep(final Position from, final Position to) {
        return CoordinateX.difference(from.getCoordinateX(), to.getCoordinateX()) == ONE_STEP && CoordinateY.difference(from.getCoordinateY(), to.getCoordinateY()) == ONE_STEP;
    }

    public boolean isKnightMoving(final Position from, final Position to) {
        return CoordinateY.difference(from.getCoordinateY(), to.getCoordinateY()) == ONE_STEP && CoordinateX.difference(from.getCoordinateX(), to.getCoordinateX()) == TWO_STEP ||
                CoordinateX.difference(from.getCoordinateX(), to.getCoordinateX()) == ONE_STEP && CoordinateY.difference(from.getCoordinateY(), to.getCoordinateY()) == TWO_STEP;
    }

    public boolean isSameColor(final Color other) {
        return color == other;
    }

    public final String getName() {
        if (color.equals(Color.BLACK)) {
            return name.toUpperCase(Locale.ROOT);
        }
        return name;
    }

    public double getScore() {
        return score;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (!(other instanceof Piece)) return false;

        Piece piece = (Piece) other;

        return name.equals(piece.name) && color == piece.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, color);
    }
}
