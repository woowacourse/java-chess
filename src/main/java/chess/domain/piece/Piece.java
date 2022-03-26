package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;

import java.util.Locale;

public abstract class Piece {

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
        return File.difference(from.getFile(), to.getFile()) == 0;
    }

    public boolean isHorizontal(final Position from, final Position to) {
        return Rank.difference(from.getRankNumber(), to.getRankNumber()) == 0;
    }

    public boolean isDiagonal(final Position from, final Position to) {
        return File.difference(from.getFile(), to.getFile()) == Rank.difference(from.getRankNumber(), to.getRankNumber());
    }

    public boolean isLeftAndRightOneStep(final Position from, final Position to) {
        return File.difference(from.getFile(), to.getFile()) == 0 && Rank.difference(from.getRankNumber(), to.getRankNumber()) == 1;
    }

    public boolean isTopAndBottomOneStep(final Position from, final Position to) {
        return Rank.difference(from.getRankNumber(), to.getRankNumber()) == 0 && File.difference(from.getFile(), to.getFile()) == 1;
    }

    public boolean isDiagonalOneStep(final Position from, final Position to) {
        return File.difference(from.getFile(), to.getFile()) == 1 && Rank.difference(from.getRankNumber(), to.getRankNumber()) == 1;
    }

    public boolean isKnightMoving(final Position from, final Position to) {
        return Rank.difference(from.getRankNumber(), to.getRankNumber()) == 1 && File.difference(from.getFile(), to.getFile()) == 2 ||
                File.difference(from.getFile(), to.getFile()) == 1 && Rank.difference(from.getRankNumber(), to.getRankNumber()) == 2;
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
