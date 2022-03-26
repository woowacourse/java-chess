package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;

import java.util.Locale;

public abstract class Piece {

    private static final int ZERO_STEP = 0;
    public static final int ONE_STEP = 1;
    public static final int TWO_STEP = 2;

    private final String name;
    protected final Color color;

    protected Piece(final Color color, final String name) {
        this.color = color;
        this.name = name;
    }

    public abstract void checkPieceMoveRange(final Board board, final Position from, final Position to);

    public void checkAnyPiece(final Board board, final Position from, final Position to) {
        if (board.hasPieceInFile(from, to) || board.hasPieceInRank(from, to)) {
            throw new IllegalArgumentException("이동 경로에 기물이 존재합니다.");
        }
    }

    public boolean isVertical(final Position from, final Position to) {
        return File.difference(from.getFile(), to.getFile()) == ZERO_STEP;
    }

    public boolean isHorizontal(final Position from, final Position to) {
        return Rank.difference(from.getRank(), to.getRank()) == ZERO_STEP;
    }

    public boolean isDiagonal(final Position from, final Position to) {
        return File.difference(from.getFile(), to.getFile()) == Rank.difference(from.getRank(), to.getRank());
    }

    public boolean isBothSidesOneStep(final Position from, final Position to) {
        return File.difference(from.getFile(), to.getFile()) == ZERO_STEP && Rank.difference(from.getRank(), to.getRank()) == ONE_STEP;
    }

    public boolean isUpAndDownOneStep(final Position from, final Position to) {
        return Rank.difference(from.getRank(), to.getRank()) == ZERO_STEP && File.difference(from.getFile(), to.getFile()) == ONE_STEP;
    }

    public boolean isDiagonalOneStep(final Position from, final Position to) {
        return File.difference(from.getFile(), to.getFile()) == ONE_STEP && Rank.difference(from.getRank(), to.getRank()) == ONE_STEP;
    }

    public boolean isKnightMoving(final Position from, final Position to) {
        return Rank.difference(from.getRank(), to.getRank()) == ONE_STEP && File.difference(from.getFile(), to.getFile()) == TWO_STEP ||
                File.difference(from.getFile(), to.getFile()) == ONE_STEP && Rank.difference(from.getRank(), to.getRank()) == TWO_STEP;
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
}
